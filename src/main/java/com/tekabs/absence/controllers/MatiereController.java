package com.tekabs.absence.controllers;

import com.tekabs.absence.models.Matiere;
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
@RequestMapping(path = "/matiere") // This means URL's start with /demo (after Application path)
public class MatiereController {

    @Autowired
    MatiereRepository matiereRepository;

    @GetMapping(path = "/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("matiere-index");
        mv.addObject("matiereModel", new Matiere());
        mv.addObject("matierelist", matiereRepository.findAll());
        return mv;
        // return matiereRepository.findAll();
    }

    @PostMapping(path = "/addmatiere")
    public String addMatiere(@Validated Matiere matiere, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("(>>>>" + result);
            model.addAttribute("matiereModel", matiere);
        } else {
            model.addAttribute("matiereModel", new Matiere());
        }
        matiereRepository.save(matiere);
        model.addAttribute("matierelist", matiereRepository.findAll());
        return "matiere-index";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView setToUpdateMatiere(@PathVariable("id") long id) {
        ModelAndView model = new ModelAndView("matiere-index");
        model.addObject("isEdit", true);
        model.addObject("materialId", id);
        model.addObject("matiereModel", matiereRepository.findById(id));
        model.addObject("matierelist", matiereRepository.findAll());
        return model;
    }

    @PostMapping("/update/{id}")
    public String updateMatiere(@PathVariable("id") long id, @Validated Matiere matiere, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            matiere.setId(id);
            model.addAttribute("matiereModel", matiereRepository.findById(id));
        } else {
            matiereRepository.save(matiere);
            model.addAttribute("matiereModel", new Matiere());
        }
        // return "matiere-index";
        model.addAttribute("matierelist", matiereRepository.findAll());
        return "matiere-index";
    }

    @GetMapping("/delete/{id}")
    public String deleteMatiere(@PathVariable("id") long id, Model model) {
        Matiere matiere = matiereRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid matiere Id:" + id));
        matiereRepository.delete(matiere);
        model.addAttribute("matiereModel", new Matiere());
        model.addAttribute("matierelist", matiereRepository.findAll());
        return "matiere-index";
    }

}
