package com.github.vilfenox.anketa.repository;

import com.github.vilfenox.anketa.Entity.Questionnaires;
import com.github.vilfenox.anketa.Entity.Variants;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionnairesRepository extends CrudRepository<Questionnaires, Long> {

    List<Questionnaires> findAll();
    Optional<Questionnaires> findByNameQuestionnaire(String nameQuestionnaire);


    @Query("SELECT v FROM Questionnaires v JOIN v.questions q " +
            "WHERE q.id = :qN")
    Questionnaires findQuestionnairesByQuestionsId(Long qN);
}
