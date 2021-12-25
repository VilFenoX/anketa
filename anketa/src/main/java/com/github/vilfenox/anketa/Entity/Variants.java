package com.github.vilfenox.anketa.Entity;

import lombok.*;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name="answer_id")
    private Answers answer;
}
