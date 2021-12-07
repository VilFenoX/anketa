package com.github.vilfenox.anketa.repository;

import com.github.vilfenox.anketa.Entity.Answers;
import org.springframework.data.repository.CrudRepository;

public interface AnswersRepository extends CrudRepository<Answers, Long> {

    Iterable<Answers> findAll();
    /*Iterable<Answer> findByQuestion_Id(Long id);
    Iterable<Answer> findAllByQuestion_Id(Long id);
    Optional<Answer> findByValueVariantAndQuestion_Id(String valueQuestion, Long id);

    @Query("SELECT v FROM Variants v JOIN v.question q " +
                                    "JOIN q.questionnaire qn " +
                                    "WHERE qn.id = :qN")
    List<Answer> findVariantsByQuestions_questionnairesId(Long qN);*/
}
