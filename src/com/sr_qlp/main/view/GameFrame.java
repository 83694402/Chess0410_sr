package com.sr_qlp.main.view;

import com.sr_qlp.main.game.Chess;
import com.sr_qlp.main.game.GamePanel_SR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

/**
 * @author sr
 * * @date 2024/4/10
 */
public class GameFrame extends JFrame implements ActionListener {
    //gamePanel
    private GamePanel_SR gp = null;
    private int player;
    private String account;
    private Socket socket;
    private boolean isLocked = false;
    private String to;//对手名称

    public void setLocked(boolean locked) {
        isLocked = locked;
        gp.setLocked(locked);
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPlayer(int player){
        this.player = player;
        gp.setCurPlayer(player);
    }
    public GameFrame(Socket socket, String account,String to){
        this.socket = socket;
        this.account = account;
        this.to = to;
        setTitle("中国象棋"+account);
        //设置窗口的大小
        setSize(560,500);
        //JFrame默认不可见
        /*面向对象编程指的是
         * 如何定义类
         * 如何使用类中的方法和属性
         * 直接使用属性的情况非常少见，因为不安全
         * */
        //设置窗口剧中
        setLocationRelativeTo(null);
        //设置点击关闭按钮同时结束虚拟机，每一个java程序都是一个虚拟机
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //为面板设置borderlayout布局
        setLayout(new BorderLayout());
        //将游戏面板添加到窗口中
        gp = new GamePanel_SR();
        gp.setAccount(account);
        gp.setSocket(socket);
        gp.setTo(to);
        add(gp,BorderLayout.CENTER);
        //添加按钮面板
        JPanel btnPanel = new JPanel(new GridLayout(6,1));
        add(btnPanel,BorderLayout.EAST);
        JLabel hintLabel = new JLabel("红方走");
        gp.setHintLabel(hintLabel);
        btnPanel.add(hintLabel);
        JButton btnHuiQi = new JButton("悔棋");
        btnPanel.add(btnHuiQi);
        btnHuiQi.addActionListener(this);
        btnHuiQi.setActionCommand("huiqi");
        JButton btnSave = new JButton("保存棋谱");
        btnPanel.add(btnSave);
        btnSave.addActionListener(this);
        btnSave.setActionCommand("baocun");
        JButton btnImport = new JButton("导入棋谱");
        btnPanel.add(btnImport);
        btnImport.addActionListener(this);
        btnImport.setActionCommand("daoru");
        JButton btnQiuHe = new JButton("求和");
        btnPanel.add(btnQiuHe);
        btnQiuHe.addActionListener(this);
        btnQiuHe.setActionCommand("qiuhe");
        JButton btnRenShu = new JButton("认输");
        btnPanel.add(btnRenShu);
        btnRenShu.addActionListener(this);
        btnRenShu.setActionCommand("renshu");
        //设置窗口可见
        setVisible(true);
    }


    public static void main(String [] args){
        //创建JFrame对象实例，名称为frm
        //JFrame frm = new JFrame();
//        new GameFrame();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("按钮被点击了");
        String cmd = e.getActionCommand();
        switch (cmd){
            case "huiqi":
                System.out.println("huiqi");
                gp.huiqi();
                break;
            case "baocun":
                System.out.println("baocun");
                save();
                break;
            case "daoru":
                System.out.println("daoru");
                daoru();
                break;
            case "qiuhe":
                System.out.println("qiuhe");
                break;
            case "renshu":
                System.out.println("renshu");
                break;

        }
    }

    private void daoru(){
        JFileChooser chooser = new JFileChooser();
        //chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = chooser.showOpenDialog(null);
        File parent = chooser.getSelectedFile();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream(parent);
            ois = new ObjectInputStream(fis);
            Chess[] chesses = (Chess []) ois.readObject();
            gp.setChesses(chesses);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(ois != null){
                try{
                    ois.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 保存棋谱的方法
     */
    private void save(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = chooser.showOpenDialog(null);
        File parent = chooser.getSelectedFile();
        System.out.println("parent-->"+parent);
        //创建文件
        String path = parent.getAbsolutePath() + File.separator +
                System.currentTimeMillis()+".txt";
        File file = new File(path);
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            //创建文件输出流对象
            fos = new FileOutputStream(file);
            //创建文件对象输出流对象
            oos = new ObjectOutputStream(fos);
            oos.writeObject(gp.getChesses());
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
