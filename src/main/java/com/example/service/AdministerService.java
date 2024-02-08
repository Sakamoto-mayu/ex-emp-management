package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.AdministerRepository;

@Service
@Transactional
public class AdministerService {
    @Autowired
    private AdministerRepository administerRepository;

}
