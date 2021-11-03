package com.github.vilfenox.anketa.controller;


import com.github.vilfenox.anketa.repository.QuestionnairesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class MainController {

    @Autowired
    private QuestionnairesRepository questionnairesRepository;

    @GetMapping
    public String getAll(Model model){

        model.addAttribute("questionnaires",questionnairesRepository.findAll());
        return "/main/resources/templates/questionnaires/questionnaires.html";
    }
}
