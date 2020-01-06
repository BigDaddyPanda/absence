package com.tekabs.absence.controllers;

import com.tekabs.absence.models.Classe;
import com.tekabs.absence.repositories.ClasseRepository;
import com.tekabs.absence.repositories.MatiereRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/classe") // This means URL's start with /demo (after Application path)
public class ClasseController {

    @Autowired
    ClasseRepository classeRepository;

    @Autowired
    MatiereRepository matiereRepository;

    @GetMapping(path = "/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("classe-index");
        mv.addObject("classeModel", new Classe());
        mv.addObject("classelist", classeRepository.findAll());
        mv.addObject("matierelist", matiereRepository.findAll());
        return mv;
        // return classeRepository.findAll();
    }

    @PostMapping(path = "/addclasse")
    public String addClasse(@Validated Classe classe, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("classeModel", classe);
        } else {
            model.addAttribute("classeModel", new Classe());
            classeRepository.save(classe);
        }
        model.addAttribute("classelist", classeRepository.findAll());
        model.addAttribute("matierelist", matiereRepository.findAll());
        return "classe-index";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView setToUpdateClasse(@PathVariable("id") long id) {
        ModelAndView model = new ModelAndView("classe-index");
        model.addObject("isEdit", true);
        model.addObject("classeId", id);
        model.addObject("classeModel", classeRepository.findById(id));
        model.addObject("classelist", classeRepository.findAll());
        model.addObject("matierelist", matiereRepository.findAll());
        return model;
    }

    @PostMapping("/update/{id}")
    public String updateClasse(@PathVariable("id") long id, @Validated Classe classe, BindingResult result,
            Model model) {
        model.addAttribute("classelist", classeRepository.findAll());
        model.addAttribute("matierelist", matiereRepository.findAll());
        if (result.hasErrors()) {
            classe.setId(id);
            model.addAttribute("classeModel", classe);
            return "classe-index";
        }
        model.addAttribute("classeModel", new Classe());
        classeRepository.save(classe);
        return "classe-index";
    }

    @GetMapping("/delete/{id}")
    public String deleteClasse(@PathVariable("id") long id, Model model) {
        Classe classe = classeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid classe Id:" + id));
        classeRepository.delete(classe);
        model.addAttribute("classeModel", new Classe());
        model.addAttribute("classelist", classeRepository.findAll());
        model.addAttribute("matierelist", matiereRepository.findAll());
        return "classe-index";
    }

    @GetMapping("/classe-students/{id}")
    public ModelAndView getGroupStudents(@PathVariable("id") long id) {
        ModelAndView model = new ModelAndView("classe-students-index");
        Classe mg = classeRepository.findById(id).get();
        // // System.out.println(">>>>>" +mg.getclasseStudents());
        model.addObject("classeInfo", mg);
        // System.out.println(mg);
        return model;
    }
}
