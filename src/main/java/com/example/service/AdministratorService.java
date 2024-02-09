package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    /**
     * 管理者を1件登録する
     * 
     * @param administrator
     * @return void
     */
    public void insert(Administrator administrator) {
        administratorRepository.insert(administrator);
    }

    /**
     * メールアドレスとパスワードが合致する管理者情報を取得
     * 
     * @param mailAddress
     * @param password
     * @return 取得できたら、Administrator 1件返す
     *         取得できない、nullを返す
     */
    public Administrator login(String mailAddress, String password) {
        return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
    }
}
