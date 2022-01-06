package com.github.vilfenox.anketa.model;

import com.github.vilfenox.anketa.db.Answers;
import com.github.vilfenox.anketa.db.Questionnaires;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class QuestionnairesForm {
    Questionnaires questionnaires;
    List<Answers> answers = new ArrayList<>();

}
