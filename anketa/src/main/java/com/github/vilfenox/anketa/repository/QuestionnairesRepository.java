package com.github.vilfenox.anketa.repository;

import com.github.vilfenox.anketa.Entity.Questionnaires;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionnairesRepository extends JpaRepository<Questionnaires, Long> {

    Optional<Questionnaires> findAllBy();
}
