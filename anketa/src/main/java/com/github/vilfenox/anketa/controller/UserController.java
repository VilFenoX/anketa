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
        model.addAttribute("questions", questionsRepository.findAllByQuestionnaire_Id(id));
        model.addAttribute("variants", variantsRepository.;
        return "/questions";
    }

    @GetMapping("/questionnaire/{id}/create_question")
    public String createQuestion(@PathVariable Long id, Model model){
        model.addAttribute("question", new Questions());
        Questionnaires questionnaire = questionnairesRepository.findById(id).get();
        model.addAttribute("questionnaire", questionnaire);
        model.addAttribute("questions", questionsRepository.findAllByQuestionnaire_Id(id));
        return "/create_question";
    }

    @PostMapping("/create_question")
    public String addQuestion(@ModelAttribute("question") Questions question,
                              @ModelAttribute("questionnaire") Questionnaires questionnaire,
                              Model model){
        //Questionnaires questionnaires = questionnaire;
        Optional<Questions> questionsFromBD = questionsRepository.findByValueQuestionAndQuestionnaire_Id(question.getValueQuestion(), questionnaire.getId());
        if (questionsFromBD.isPresent()) {
            model.addAttribute("message", "Question exists!");
            return "create_question";
        }
        question.setValueQuestion(question.getValueQuestion());
        questionsRepository.save(question);
        model.addAttribute("questions",questionsRepository.findAllByQuestionnaire_Id(questionnaire.getId()));
        return "questions";
    }


}
