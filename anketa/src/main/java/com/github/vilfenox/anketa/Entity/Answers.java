package com.github.vilfenox.anketa.Entity;

import lombok.*;

import javax.persistence.*;
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
    @ManyToOne
    public Variants variant;
    @ManyToOne
    public User user;

/*    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name="variant_id")
    private Variants variant;*/
    @OneToMany(mappedBy = "answer", fetch = FetchType.EAGER)
    private List<Variants> answer;
}
