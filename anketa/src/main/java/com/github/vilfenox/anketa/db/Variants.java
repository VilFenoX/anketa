package com.github.vilfenox.anketa.db;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "variants")
@EqualsAndHashCode(exclude = "question")
public class Variants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String valueVariant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name="question_id")
    private Questions question;

/*    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name="answer_id")
    private Answers answer;*/

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "variants_answers",
            joinColumns = @JoinColumn(name = "variant_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private List<Answers> answers = new ArrayList<>();
}
