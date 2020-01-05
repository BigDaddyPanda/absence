package com.tekabs.absence.controllers;

import java.util.List;

import com.tekabs.absence.models.Student;
import com.tekabs.absence.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/student") // This means URL's start with /demo (after Application path)
public class StudentController {

    @Autowired
    StudentRepository studentRepository;


    @GetMapping(path = "/")
    public ModelAndView home() {
        System.out.println("Fuxk");
        ModelAndView mv = new ModelAndView("student-index");
		mv.addObject("studentModel", new Student());
		mv.addObject("studentlist", studentRepository.findAll());
		return mv;
        // return studentRepository.findAll();
    }

    @PostMapping(path = "/addstudent")
    public String addStudent(@Validated Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("Wtf");
            return "student-index";
        }
        System.out.println(student);
        studentRepository.save(student);
        model.addAttribute("studentModel", new Student());
        model.addAttribute("studentlist", studentRepository.findAll());
        return "student-index";
    }

    @PostMapping("/update/{matricule}")
    public String updateStudent(@PathVariable("matricule") long matricule, @Validated Student student,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            student.setMatricule(matricule);
            return "update-student";
        }

        studentRepository.save(student);
        model.addAttribute("students", studentRepository.findAll());
        return "index";
    }


    @GetMapping("/delete/{matricule}")
    public String deleteStudent(@PathVariable("matricule") long matricule, Model model) {
        Student student = studentRepository.findById(matricule).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + matricule));
        studentRepository.delete(student);
        model.addAttribute("students", studentRepository.findAll());
        return "index";
    }

}
