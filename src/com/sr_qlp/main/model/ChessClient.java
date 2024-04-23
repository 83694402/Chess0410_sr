package com.sr_qlp.main.model;

import com.sr_qlp.main.server.ChessServer.ServerThread;

/**
 * @author sr
 * * @date Create at 16:22 2024/4/21
 * 服务端记录的客户端数据
 */
public class ChessClient {
    private String account;
    private ServerThread st;

    public ChessClient(){

    }

    public ChessClient(String account, ServerThread st) {
        this.account = account;
        this.st = st;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public ServerThread getSt() {
        return st;
    }

    public void setSt(ServerThread st) {
        this.st = st;
    }

    @Override
    public String toString() {
        return "ChessClient{" +
                "account='" + account + '\'' +
                ", st=" + st +
                '}';
    }
}
