package com.tekabs.absence.controllers;

import com.tekabs.absence.models.Groupe;
import com.tekabs.absence.repositories.GroupeRepository;

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
@RequestMapping(path = "/groupe") // This means URL's start with /demo (after Application path)
public class GroupeController {

    @Autowired
    GroupeRepository groupeRepository;

    @GetMapping(path = "/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("groupe-index");
        mv.addObject("groupeModel", new Groupe());
        mv.addObject("groupelist", groupeRepository.findAll());
        return mv;
        // return groupeRepository.findAll();
    }

    @PostMapping(path = "/addgroupe")
    public String addGroupe(@Validated Groupe groupe, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("Wtf");
            return "groupe-index";
        }
        groupeRepository.save(groupe);
        model.addAttribute("groupeModel", new Groupe());
        model.addAttribute("groupelist", groupeRepository.findAll());
        return "groupe-index";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView setToUpdateGroupe(@PathVariable("id") long id) {
        ModelAndView model = new ModelAndView("groupe-index");
        model.addObject("isedit", true);
        model.addObject("groupeModel", groupeRepository.findById(id));
        model.addObject("groupelist", groupeRepository.findAll());
        return model;
    }

    @PostMapping("/update/{id}")
    public String updateGroupe(@PathVariable("id") long id, @Validated Groupe groupe, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            groupe.setId(id);
        } else {
            groupeRepository.save(groupe);

        }
        // return "groupe-index";
        // model.addAttribute("groupeModel", groupeRepository.findById(id));
        model.addAttribute("groupelist", groupeRepository.findAll());
        return "groupe-index";
    }

    @GetMapping("/delete/{id}")
    public String deleteGroupe(@PathVariable("id") long id, Model model) {
        Groupe groupe = groupeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid groupe Id:" + id));
        groupeRepository.delete(groupe);
        model.addAttribute("groupeModel", new Groupe());
        model.addAttribute("groupelist", groupeRepository.findAll());
        return "groupe-index";
    }
}
