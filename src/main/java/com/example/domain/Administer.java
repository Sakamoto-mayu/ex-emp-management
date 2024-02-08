package com.example.domain;

import com.example.form.InsertAdministerForm;

public class Administer {
    /**
     * 管理者ID
     */
    private Integer id;
    /**
     * 管理者名前
     */
    private String name;
    /**
     * メールアドレス
     */
    private String mailAddress;
    /**
     * パスワード
     */
    private String password;

    /**
     * 引数なしコンストラクター
     */
    public Administer() {
    }

    /**
     * 引数ありコンストラクタ
     * 
     * @param id
     * @param name
     * @param mailAddress
     * @param password
     */
    public Administer(Integer id, String name, String mailAddress, String password) {
        this.id = id;
        this.name = name;
        this.mailAddress = mailAddress;
        this.password = password;
    }

    public Administer(InsertAdministerForm form) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administer [id=" + id + ", name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
                + "]";
    }

}
