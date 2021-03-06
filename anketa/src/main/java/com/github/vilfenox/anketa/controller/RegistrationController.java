package com.github.vilfenox.anketa.controller;

import com.github.vilfenox.anketa.model.Role;
import com.github.vilfenox.anketa.model.Status;
import com.github.vilfenox.anketa.db.User;
import com.github.vilfenox.anketa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    //@RequestMapping("/auth")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") User user, Model model){
        Optional<User> userFromDB = userRepository.findOneByEmail(user.getEmail());
        if (userFromDB.isPresent()) {
            model.addAttribute("message", "User exists!");
            return "/registration";
        }
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return "login";
    }
}
