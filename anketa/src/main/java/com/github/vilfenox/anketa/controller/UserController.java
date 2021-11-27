package com.github.vilfenox.anketa.controller;


import com.github.vilfenox.anketa.Entity.Questionnaires;
import com.github.vilfenox.anketa.Entity.Questions;
import com.github.vilfenox.anketa.Entity.Variants;
import com.github.vilfenox.anketa.repository.QuestionnairesRepository;
import com.github.vilfenox.anketa.repository.QuestionsRepository;
import com.github.vilfenox.anketa.repository.VariantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private QuestionnairesRepository questionnairesRepository;
    @Autowired
    private QuestionsRepository questionsRepository;
    @Autowired
    private VariantsRepository variantsRepository;

    @GetMapping
    public String getAllQuestionnaires(Model model){
        model.addAttribute("questionnaires",questionnairesRepository.findAll());
        return "/user_questionnaires";
    }


    @GetMapping("/questionnaire/{id}")
  //  @PreAuthorize("hasAuthority('developers:read')")
    public String listQuestion(@PathVariable Long id, Model model){
        Questionnaires questionnaire = questionnairesRepository.findById(id).get();
        model.addAttribute("questionnaire", questionnaire);
        Iterable<Questions> questionsFromBD = questionsRepository.findAllByQuestionnaire_Id(id);
        model.addAttribute("questions", questionsFromBD);
        return "/user_variants";
    }

}
