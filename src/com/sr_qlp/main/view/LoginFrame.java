package com.sr_qlp.main.view;

import com.sr_qlp.main.model.Message;
import com.sr_qlp.main.model.User;
import com.sr_qlp.main.util.SocketUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author sr
 * * @date Create at 9:00 2024/4/21
 */
public class LoginFrame extends JFrame implements ActionListener     {
    private JTextField tfAccount;
    private JPasswordField tfPassword;
    private Socket socket;

    public LoginFrame(){
        setTitle("中国象棋");
        //设置窗口的大小
        setSize(400,300);
        //JFrame默认不可见
        //设置窗口剧中
        setLocationRelativeTo(null);
        //设置点击关闭按钮同时结束虚拟机，每一个java程序都是一个虚拟机
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //使用绝对布局
        setLayout(null);
        //账号文字
        JLabel lbAccount = new JLabel("账号");
        lbAccount.setBounds(50,50,50,40);
        add(lbAccount);
        //账号输入框
        tfAccount = new JTextField();
        tfAccount.setBounds(110,50,200,40);
        add(tfAccount);
        //密码文字
        JLabel lbPassword = new JLabel("密码");
        lbPassword.setBounds(50,100,50,40);
        add(lbPassword);
        //密码输入框
        tfPassword = new JPasswordField();
        tfPassword.setBounds(110,100,200,40);
        add(tfPassword);
        //注册按钮
        JButton btnReg = new JButton("注册");
        btnReg.setBounds(50,150,260,40);
        add(btnReg);
        btnReg.setActionCommand("reg");
        btnReg.addActionListener(this);
        //登录按钮
        JButton btnLogin = new JButton("登录");
        btnLogin.setBounds(50,200,120,40);
        add(btnLogin);
        btnLogin.setActionCommand("login");
        btnLogin.addActionListener(this);
        //忘记密码按钮
        JButton btnForget = new JButton("忘记密码");
        btnForget.setBounds(190,200,120,40);

        btnForget.setActionCommand("forget");
        btnForget.addActionListener(this);
        add(btnForget);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case "login":
                login();
                break;
            case "reg":
                reg();
                break;
            case "forget":
                forget();
                break;
            default:
                break;
        }
    }

    private void login(){
        String account = tfAccount.getText();
        char[] password = tfPassword.getPassword();
        String passwordStr = new String(password);
        User bean = new User(account,passwordStr);

        if(socket == null){
            socket = SocketUtil.createLocalHost(8080);
        }
        //发送登录请求
        Message request = new Message();
        request.setType(Message.Type.LOGIN);
        request.setContent(bean);
        request.setFrom(account);

        SocketUtil.send(socket,request);
        //接受服务端的响应信息
//        new Thread(){
//            @Override
//            public void run() {
//                while(true){
                    Object response = SocketUtil.receive(socket);
                    System.out.println(response);
                    if(response instanceof Message){
                        Message resp = (Message) response;
                        if(resp.getType() == Message.Type.SUCCESS){
                            //登录成功
                            //跳转到游戏大厅，隐藏登陆界面
                            LoginFrame.this.dispose();
                            new MainFrame(socket,account);
                        }
                    }

//                }
//            }
//        }.start();

    }
    private void reg(){

    }
    private void forget(){

    }
}
