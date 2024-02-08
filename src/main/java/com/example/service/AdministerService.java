package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administer;
import com.example.repository.AdministerRepository;

@Service
@Transactional
public class AdministerService {
    @Autowired
    private AdministerRepository administerRepository;

    /**
     * 管理者を1件登録する
     * 
     * @param administer
     * @return void
     */
    public void insert(Administer administer) {
        administerRepository.insert(administer);
    }
}
