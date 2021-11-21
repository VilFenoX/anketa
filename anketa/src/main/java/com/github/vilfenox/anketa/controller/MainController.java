package com.github.vilfenox.anketa.controller;


import com.github.vilfenox.anketa.Entity.Questionnaires;
import com.github.vilfenox.anketa.Entity.Questions;
import com.github.vilfenox.anketa.repository.QuestionnairesRepository;
import com.github.vilfenox.anketa.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @Autowired
    private QuestionsRepository questionsRepository;

    @GetMapping
    public String getAllQuestionnaires(Model model){
        model.addAttribute("questionnaires",questionnairesRepository.findAll());
        return "/questionnaires";
    }

    @GetMapping("/create")
    //@RequestMapping("/auth")
    public String create(Model model){
        model.addAttribute("questionnaire", new Questionnaires());
        return "create_questionnaire";
    }

    @PostMapping("/create")
    public String addQuestionnaire(@ModelAttribute("questionnaire") Questionnaires questionnaire, Model model){
        Optional<Questionnaires> questionnairesFromBD = questionnairesRepository.findByNameQuestionnaire(questionnaire.getNameQuestionnaire());
        if (questionnairesFromBD.isPresent()) {
            model.addAttribute("message", "Questionnaire exists!");
            return "create_questionnaire";
        }
        model.addAttribute("questionnaires",questionnairesRepository.findAll());
        questionnaire.setNameQuestionnaire(questionnaire.getNameQuestionnaire());
        questionnairesRepository.save(questionnaire);
        return "/questionnaires";
    }


    @GetMapping("/questionnaire/{id}")
  //  @PreAuthorize("hasAuthority('developers:read')")
    public String listQuestion(@PathVariable Long id, Model model){
       // model.addAttribute("question", new Questions());
        Questionnaires questionnaire = questionnairesRepository.findById(id).get();
        model.addAttribute("questionnaire", questionnaire);
        model.addAttribute("questions", questionsRepository.findAllByQuestionnaire_Id(id));
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
        Questionnaires questionnaires = questionnaire;
       // System.out.println(questionnaires);
        //Optional<Questions> questionsFromBD = questionsRepository.findByValueQuestion(question.getValueQuestion());
        Optional<Questions> questionsFromBD = questionsRepository.findByValueQuestionAndAndQuestionnaire_Id(question.getValueQuestion(), questionnaire.getId());
        if (questionsFromBD.isPresent()) {
            model.addAttribute("message", "Question exists!");
            return "create_question";
        }
       // model.addAttribute("questionnaire", questionnaire);
        question.setValueQuestion(question.getValueQuestion());
        questionsRepository.save(question);
        model.addAttribute("questions",questionsRepository.findAllByQuestionnaire_Id(questionnaire.getId()));
        return "questions";
    }

}
