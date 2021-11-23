package com.github.vilfenox.anketa.repository;

import com.github.vilfenox.anketa.Entity.Questions;
import com.github.vilfenox.anketa.Entity.Variants;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VariantsRepository extends CrudRepository<Variants, Long> {

    Iterable<Variants> findAll();
    Iterable<Variants> findByQuestion_Id(Long id);
    Iterable<Variants> findAllByQuestion_Id(Long id);
    //Optional<Variants> findByValueQuestion(String valueQuestion);
    Optional<Variants> findByValueVariantAndQuestion_Id(String valueQuestion, Long id);
}
