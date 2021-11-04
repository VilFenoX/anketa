package com.github.vilfenox.anketa.repository;

import com.github.vilfenox.anketa.Entity.Questionnaires;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface QuestionnairesRepository extends CrudRepository<Questionnaires, Long> {

    Iterable<Questionnaires> findAll();
    Optional<Questionnaires> findByNameQuestionnaire(String nameQuestionnaire);

}
