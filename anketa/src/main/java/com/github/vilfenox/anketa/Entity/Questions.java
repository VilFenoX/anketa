package com.github.vilfenox.anketa.Entity;

import com.github.vilfenox.anketa.model.Choice;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "questions")
@EqualsAndHashCode(exclude = "questionnaire")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String valueQuestion;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "choice_type")
    private Choice choiceType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name="questionnaire_id")
    private Questionnaires questionnaire;

    public Questions() {
    }

    public Long getId() {
        return this.id;
    }

    public String getValueQuestion() {
        return this.valueQuestion;
    }

    public Choice getChoiceType() {
        return this.choiceType;
    }

    public Questionnaires getQuestionnaire() {
        return this.questionnaire;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValueQuestion(String valueQuestion) {
        this.valueQuestion = valueQuestion;
    }

    public void setChoiceType(Choice choiceType) {
        this.choiceType = choiceType;
    }

    public void setQuestionnaire(Questionnaires questionnaire) {
        this.questionnaire = questionnaire;
    }

    public String toString() {
        return "Questions(id=" + this.getId() + ", valueQuestion=" + this.getValueQuestion() + ", choiceType=" + this.getChoiceType() + ", questionnaire=" + this.getQuestionnaire() + ")";
    }
}
