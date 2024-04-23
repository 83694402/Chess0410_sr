package com.sr_qlp.main.view;

import com.sr_qlp.main.model.Message;
import com.sr_qlp.main.server.ClientThread;
import com.sr_qlp.main.util.SocketUtil;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

/**
 * @author sr
 * * @date Create at 9:56 2024/4/21
 */
public class MainFrame extends JFrame {
    private Socket socket;
    private String account;//当前登陆的用户名
    private JList list;
    private DefaultListModel model;
    private Vector<String> data;//所有登录的用户数据
    public MainFrame setAccount(String account) {
        this.account = account;
        return this;
    }


    public MainFrame(Socket socket,String account){
        this.socket = socket;
        this.account = account;

        setTitle("中国象棋"+account);
        //设置窗口的大小
        setSize(400,300);
        //JFrame默认不可见
        //设置窗口剧中
        setLocationRelativeTo(null);
        //设置点击关闭按钮同时结束虚拟机，每一个java程序都是一个虚拟机
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String [] labels = new String[]{"客户端A","客户端B","客户端C"};
        model = new DefaultListModel();
//        for (int i = 0,n=labels.length; i < n; i++){
//            model.addElement(labels[i]);
//        }
        list = new JList(model);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    System.out.println("双击"+list.getSelectedIndex());
                    String to = data.elementAt(list.getSelectedIndex());
                    Message req = new Message();
                    req.setType(Message.Type.FIGHT);
                    req.setFrom(account);
                    req.setTo(to);
                    SocketUtil.send(socket,req);
                }
            }
        });
        JScrollPane scrollPanel = new JScrollPane(list);
        add(scrollPanel);
        setVisible(true);
        getClientList();
    }

    private void getClientList(){
        Message  req = new Message();
        req.setType(Message.Type.LIST);
//        req.setFrom(account);
        SocketUtil.send(socket,req);
        new ClientThread(socket, new ClientThread.ResponseListener() {
            @Override
            public void success(Message resp) {
                if(resp.getType() == Message.Type.SUCCESS){
                    model.clear();
                    data= (Vector<String>) resp.getContent();
                    data.forEach(item->{

                        model.addElement(item);
                    });

                    list.validate();
                }else if(resp.getType() == Message.Type.FIGHT_SUCCESS){
                    GameFrame gf =  new GameFrame(socket,account,resp.getTo());//打开游戏界面
                    if(account.equals(resp.getFrom())){
                        gf.setPlayer(resp.getFromPlayer());
                        gf.setLocked(false);//红方一开始不锁定棋盘
                        gf.setAccount(resp.getFrom());
                        gf.setTo(resp.getTo());
                    }else{
                        gf.setPlayer(resp.getToPlayer());
                        gf.setLocked(true);//黑方一开始锁定棋盘
                        gf.setAccount(resp.getTo());
                        gf.setTo(resp.getFrom());
                    }

                    MainFrame.this.dispose();//隐藏窗口

                }
            }
        }).start();
    }
    public static void main(String[] args) {
        //new MainFrame(socket);
    }
}
