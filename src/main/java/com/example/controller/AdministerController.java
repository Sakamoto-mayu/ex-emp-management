package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administer;
import com.example.form.InsertAdministerForm;
import com.example.repository.AdministerRepository;
import com.example.service.AdministerService;

@Controller
@RequestMapping("/")
public class AdministerController {

    @Autowired
    private AdministerService administerService;

    @Autowired
    // private EmployeeRepository repository;
    private AdministerRepository repository;

    @GetMapping("/toInsert")
    public String toInsert(InsertAdministerForm form) {
        return "administer/insert";
    }

    @PostMapping("/insert")
    public String insert(InsertAdministerForm form) {
        Administer administer = new Administer(form);
        // TODO: formでFormには正確な引数が入っていない、もしくは取得できていない。
        // TODO: nullでもカラムが増えている
        System.out.println("フォーム" + administer);

        administerService.insert(administer);
        return "tmp";
    }

    @GetMapping("/tmp")
    public String index(Model model) {

        Administer administer = repository.load(4);
        model.addAttribute("administer", administer);
        return "tmp";
    }
}
