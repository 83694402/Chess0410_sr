//package com.sr_qlp.main;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.io.File;
//
///**
// * @author sr
// * * @date 12:02 2024/4/10
// */
//public class GamePanel_SR_bak extends JPanel {
//    Chess [] chesses = new Chess[32];
//    private Chess selectedChess;
//    //现在的阵营编号
//    private int curPlayer = 0;
//    //无参构造方法
//    public GamePanel_SR_bak(){
//        createChesses();
//        /*  如何操作棋子
//            1、点击棋盘
//            2、如何判断点击的地方是否有棋子
//            3、如何区分第一次选择、重新选择、移动、吃子
//         */
//        //添加点击事件
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                System.out.println("点击的棋子坐标为：x= " + e.getX() + "，y=" + e.getY());
//                Point p = Chess.getPointFromXY(e.getX(),e.getY());
//                System.out.println("点击的网格坐标为："+ p);
//                Chess c = getChessByP(p);
//                //第一次选择棋子
//                if(selectedChess == null) {
//                    selectedChess = c;
//                    if (c.getPlayer() != curPlayer){
//                        selectedChess = null;
//                    }
//                    System.out.println("第一次选择棋子");
//                }else{
//                    //重新选择，移动，吃子
//                    if (c != null){
//                        //第n次点击的时候有棋子
//                        if(c.getPlayer() == selectedChess.getPlayer()){
//                            //重新选择
//                            selectedChess = c;
//                            System.out.println("重新选择");
//                        }else{
//                            //吃子
//                            System.out.println("吃子");
//                            if(selectedChess.isAbleMove(p, GamePanel_SR_bak.this)){
//                                chesses[c.getInitIndex()] = null;
//                                selectedChess.setP(p);
//                            }
//                            overMyTurn();
//                        }
//                    }else{
//                        //第n次点击的时候没有棋子
//                        System.out.println("移动");
//                        if(selectedChess.isAbleMove(p, GamePanel_SR_bak.this)){
//                            selectedChess.setP(p);
//                        }
//                        overMyTurn();
//                    }
//                }
//                System.out.println("点击的棋子对象为:"+selectedChess);
//                repaint();
//            }
//        });
//    }
//    private void overMyTurn(){
//        curPlayer = curPlayer == 0 ? 1:0;
//        selectedChess = null;
//    }
//    /*
//    根据网格坐标查找棋子
//     */
//    public Chess getChessByP(Point p){
//        for (Chess item:chesses){
//            if(item != null && item.getP().equals(p)){
//                return item;
//            }
//        }
//        return null;
//    }
//    private void createChesses(){
//        String [] names = new String[]{"che","ma","xiang","shi","boss",
//                "shi","xiang","ma","che","pao","pao","bing","bing","bing","bing","bing"};
//        Point [] ps = new Point[]{new Point(1,1),new Point(2,1),
//                new Point(3,1),new Point(4,1),new Point(5,1),new Point(6,1),
//                new Point(7,1),new Point(8,1),new Point(9,1),
//                new Point(2,3),new Point(8,3),
//                new Point(1,4),new Point(3,4),new Point(5,4),new Point(7,4),new Point(9,4)};
//        for (int i = 0; i < names.length;i++){
//            Chess c = new Chess(names[i],ps[i],0);
////            c.setName(names[i]);
////            c.setP(ps[i]);
////            c.setPlayer(0);
//            c.setInitIndex(i);
//            chesses[i] = c;
//        }
//
//        for (int i = 0; i < names.length;i++){
//            Chess c = new Chess(names[i],ps[i],1);
////            c.setName(names[i]);
////            c.setP(ps[i]);
////            c.setPlayer(1);
//            c.reverse();
//            c.setInitIndex(i+16);
//            chesses[i+16] = c;
//        }
//    }
//    private void drawChesses(Graphics g){
//        for(Chess item:chesses){//for-each循环
//            if(item != null)
//                item.draw(g,this);
//        }
//    }
//    @Override//重写注解
//    public void paint(Graphics g){
//        //1、准备图片路径
//        String bgPath = "pic"+ File.separator+"qipan.jpg";
//
//        //2、通过图片路径得到图片对象
//        Image bgImg = Toolkit.getDefaultToolkit().getImage(bgPath);
//
//        //3、使用draw.Image方法将图片绘制到面板上
//        g.drawImage(bgImg,0,0,this);
//
//        drawChesses(g);
//        if(selectedChess != null){
//            selectedChess.drawRect(g);
//        }
//    }
//
//}
