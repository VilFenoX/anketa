package com.github.vilfenox.anketa.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
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

    @OneToMany(mappedBy = "variant", fetch = FetchType.EAGER)
    private List<Answers> answers;

}
