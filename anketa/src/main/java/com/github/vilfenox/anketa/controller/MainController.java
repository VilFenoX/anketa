package com.github.vilfenox.anketa.controller;


import com.github.vilfenox.anketa.model.Developer;
import com.github.vilfenox.anketa.repository.QuestionnairesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/api")
public class MainController {


    private List<Developer> DEVELOPERS = Stream.of(
            new Developer(1L, "Ivan","Ivanov"),
            new Developer(2L, "Sergey","Sergeev"),
            new Developer(2L, "Petr","Petrov")
    ).collect(Collectors.toList());

    @Autowired
    private QuestionnairesRepository questionnairesRepository;

    @GetMapping
    public String getAll(Model model){
       // System.out.println(questionnairesRepository.findAll());
        model.addAttribute("questionnaires",questionnairesRepository.findAll());
        return "/questionnaires";
    }
}
