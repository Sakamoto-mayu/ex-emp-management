package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.InsertAdministerForm;
import com.example.service.AdministerService;

@Controller
@RequestMapping("/")
public class AdministerController {

    @Autowired
    private AdministerService administerService;

    @GetMapping("/toInsert")
    public String toInsert(InsertAdministerForm form) {
        return "administer/insert";
    }
}
