package com.example.crud.controller;

import com.example.crud.form.UserForm;
import com.example.crud.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.example.crud.form.UserForm.from;

@Controller
public class ProfileController {
    @GetMapping("/")
    public String getProfilePage(ModelMap model, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        User details = (User)authentication.getPrincipal();
        UserForm user = from(details);
        model.addAttribute("user", user);
        return "profile";

    }
}