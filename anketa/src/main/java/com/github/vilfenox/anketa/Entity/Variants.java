package com.github.vilfenox.anketa.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

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
}