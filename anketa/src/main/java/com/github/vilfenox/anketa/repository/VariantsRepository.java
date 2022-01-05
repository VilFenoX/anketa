package com.github.vilfenox.anketa.repository;

import com.github.vilfenox.anketa.Entity.Variants;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VariantsRepository extends CrudRepository<Variants, Long> {

    Iterable<Variants> findAll();
    Iterable<Variants> findByQuestion_Id(Long id);
    Iterable<Variants> findAllByQuestion_Id(Long id);
    Optional<Variants> findByValueVariantAndQuestion_Id(String valueQuestion, Long id);
    Optional<Variants> findById (Long id);

    @Query("SELECT v FROM Variants v JOIN v.question q " +
                                    "JOIN q.questionnaire qn " +
                                    "WHERE qn.id = :qN")
    List<Variants> findVariantsByQuestions_questionnairesId(Long qN);

   // Iterable<Variants> findByQuestionnaires(Questionnaires questionnaire);
}
