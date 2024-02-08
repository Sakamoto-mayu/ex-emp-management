package com.example.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

@Controller
@RequestMapping("/tmp")
public class TmpController {

    @Autowired
    private EmployeeRepository repository;

    // @GetMapping("/")
    // public String index(Model model) {
    // List<Employee> employees = repository.findAll();
    // model.addAttribute("employees", employees);
    // return "tmp";
    // }
    // @GetMapping("/")
    // public String index(Model model) {
    // Employee employees = repository.load(1);
    // model.addAttribute("employees", employees);
    // return "tmp";
    // }
    @GetMapping("/")
    public String index(Model model) {
        // Date型に変換
        LocalDate hireDate = LocalDate.parse("2002-08-23");
        Employee argEmployee = new Employee("ラクス太郎", "e1.png", "男性", hireDate, "rakus.yamada@sample.com", "666-6666",
                "岩手県盛岡市1-1-1", "090-0000-0000", 450000, "普通のラクス太郎です", 3);

        repository.update(argEmployee);

        Employee employees = repository.load(1);
        model.addAttribute("employees", employees);
        return "tmp";
    }
}
