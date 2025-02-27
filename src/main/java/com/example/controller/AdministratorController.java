package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private HttpSession session;

    @Autowired
    private AdministratorService administratorService;

    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator();

        // TODO: nullでもカラムが増えている

        administrator.setName(form.getName());
        administrator.setMailAddress(form.getMailAddress());
        administrator.setPassword(form.getPassword());

        administratorService.insert(administrator);
        return "redirect:/";
    }

    @GetMapping("/")
    public String toLogin(LoginForm form, Model model) {
        return "administrator/login";
    }

    @PostMapping("/login")
    public String login(LoginForm loginForm, Model model) {

        Administrator administrator = null;
        administrator = administratorService.login(loginForm.getMailAddress(), loginForm.getPassword());
        if (administrator == null) {
            model.addAttribute("errMessage", "メールアドレスまたはパスワードは不正です");
            return "administrator/login";
        } else {
            session.setAttribute("administratorName", administrator);
        }
        return "redirect:/employee/showList";
    }

    @GetMapping("/logout")
    public String logout(LoginForm form) {
        session.invalidate();
        return "redirect:/";
    }
}
