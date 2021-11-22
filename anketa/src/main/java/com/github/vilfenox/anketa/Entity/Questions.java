package com.github.vilfenox.anketa.Entity;

import com.github.vilfenox.anketa.model.Choice;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
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

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<Variants> variants;

    public Questions() {
    }
}
