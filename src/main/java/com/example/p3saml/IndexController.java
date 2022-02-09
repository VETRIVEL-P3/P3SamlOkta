package com.example.p3saml;

import org.apache.catalina.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/auth")
    public String handleSamlAuth() {
        Authentication auth = getContext().getAuthentication();
        if (auth != null) {
            return "redirect:/home";
        }

        else {
            return "/";
        }
    }
    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String home(ModelMap model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name", authentication.getPrincipal());
        return "home";



    }

}