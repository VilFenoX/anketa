package com.github.vilfenox.anketa.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "answers")
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    public Questions question;
/*    @ManyToOne
    public Variants variant;*/
    @ManyToOne
    public User user;

    @ManyToMany(mappedBy = "answers", cascade=CascadeType.ALL)
    private List<Variants> variants = new ArrayList<>();
}
