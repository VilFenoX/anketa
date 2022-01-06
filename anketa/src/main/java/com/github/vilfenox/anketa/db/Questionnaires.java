package com.github.vilfenox.anketa.db;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
