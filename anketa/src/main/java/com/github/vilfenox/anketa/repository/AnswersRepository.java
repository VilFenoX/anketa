package com.github.vilfenox.anketa.repository;

import com.github.vilfenox.anketa.db.Answers;
import com.github.vilfenox.anketa.db.Questions;
import com.github.vilfenox.anketa.db.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AnswersRepository extends CrudRepository<Answers, Long> {

    Iterable<Answers> findAll();

    Optional<Answers> findOneByUserAndQuestion(User user, Questions question);
    List<Answers> findByUser_Id(Long id);


    @Query("SELECT a FROM Answers a JOIN a.question q " +
                                    "JOIN q.questionnaire qn " +
                                    "WHERE qn.id = :qN and a.user.id = :aU")
    List<Answers> findAnswersByQuestionnaire_userId(Long qN, Long aU);
}
