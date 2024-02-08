package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.repository.AdministratorRepository;
import com.example.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    // private EmployeeRepository repository;
    private AdministratorRepository repository;

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
}
