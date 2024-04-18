package com.sr_qlp.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author sr
 * * @date 12:02 2024/4/10
 */
public class GamePanel_SR extends JPanel {
    private Chess [] chesses = new Chess[32];

    public Chess[] getChesses() {
        return chesses;
    }

    public void setChesses(Chess[] chesses) {
        this.chesses = chesses;
        repaint();
    }

    private Chess selectedChess;
    //现在的阵营编号
    private int curPlayer = 0;
    //Java集合的使用
    private LinkedList<Record> huiqiList = new LinkedList();
    //提示label
    private JLabel hintLabel;
    public void setHintLabel(JLabel hintLabel) {
        this.hintLabel = hintLabel;
    }

    //无参构造方法
    public GamePanel_SR(){
        createChesses();
        /*  如何操作棋子
            1、点击棋盘
            2、如何判断点击的地方是否有棋子
            3、如何区分第一次选择、重新选择、移动、吃子
         */
        //添加点击事件
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("点击的棋子坐标为：x= " + e.getX() + "，y=" + e.getY());
                Point p = Chess.getPointFromXY(e.getX(),e.getY());
                System.out.println("点击的网格坐标为："+ p);
                Chess c = getChessByP(p);
                //第一次选择棋子
                if(selectedChess == null) {
                    selectedChess = c;
                    if (selectedChess != null && c.getPlayer() != curPlayer){
                        selectedChess = null;
                        hintLabel.setText("<html>"+"不能选择对方的棋子"+"<br/>"+
                                (curPlayer == 0 ? "红方走":"黑方走")+"</html>");
                    }
                    System.out.println("第一次选择棋子");
                }else{
                    //重新选择，移动，吃子
                    if (c != null){
                        //第n次点击的时候有棋子
                        if(c.getPlayer() == selectedChess.getPlayer()){
                            //重新选择
                            selectedChess = c;
                            System.out.println("重新选择");
                        }else{
                            //吃子
                            System.out.println("吃子");
                            if(selectedChess.isAbleMove(p,GamePanel_SR.this)){
                                chesses[c.getInitIndex()] = null;
                                Record record = new Record();
                                record.setChess(selectedChess);
                                record.setStart(selectedChess.getP());
                                record.setEnd(p);
                                record.setEatedChess(c);
                                huiqiList.add(record);
                                selectedChess.setP(p);
                                overMyTurn();
                            }

                        }
                    }else{
                        //第n次点击的时候没有棋子
                        System.out.println("移动");
                        if(selectedChess.isAbleMove(p,GamePanel_SR.this)){
                            Record record = new Record();
                            record.setChess(selectedChess);
                            record.setStart(selectedChess.getP());
                            record.setEnd(p);
                            record.setEatedChess(c);
                            huiqiList.add(record);
                            selectedChess.setP(p);
                            overMyTurn();
                        }

                    }
                }
                System.out.println("点击的棋子对象为:"+selectedChess);
                System.out.println("棋子数据记录合集"+huiqiList);
                repaint();
            }
        });
    }
    private void overMyTurn(){
        curPlayer = curPlayer == 0 ? 1:0;
        selectedChess = null;
        hintLabel.setText(curPlayer == 0 ? "红方走":"黑方走");
    }
    /*
    根据网格坐标查找棋子
     */
    public Chess getChessByP(Point p){
        for (Chess item:chesses){
            if(item != null && item.getP().equals(p)){
                return item;
            }
        }
        return null;
    }
    private void createChesses(){
        String [] names = new String[]{"che","ma","xiang","shi","boss",
                "shi","xiang","ma","che","pao","pao","bing","bing","bing","bing","bing"};
        int [] xs = {1,2,3,4,5,6,7,8,9,2,8,1,3,5,7,9};
        for (int i = 0; i < names.length;i++){
            Chess c = ChessFactory.create(names[i],0,xs[i]);
//            c.setName(names[i]);
//            c.setP(ps[i]);
//            c.setPlayer(0);
            c.setInitIndex(i);
            chesses[i] = c;
        }

        for (int i = 0; i < names.length;i++){
            Chess c = ChessFactory.create(names[i],1,xs[i]);
//            c.setName(names[i]);
//            c.setP(ps[i]);
//            c.setPlayer(1);
            c.reverse();
            c.setInitIndex(i+16);
            chesses[i+16] = c;
        }
    }
    private void drawChesses(Graphics g){
        for(Chess item:chesses){//for-each循环
            if(item != null)
                item.draw(g,this);
        }
    }
    @Override//重写注解
    public void paint(Graphics g){
        //1、准备图片路径
        String bgPath = "pic"+ File.separator+"qipan.jpg";

        //2、通过图片路径得到图片对象
        Image bgImg = Toolkit.getDefaultToolkit().getImage(bgPath);

        //3、使用draw.Image方法将图片绘制到面板上
        g.drawImage(bgImg,0,0,this);

        drawChesses(g);
        if(selectedChess != null){
            selectedChess.drawRect(g);
        }
    }

    /*
    *
    * 实现悔棋功能aa
     */

    public void huiqi(){
        Record record = huiqiList.pollLast();
        //将操作棋子的坐标修改回去
        record.getChess().setP(record.getStart());
        //将chess数组的棋子恢复
        chesses[record.getChess().getInitIndex()] = record.getChess();
        if(record.getEatedChess() != null){
            chesses[record.getEatedChess().getInitIndex()] = record.getEatedChess();
        }
        curPlayer = 1 - record.getChess().getPlayer();
        overMyTurn();
        //刷新棋盘
        repaint();
    }

}
