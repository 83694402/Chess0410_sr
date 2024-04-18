package com.sr_qlp.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author sr
 * * @date 2024/4/10
 */
public class MainFrame_SR extends JFrame implements ActionListener {
    //gamePanel
    private GamePanel_SR gp = new GamePanel_SR();

    public MainFrame_SR(){
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
        new MainFrame_SR();

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
                break;
            case "daoru":
                System.out.println("daoru");
                break;
            case "qiuhe":
                System.out.println("qiuhe");
                break;
            case "renshu":
                System.out.println("renshu");
                break;

        }
    }
}
