package com.github.vilfenox.anketa.controller;


import com.github.vilfenox.anketa.Entity.*;
import com.github.vilfenox.anketa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
// получаем авторизированного пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        // получаем анкету
        Questionnaires questionnaire = questionnairesRepository.findById(id).get();
        // получаем пользователя
        User user = userRepository.findOneByEmail(currentPrincipalName).get();
        model.addAttribute("user", user);


/*// получаем список вопросов
        Iterable<Variants> variantsFromBD = variantsRepository.findByQuestionnaires(questionnaire);
        for (Variants variant: variantsFromBD) {
            // теоритически пользователь мог уже отвечать на эти вопросы
            // поэтому было бы неплохо найти ответ этого пользователя на данный вопрос в базе данных
            Answer answer = answerRepository.findByUserAndQuestion(user, question)
                    .orElseGet(() -> { // в противном случае создаем новый объект ответа
                        Answer newAnswer = new Answer();
                        newAnswer.setUser(user); // и указываем пользователя
                        newAnswer.setQuestion(question); // и вопрос
                        return newAnswer;
                    });
            answers.add(answer);*/
        model.addAttribute("questionnaire", questionnaire);

        Stack<Questions> questionsFromBD = questionsRepository.findAllByQuestionnaire_Id(id);
        model.addAttribute("question", questionsFromBD.pop());
        model.addAttribute("answer", new Answers());

       return "user_variants_one";
        //return "user_variants_all";
    }

    @PostMapping("/save_answer")
    public String saveAnswer(@ModelAttribute("answer") Answers answer,
                             @ModelAttribute("user") User user, Model model){
       List<Variants> answers = new ArrayList<>();
        System.out.println(user);
        for (Variants ans: answer.getAnswers()
             ) {
            System.out.println(ans);
            answers.add(ans);
            answer.setQuestion(ans.getQuestion());
            answer.setVariant(ans);
            answer.setUser(user);
            answersRepository.save(answer);
        }
       // answer.setAnswers(answer.getAnswers());

        return "/success";
    }
}
