package com.github.vilfenox.anketa.controller;


import com.github.vilfenox.anketa.Entity.*;
import com.github.vilfenox.anketa.repository.*;
import com.github.vilfenox.anketa.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public String getAllQuestionnaires(Model model){
        model.addAttribute("questionnaires",questionnairesRepository.findAll());
        return "/user_questionnaires";
    }


    @GetMapping("/questionnaire/{id}")
  //  @PreAuthorize("hasAuthority('developers:read')")
    public String listQuestion(@PathVariable Long id, Model model){

            // получаем авторизированного пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Questionnaires questionnaire = questionnairesRepository.findById(id).get();
        model.addAttribute("questionnaire", questionnaire);

        model.addAttribute("user", userRepository.findByEmail(currentPrincipalName).get());

        Iterable<Questions> questionsFromBD = questionsRepository.findAllByQuestionnaire_Id(id);
        model.addAttribute("questions", questionsFromBD);
        return "/user_variants";
    }

    @PostMapping("/save_answer")
    public String saveAnswer(@ModelAttribute("user") User user, Model model){
        System.out.println(user);
        return "/success";
    }
}
