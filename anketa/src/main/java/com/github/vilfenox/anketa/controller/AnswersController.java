package com.github.vilfenox.anketa.controller;


import com.github.vilfenox.anketa.Entity.*;
import com.github.vilfenox.anketa.model.QuestionnairesForm;
import com.github.vilfenox.anketa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/answers")
public class AnswersController {

    @Autowired
    private AnswersRepository answersRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionnairesRepository questionnairesRepository;

    @GetMapping("/user_list")
    public String getAllUsers(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "/list_users";
    }


    @GetMapping("/user/{id}")
  //  @PreAuthorize("hasAuthority('developers:read')")
    public String listAnswers(@PathVariable Long id, Model model){
        List<Answers> answersFromBD = answersRepository.findByUser_Id(id);

        if (answersFromBD.isEmpty()) {
            model.addAttribute("message", "User did not answer questions");
            return "/list_users";
        }
        Set<Questionnaires> listQuestionnaires=new HashSet<>();
         answersFromBD.forEach((a) -> listQuestionnaires.add(questionnairesRepository.findQuestionnairesByQuestionsId(a.question.getId())));

        model.addAttribute("questionnaires", listQuestionnaires);

        // получаем авторизированного пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        // получаем пользователя
        User user = userRepository.findOneByEmail(currentPrincipalName).get();
        model.addAttribute("user", user);
        return "/completed_questionnaires";
    }



    @GetMapping("/questionnaire/{id}/user/{id_user}")
    //  @PreAuthorize("hasAuthority('developers:read')")
    public String listVariant(@PathVariable Long id,
                              @PathVariable Long id_user,
                              Model model){
        QuestionnairesForm questionnairesForm = new QuestionnairesForm();
        List<Answers> answersFromBD = answersRepository.findAnswersByQuestionnaire_userId(id, id_user);
        Questionnaires questionnaires = questionnairesRepository.findById(id).get();
        questionnairesForm.setQuestionnaires(questionnaires);
        questionnairesForm.setAnswers(answersFromBD);

        model.addAttribute("questionnairesForm", questionnairesForm);
        return "/completed_answers";
    }

}
