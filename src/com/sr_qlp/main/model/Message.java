package com.sr_qlp.main.model;

import java.io.Serializable;

/**
 * @author sr
 * * @date Create at 15:45 2024/4/21
 */
public class Message implements Serializable {
    //消息的主体内容
    private Object content;
    //消息类型
    private Type type;
    //消息发起者用户名
    private String from;
    //消息接收者用户名
    private String to;
    //消息发起者阵营
    private int fromPlayer;
    //消息接收者阵营
    private int toPlayer;

    public static enum Type{
        LOGIN,//登录
        REG,//注册
        FORGET,//忘记密码
        SUCCESS,//发送成功
        FAILURE,//发送失败
        LIST,//获取当前登录的所有人
        FIGHT,//发起对战
        FIGHT_SUCCESS,//发起对战成功
        MOVE,//移动棋子
        EAT,//吃子
        PEACE,//求和
        DEFEAT//认输
    }

    public Message(){

    }
    public Message(Object content, Type type, String from, String to) {
        this.content = content;
        this.type = type;
        this.from = from;
        this.to = to;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getFromPlayer() {
        return fromPlayer;
    }

    public void setFromPlayer(int fromPlayer) {
        this.fromPlayer = fromPlayer;
    }

    public int getToPlayer() {
        return toPlayer;
    }

    public void setToPlayer(int toPlayer) {
        this.toPlayer = toPlayer;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content=" + content +
                ", type=" + type +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", fromPlayer=" + fromPlayer +
                ", toPlayer=" + toPlayer +
                '}';
    }


}
