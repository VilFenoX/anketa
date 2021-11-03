package com.github.vilfenox.anketa.Entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questionnaires")
@EqualsAndHashCode(exclude = "questions")
public class Questionnaires {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_questionnaire")
    private String nameQuestionnaire;

    @OneToMany(mappedBy = "questionnaire", fetch = FetchType.EAGER)
    private List<Questions> questions;

    public Questionnaires() {
    }

    public Long getId() {
        return this.id;
    }

    public String getNameQuestionnaire() {
        return this.nameQuestionnaire;
    }

    public List<Questions> getQuestions() {
        return this.questions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNameQuestionnaire(String nameQuestionnaire) {
        this.nameQuestionnaire = nameQuestionnaire;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    public String toString() {
        return "Questionnaires(id=" + this.getId() + ", nameQuestionnaire=" + this.getNameQuestionnaire() + ", questions=" + this.getQuestions() + ")";
    }
}
