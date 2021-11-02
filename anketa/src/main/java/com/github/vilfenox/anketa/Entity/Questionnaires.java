package com.github.vilfenox.anketa.Entity;


import com.github.vilfenox.anketa.model.Choice;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
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
}
