package com.github.vilfenox.anketa.repository;

import com.github.vilfenox.anketa.Entity.Answers;
import com.github.vilfenox.anketa.Entity.Questions;
import com.github.vilfenox.anketa.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AnswersRepository extends CrudRepository<Answers, Long> {

    Iterable<Answers> findAll();
    Optional<Answers> findOneByUserAndQuestion(User user, Questions question);

    @Query("SELECT v FROM Variants v JOIN v.question q " +
                                    "JOIN q.questionnaire qn " +
                                    "WHERE qn.id = :qN")
    List<Answers> findVariantsByQuestions_questionnairesId(Long qN);

    Optional<Answers> findByUserAndQuestion(User user, Questions question);

    Optional<Answers> findAllByUserAndQuestion(User user, Questions question);
}
