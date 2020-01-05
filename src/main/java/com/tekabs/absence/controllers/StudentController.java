package com.tekabs.absence.controllers;

import com.tekabs.absence.models.Student;
import com.tekabs.absence.repositories.GroupeRepository;
import com.tekabs.absence.repositories.StudentRepository;

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
@RequestMapping(path = "/student") // This means URL's start with /demo (after Application path)
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupeRepository groupeRepository;

    @GetMapping(path = "/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("student-index");
        mv.addObject("studentModel", new Student());
        mv.addObject("studentlist", studentRepository.findAll());
        mv.addObject("groupelist", groupeRepository.findAll());
        return mv;
        // return studentRepository.findAll();
    }

    @PostMapping(path = "/addstudent")
    public String addStudent(@Validated Student student, BindingResult result, Model model) {
        model.addAttribute("studentModel", new Student());
        if (result.hasErrors()) {
            model.addAttribute("studentModel", student);
        }
        studentRepository.save(student);
        model.addAttribute("studentlist", studentRepository.findAll());
        model.addAttribute("groupelist", groupeRepository.findAll());
        return "student-index";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView setToUpdateStudent(@PathVariable("id") long id) {
        ModelAndView model = new ModelAndView("student-index");
        model.addObject("isEdit", true);
        model.addObject("studentId", id);
        model.addObject("groupelist", groupeRepository.findAll());
        model.addObject("studentModel", studentRepository.findById(id));
        model.addObject("studentlist", studentRepository.findAll());
        return model;
    }

    @PostMapping("/update/{matricule}")
    public String updateStudent(@PathVariable("matricule") long matricule, @Validated Student student,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            student.setMatricule(matricule);
            model.addAttribute("studentModel", studentRepository.findById(matricule));
            return "student-index";
        }
        studentRepository.save(student);
        model.addAttribute("groupelist", groupeRepository.findAll());
        model.addAttribute("studentModel", new Student());
        model.addAttribute("studentlist", studentRepository.findAll());
        return "student-index";
    }

    @GetMapping("/delete/{matricule}")
    public String deleteStudent(@PathVariable("matricule") long matricule, Model model) {
        Student student = studentRepository.findById(matricule)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + matricule));
        studentRepository.delete(student);
        model.addAttribute("studentModel", new Student());
        model.addAttribute("groupelist", groupeRepository.findAll());
        model.addAttribute("studentlist", studentRepository.findAll());
        return "student-index";
    }

}
