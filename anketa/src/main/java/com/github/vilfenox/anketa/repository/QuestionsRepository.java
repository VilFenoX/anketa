package com.github.vilfenox.anketa.repository;

import com.github.vilfenox.anketa.Entity.Questionnaires;
import com.github.vilfenox.anketa.Entity.Questions;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

public interface QuestionsRepository extends CrudRepository<Questions, Long> {

    Iterable<Questions> findAll();
    //Iterable<Questions> findAllByQuestionnaire_Id(Long id);
    Stack<Questions> findAllByQuestionnaire_Id(Long id);

    Optional<Questions> findByValueQuestion(String valueQuestion);
    Optional<Questions> findByValueQuestionAndQuestionnaire_Id(String valueQuestion, Long id);
}
