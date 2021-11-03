package com.github.vilfenox.anketa.controller;


import com.github.vilfenox.anketa.Entity.Questionnaires;
import com.github.vilfenox.anketa.Entity.User;
import com.github.vilfenox.anketa.model.Developer;
import com.github.vilfenox.anketa.model.Role;
import com.github.vilfenox.anketa.model.Status;
import com.github.vilfenox.anketa.repository.QuestionnairesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/api")
public class MainController {


/*    private List<Developer> DEVELOPERS = Stream.of(
            new Developer(1L, "Ivan","Ivanov"),
            new Developer(2L, "Sergey","Sergeev"),
            new Developer(2L, "Petr","Petrov")
    ).collect(Collectors.toList());*/

    @Autowired
    private QuestionnairesRepository questionnairesRepository;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("questionnaires",questionnairesRepository.findAll());
        return "/questionnaires";
    }

    @GetMapping("/create")
    //@RequestMapping("/auth")
    public String create(Model model){
        model.addAttribute("questionnaire", new Questionnaires());
        return "/create";
    }

    @PostMapping("/create")
    public String addQuestionnaire(@ModelAttribute("questionnaire") Questionnaires questionnaire, Model model){
        Optional<Questionnaires> questionnairesFromBD = questionnairesRepository.findByNameQuestionnaire(questionnaire.getNameQuestionnaire());
        if (questionnairesFromBD.isPresent()) {
            model.addAttribute("message", "Questionnaire exists!");
            return "/create";
        }
        questionnaire.setNameQuestionnaire(questionnaire.getNameQuestionnaire());
        questionnairesRepository.save(questionnaire);
        return "/create";
    }
}
