package com.github.vilfenox.anketa.Entity;

import com.github.vilfenox.anketa.model.Choice;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "questions")
@EqualsAndHashCode(exclude = "questionnaire")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @Column(name = "questionnaire_id")
   // private Long questionnaireId;

    @Column(name = "value")
    private String valueQuestion;

    @Column(name = "choice_type")
    private Choice choiceType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name="questionnaire_id")
    private Questionnaires questionnaire;

}
