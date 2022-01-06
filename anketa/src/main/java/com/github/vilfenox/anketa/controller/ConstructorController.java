package com.github.vilfenox.anketa.controller;


import com.github.vilfenox.anketa.db.Questionnaires;
import com.github.vilfenox.anketa.db.Questions;
import com.github.vilfenox.anketa.db.Variants;
import com.github.vilfenox.anketa.repository.QuestionnairesRepository;
import com.github.vilfenox.anketa.repository.QuestionsRepository;
import com.github.vilfenox.anketa.repository.VariantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api")
public class ConstructorController {

    @Autowired
    private QuestionnairesRepository questionnairesRepository;
    @Autowired
    private QuestionsRepository questionsRepository;
    @Autowired
    private VariantsRepository variantsRepository;

    @GetMapping
    public String getAllQuestionnaires(Model model){
        model.addAttribute("questionnaires", questionnairesRepository.findAll());
        return "/questionnaires";
    }

    @GetMapping("/create")
    //@RequestMapping("/auth")
    public String create(Model model){
        model.addAttribute("questionnaire", new Questionnaires());
        return "create_questionnaire";
    }

    @PostMapping("/create")
    public String addQuestionnaire(@ModelAttribute("questionnaire") Questionnaires questionnaire, Model model){
        Optional<Questionnaires> questionnairesFromBD = questionnairesRepository.findByNameQuestionnaire(questionnaire.getNameQuestionnaire());
        if (questionnairesFromBD.isPresent()) {
            model.addAttribute("message", "Questionnaire exists!");
            return "create_questionnaire";
        }
        questionnaire.setNameQuestionnaire(questionnaire.getNameQuestionnaire());
        questionnairesRepository.save(questionnaire);
        model.addAttribute("questionnaires",questionnairesRepository.findAll());
        return "/questionnaires";
    }

    @GetMapping("/questionnaire/{id}")
  //  @PreAuthorize("hasAuthority('developers:read')")
    public String listQuestion(@PathVariable Long id, Model model){
        Questionnaires questionnaire = questionnairesRepository.findById(id).get();
        model.addAttribute("questionnaire", questionnaire);
        model.addAttribute("questions", questionsRepository.findAllByQuestionnaire_Id(id));
        return "/questions";
    }

    @GetMapping("/questionnaire/{id}/edit")
    //  @PreAuthorize("hasAuthority('developers:read')")
    public String listQuestionnaireEdit(@PathVariable Long id, Model model){
        Questionnaires questionnaire = questionnairesRepository.findById(id).get();
        model.addAttribute("questionnaire", questionnaire);
        return "/questionnaires_edit";
    }

    @RequestMapping("/questionnaire/{id}")
    //  @PreAuthorize("hasAuthority('developers:read')")
    public String patchQuestionnaire(@ModelAttribute("questionnaire") Questionnaires questionnaire,
                                     @PathVariable Long id, Model model){
        Questionnaires questionnaireFromDB = questionnairesRepository.findById(id).get();
        questionnaireFromDB.setNameQuestionnaire(questionnaire.getNameQuestionnaire());
        questionnairesRepository.save(questionnaireFromDB);
        model.addAttribute("questionnaires", questionnairesRepository.findAll());
        return "questionnaires";
    }

    @RequestMapping("/questionnaire/{id}/delete")
    //  @PreAuthorize("hasAuthority('developers:read')")
    public String deleteQuestionnaire(@PathVariable Long id, Model model){
        Questionnaires questionnaireFromDB = questionnairesRepository.findById(id).get();
        questionnairesRepository.delete(questionnaireFromDB);
        model.addAttribute("questionnaires", questionnairesRepository.findAll());
        return "questionnaires";
    }


    @GetMapping("/questionnaire/{id}/create_question")
    public String createQuestion(@PathVariable Long id, Model model){
        model.addAttribute("question", new Questions());
        Questionnaires questionnaire = questionnairesRepository.findById(id).get();
        model.addAttribute("questionnaire", questionnaire);
        model.addAttribute("questions", questionsRepository.findAllByQuestionnaire_Id(id));
        return "/create_question";
    }

    @PostMapping("/create_question")
    public String addQuestion(@ModelAttribute("question") Questions question,
                              @ModelAttribute("questionnaire") Questionnaires questionnaire,
                              Model model){
        Optional<Questions> questionsFromBD = questionsRepository.findByValueQuestionAndQuestionnaire_Id(question.getValueQuestion(), questionnaire.getId());
        if (questionsFromBD.isPresent()) {
            model.addAttribute("message", "Question exists!");
            return "create_question";
        }
        question.setValueQuestion(question.getValueQuestion());
        questionsRepository.save(question);
        model.addAttribute("questions",questionsRepository.findAllByQuestionnaire_Id(questionnaire.getId()));
        return "questions";
    }

    @GetMapping("/question/{id}/edit")
    //  @PreAuthorize("hasAuthority('developers:read')")
    public String listQuestionEdit(@PathVariable Long id,
                                   @ModelAttribute("questionnaire") Questionnaires questionnaire,
                                   Model model){
        Questions question = questionsRepository.findById(id).get();
        model.addAttribute("question", question);
        model.addAttribute("questionnaire", questionnaire);
        return "/question_edit";
    }

    @RequestMapping("/question/{id}")
    //  @PreAuthorize("hasAuthority('developers:read')")
    public String patchQuestion(@ModelAttribute("question") Questions question,
                                 @PathVariable Long id, Model model){
        Questionnaires questionnaire = questionnairesRepository.findQuestionnairesByQuestionsId(id);
        Questions questionFromDB = questionsRepository.findById(id).get();
        questionFromDB.setValueQuestion(question.getValueQuestion());
        questionFromDB.setChoiceType(question.getChoiceType());
        model.addAttribute("questionnaire", questionnaire);
        questionsRepository.save(questionFromDB);
        model.addAttribute("questions",questionsRepository.findAllByQuestionnaire_Id(questionnaire.getId()));
        return "/questions";
    }

    @RequestMapping("/question/{id}/delete")
    //  @PreAuthorize("hasAuthority('developers:read')")
    public String deleteQuestion(@PathVariable Long id,
                                 Model model){
        Questionnaires questionnaire = questionnairesRepository.findQuestionnairesByQuestionsId(id);
        Questions questionFromDB = questionsRepository.findById(id).get();
        questionsRepository.delete(questionFromDB);
        model.addAttribute("questionnaire", questionnaire);
        model.addAttribute("questions",questionsRepository.findAllByQuestionnaire_Id(questionnaire.getId()));
        return "questions";
    }


    @GetMapping("/question/{id}")
    //  @PreAuthorize("hasAuthority('developers:read')")
    public String listVariant(@PathVariable Long id, Model model){
        Questions question = questionsRepository.findById(id).get();
        model.addAttribute("question", question);
        model.addAttribute("variants", variantsRepository.findByQuestion_Id(id));
        return "/variants";
    }

    @GetMapping("/question/{id}/create_variant")
    public String createVariant(@PathVariable Long id, Model model){
        model.addAttribute("variant", new Variants());
        Questions question = questionsRepository.findById(id).get();
        model.addAttribute("question", question);
        model.addAttribute("variants", variantsRepository.findAllByQuestion_Id(id));
        return "/create_variant";
    }

    @PostMapping("/create_variant")
    public String addVariant(@ModelAttribute("variant") Variants variant,
                              @ModelAttribute("question") Questions question,
                              Model model){
        Optional<Variants> variantsFromBD = variantsRepository.findByValueVariantAndQuestion_Id(variant.getValueVariant(), question.getId());
        if (variantsFromBD.isPresent()) {
            model.addAttribute("message", "Variant exists!");
            return "create_variant";
        }
        variant.setValueVariant(variant.getValueVariant());
        variantsRepository.save(variant);
        model.addAttribute("variants",variantsRepository.findAllByQuestion_Id(question.getId()));
        return "variants";
    }

    @GetMapping("/variant/{id}/edit")
    //  @PreAuthorize("hasAuthority('developers:read')")
    public String listVariantEdit(@PathVariable Long id,
                                   Model model){
        Variants variant = variantsRepository.findById(id).get();
        model.addAttribute("variant", variant);
        return "/variant_edit";
    }

    @RequestMapping("/variant/{id}")
    //  @PreAuthorize("hasAuthority('developers:read')")
    public String patchVariant(@PathVariable Long id,
                               @ModelAttribute("variant") Variants variant,
                               Model model){
        Questions question = questionsRepository.findQuestionByVariantsId(id);
        Variants variantFromDB = variantsRepository.findById(id).get();
        variantFromDB.setValueVariant(variant.getValueVariant());
        variantsRepository.save(variantFromDB);
        model.addAttribute("question", question);
        model.addAttribute("variants",variantsRepository.findAllByQuestion_Id(question.getId()));
        return "/variants";
    }

    @RequestMapping("/variant/{id}/delete")
    //  @PreAuthorize("hasAuthority('developers:read')")
    public String deleteVariant(@PathVariable Long id,
                                 Model model){
        Questions question = questionsRepository.findQuestionByVariantsId(id);
        Variants variantFromDB = variantsRepository.findById(id).get();
        variantsRepository.delete(variantFromDB);
        model.addAttribute("question", question);
        model.addAttribute("variants",variantsRepository.findAllByQuestion_Id(question.getId()));
        return "/variants";
    }
}
