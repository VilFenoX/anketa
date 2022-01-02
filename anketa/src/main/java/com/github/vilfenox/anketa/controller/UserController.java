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

import java.util.ArrayList;
import java.util.List;

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


        // создаем список для хранения ответов
        List<Answers> answers = new ArrayList<>();
        // получаем список вопросов
        Iterable<Questions> questionsFromBD = questionsRepository.findByQuestionnaire(questionnaire);
        for (Questions question: questionsFromBD) {
            // теоритически пользователь мог уже отвечать на эти вопросы
            // поэтому было бы неплохо найти ответ этого пользователя на данный вопрос в базе данных
            Answers answer = answersRepository.findOneByUserAndQuestion(user, question)  //... если уже заполнял выдает ошибку
                    .orElseGet(() -> { // в противном случае создаем новый объект ответа
                        Answers newAnswer = new Answers();
                        newAnswer.setUser(user); // и указываем пользователя
                        newAnswer.setQuestion(question); // и вопрос
                        return newAnswer;
                    });
            answers.add(answer);
        }
        // подготовливаем форму
        QuestionnairesForm questionnairesForm = new QuestionnairesForm();
        questionnairesForm.setQuestionnaires(questionnaire);
        questionnairesForm.setAnswers(answers);
        // и передаем на страницу
        model.addAttribute("questionnairesForm", questionnairesForm);
       //return "user_variants_one";
        return "user_variants_all";
    }

    @PostMapping("/save_answer")
    public String saveAnswer(@ModelAttribute("questionnairesForm") QuestionnairesForm questionnairesForm,
                             @ModelAttribute("user") User user, Model model){
        for (Answers ans: questionnairesForm.getAnswers()) {
            Answers answer = new Answers();
            Variants variant = new Variants();
           // variant.setValueVariant(ans.);
            variant.getAnswers().add(ans);

            variant.setQuestion(ans.getQuestion());
            variant.setId(ans.getVariants());
           // answer.setVariant(ans.getVariant());
           // answer.setQuestion(ans.getQuestion());
           // answer.setUser(user);
           // answer.setVariants(ans.getVariants());

            answersRepository.save(answer);

        }
        return "/success";
    }
}
