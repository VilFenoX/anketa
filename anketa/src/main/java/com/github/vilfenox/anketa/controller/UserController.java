package com.github.vilfenox.anketa.controller;


import com.github.vilfenox.anketa.Entity.*;
import com.github.vilfenox.anketa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Stack;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private QuestionnairesRepository questionnairesRepository;
    @Autowired
    private QuestionsRepository questionsRepository;
    @Autowired
    private VariantsRepository variantsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnswersRepository answersRepository;


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


        Stack<Questions> questionsFromBD = questionsRepository.findAllByQuestionnaire_Id(id);
        model.addAttribute("question", questionsFromBD.pop());
        model.addAttribute("answer", new Answers());

        return "/user_variants";
    }

    @PostMapping("/save_answer")
    public String saveAnswer(@ModelAttribute("answer") Answers answer, Model model){
        answersRepository.save(answer);
        return "/success";
    }
}
