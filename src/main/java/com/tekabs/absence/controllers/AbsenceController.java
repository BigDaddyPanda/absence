package com.tekabs.absence.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;

import com.tekabs.absence.models.Absence;
import com.tekabs.absence.models.AbsenceUtil;
import com.tekabs.absence.models.Classe;
import com.tekabs.absence.models.Matiere;
import com.tekabs.absence.models.Student;
import com.tekabs.absence.repositories.AbsenceRepository;
import com.tekabs.absence.repositories.ClasseRepository;
import com.tekabs.absence.repositories.MatiereRepository;
import com.tekabs.absence.repositories.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/absence") // This means URL's start with /demo (after Application path)
public class AbsenceController {

    @Autowired
    AbsenceRepository absenceRepository;

    @Autowired
    ClasseRepository classeRepository;

    @Autowired
    MatiereRepository matiereRepository;

    @Autowired
    StudentRepository studentRepository;

    @GetMapping(path = "/classe/{id}/matiere/{matid}")
    public ModelAndView getGroupeAbsenceList(@PathVariable("id") long id, @PathVariable("matid") long matid) {
        ModelAndView mv = new ModelAndView("absence-classe-view");
        Classe classeEtudiant = classeRepository.findById(id).get();
        Iterable<Student> groupeEtudiant = classeEtudiant.getClasseStudents();
        // ArrayList<Absence> listeAbsence = new ArrayList<>();
        Matiere matiere = matiereRepository.findById(matid).get();
        AbsenceUtil absenceUtil = new AbsenceUtil(groupeEtudiant, matiere);
        mv.addObject("matiere", matiere);
        mv.addObject("absenceUtil", absenceUtil);
        mv.addObject("classeEtudiant", classeEtudiant);
        return mv;
    }

    @GetMapping(path = "/student/{id}")
    public ModelAndView getStudentAbsence(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("absence-etudiant-view");
        Student st = studentRepository.findById(id).get();
        List<Matiere> matieres = st.getStudentClasse().getClasseMatieres();
        ArrayList<Absence> absences = new ArrayList<>();
        for (Matiere mat : matieres) {
            Absence abs = absenceRepository.findAbsenceByMatiereAndMatricule(mat.getId(), st.getMatricule()).get();
            if (abs.getEstAbsent())
                absences.add(abs);
        }

        mv.addObject("student", st);
        mv.addObject("matieres", matieres);
        mv.addObject("absences", absences);
        return mv;
    }

    @PostMapping(path = "/save")
    public String addMatiere(@Validated AbsenceUtil absenceUtil, BindingResult result) {
        for (Absence abs : absenceUtil.getListeAbsence()) {
            abs.setDateEnregistrement(absenceUtil.getDateEnregistrement());
            absenceRepository.save(abs);
        }
        return "redirect:/classe/";
    }

}
