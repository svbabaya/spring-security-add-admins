package ru.maxima.springboottest.ProjectSpringBoot1.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maxima.springboottest.ProjectSpringBoot1.models.Person;
import ru.maxima.springboottest.ProjectSpringBoot1.services.PeopleService;
import ru.maxima.springboottest.ProjectSpringBoot1.services.RegistrationService;
import ru.maxima.springboottest.ProjectSpringBoot1.validate.PersonValidator;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService service;
    private final PersonValidator validator;
    private final PeopleService peopleService;

    @Autowired
    public AuthController(RegistrationService service, PersonValidator validator, PeopleService peopleService ) {
        this.service = service;
        this.validator = validator;
        this.peopleService = peopleService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {

        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult) {
        validator.validate(person, bindingResult);
        service.register(person);
        return "redirect:/auth/login";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "auth/admin";
    }

    @GetMapping("/logout")
    public String logOutPage() {
        return "logout";
    }

    @GetMapping("/makeAdmin")
    public String makeAdmin(Model model) {
        String confirm = null;
        int id = 0;
        model.addAttribute("people", peopleService.findRoleUser());
        model.addAttribute("confirm", confirm);
        model.addAttribute("idPerson", id);
        return "/auth/makeAdmin";
    }

    @PostMapping("/makeAdmin")
    public String saveChanges(@ModelAttribute("confirm") String confirm,
                              @ModelAttribute("idPerson") int id) {
        if (confirm.equals("on")) {
            Person person = peopleService.findOne(id);
            person.setRole("ROLE_ADMIN");
            peopleService.update(id, person);
        }
        return "redirect:/people";
    }
}
