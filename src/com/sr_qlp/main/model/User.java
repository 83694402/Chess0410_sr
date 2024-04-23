package com.sr_qlp.main.model;

import java.io.Serializable;

/**
 * @author sr
 * * @date Create at 10:24 2024/4/21
 */
public class User implements Serializable {
    private String account;
    private String password;

    public User(){

    }
    public User(String account,String password){
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
