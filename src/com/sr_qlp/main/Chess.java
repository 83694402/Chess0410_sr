package com.sr_qlp.main;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.Serializable;

/**
 * @author sr
 * * @date Create at 14:33 2024/4/12
 */
public abstract class Chess implements Serializable {
    //棋子边距
    private static final int MARGIN = 20;
    //棋子大小
    private static final int SIZE = 30;
    //棋子间距
    private static final int SPACE = 40;
    //棋子绘制时的具体坐标
    private int x,y;
    //棋子的初始序号
    private int initIndex;
    //设置initIndex
    public void setInitIndex(int initIndex) {
        this.initIndex = initIndex;
    }
    //获取initIndex
    public int getInitIndex() {
        return initIndex;
    }

    //棋子阵营，0：红，1：黑
    private int player;
    public void setPlayer(int player){
        this.player = player;
    }
    public int getPlayer(){
        return this.player;
    }
    //图片名称
    private String name;
    public void setName(String name){
        this.name = name;
    }
    //棋子的网格坐标
    private Point p;
    private Point initP;
    public void setP(Point p){
        this.p = (Point)p.clone();
        if(initP == null){
            initP = this.p;
        }
        calcXY();
    }
    public Chess (){

    }
    public Chess(String name,Point p,int player){
        this.setName(name);
        this.setP(p);
        this.setPlayer(player);
    }
    private String suffix = ".png";
    public void draw(Graphics g, JPanel panel){
        String path = "pic"+ File.separator + name + player +suffix;
        Image img = Toolkit.getDefaultToolkit().getImage(path);
        g.drawImage(img,x,y,SIZE,SIZE,panel);
    }
    private void calcXY(){
        x = MARGIN-SIZE/2 + SPACE*(p.x-1);
        y = MARGIN-SIZE/2 + SPACE*(p.y-1);
    }
    public static Point getPointFromXY(int x,int y){
        Point p = new Point();
        p.x = (x-MARGIN+SIZE/2) / SPACE + 1;
        p.y = (y-MARGIN+SIZE/2) / SPACE + 1;
        if(p.x < 1 || p.x >9 || p.y < 1 || p.y >10)
            return null;
        return p;
    }
    public void reverse(){
        p.x = 10-p.x;
        p.y = 11-p.y;
        initP = p;
        calcXY();
    }

    public Point getP() {
        return p;
    }

    //给选中的棋子绘制矩形边框
    public void drawRect(Graphics g){
        g.drawRect(this.x,this.y,SIZE,SIZE);
    }

    //判断是走直线还是斜线或者都不是
    //1：正斜线，2：y轴直线，3：x轴直线,-2:都不是,0:日字是x轴日字，-1：y轴日字
    public int line(Point tp){
        if(Math.abs(tp.y - p.y) == Math.abs(tp.x - p.x)){
            //正斜线
            return 1;
        }
        else if(tp.x == p.x){
           //y
            return 2;
        }else if(tp.y == p.y){
            //x
            return 3;
        }else{
            //日字
            //横向的日字
            if(Math.abs(tp.x - p.x) == 2 && Math.abs(tp.y - p.y) == 1){
                //x
                return 0;
            }else if(Math.abs(tp.x - p.x) == 1 && Math.abs(tp.y - p.y) == 2){
                //y
                return -1;
            }
        }
        return -2;
    }
    //计算起点到目标点之间的步数
    public int getStep(Point tp){
        int line = line(tp);
        if(line == 1 || line == 2){
            //正斜线和y轴直线
            return Math.abs(p.y-tp.y);
        }else if(line == 3){
            //x
            return Math.abs(p.x-tp.x);
        }
        return 0;
    }
    //判断棋子能否移动
    public abstract boolean isAbleMove(Point tp,GamePanel_SR gamePanel);{
//        if("boss".equals(name)){
//            return line(tp) > 1 && isHome(tp) && getStep(tp) == 1;
//        }else if("shi".equals(name)){
//            return line(tp) == 1 && isHome(tp) && getStep(tp) == 1;
//        }else if("xiang".equals(name)){
//            return line(tp) == 1 && getStep(tp) == 2 && !isOverRiver(tp) && !isBieJiao(tp,gamePanel);
//        }else if("ma".equals(name)){
//            return (line(tp) == 0 || line(tp) == -1) && !isBieJiao(tp,gamePanel);
//        }else if("che".equals(name)){
//            return line(tp) > 1 && getCount(tp,gamePanel) == 0;
//        }else if("pao".equals(name)){
//            //目标点point对象
//            Chess chessTmp = gamePanel.getChessByP(tp);
//            if(chessTmp != null){
//                //吃子
//                if(chessTmp.getPlayer() != this.player)
//                    return line(tp) > 1 && getCount(tp,gamePanel) == 1;
//            }else{
//                //移动
//                return line(tp) > 1 && getCount(tp,gamePanel) == 0;
//            }
//
//        }else if("bing".equals(name)){
//            //没过河之前只能向前走
//            if(!isOverRiver(tp)){
//                return isAhead(tp) && getStep(tp) == 1 && !isLeftOrRight(tp) && line(tp)>1;
//            }else if(isOverRiver(tp)){
//                //过河之后可以左右，可以前后
//                return isAhead(tp) && getStep(tp) == 1 && line(tp)>1;
//
//            }
//            return true;
//        }
//        return false;
    }
    //判断是否朝前走

    public boolean isAhead(Point tp){
        //如果是上面的兵
        if(isUpOrDown() == 1){
            return (tp.y - this.getP().y) >= 0;
        }else if (isUpOrDown() == 2){
            //如果是下面的兵
            return (tp.y - this.getP().y) <= 0;
        }
        return false;
    }

    //判断是否左右走
    public boolean isLeftOrRight(Point tp){
        return (Math.abs(tp.x-this.getP().x)) > 0;
    }

    //判断棋子是否在皇宫内
    public boolean isHome(Point tp){
        if(tp.x < 4 || tp.x > 6) {
            return false;
        }
        int upOrDown = isUpOrDown();
        //判断棋子属于上面还是属于下面
        if(upOrDown == 1){
            //上面
            if(tp.y < 1 || tp.y > 3) {
                return false;
            }
        }else if (upOrDown == 2){
            //下面
            if(tp.y < 8 || tp.y > 10){
                return false;
            }
        }
        return true;
    }

    //判断棋子在上面还是在下面，上面返回1，下面返回2
    public int isUpOrDown(){
        if(initP.y < 6){
            //棋盘上面
            return 1;
        }else if(initP.y > 5){
            //棋盘下面
            return 2;
        }
        //其他
        return 0;
    }

    //判断是否蹩脚
    /**
     * 判断是否蹩脚
     * @param tp 鼠标点击的点的坐标
     * @param gamePanel 传入游戏面板，利用游戏面板的getChessByP方法
     * @return
     */
    public boolean isBieJiao(Point tp,GamePanel_SR gamePanel){
        Point center = new Point();
        if("xiang".equals(name)) {
            center.x = (p.x + tp.x) / 2;
            center.y = (p.y + tp.y) / 2;
            return gamePanel.getChessByP(center) != null;
        }else if("ma".equals(name)){
            int line = line(tp);
            if(line == 0){
                //横向的日字
                //x
                center.x = (p.x + tp.x)/2;
                center.y = p.y;
            }else if (line == -1){
                //y
                center.y = (p.y + tp.y) /2;
                center.x = p.x;
            }
            return gamePanel.getChessByP(center) != null;
        }
        return false;
    }
    //判断是否过河
    public boolean isOverRiver(Point tp){
        //判断棋子原本位置
        //原本在上面
        if(isUpOrDown() == 1){
            if(tp.y < 6){
                return false;
            }
        }else if(isUpOrDown() == 2){
            //原本在下面
            if(tp.y > 5)
                return false;
        }
        return true;

    }

    //计算棋子和移动点之间的棋子数
    public int getCount(Point tp,GamePanel_SR gamePanel){
        int start = 0;
        int end = 0;
        int count = 0;
        Point np = new Point();
        //横向移动
        if(p.y == tp.y){
            //从左向右移动
            if(tp.x > p.x){
                start = p.x + 1;
                end = tp.x;
            }else if(tp.x <p.x){
                //从右向左移动
                start = tp.x+1;
                end = p.x;
            }
            System.out.println(start+","+end);
            for (int i = start; i < end; i++){
                np.x = i;
                np.y = p.y;
                if(gamePanel.getChessByP(np) != null){
                    count++;
                }
            }
        }else if (tp.x == p.x){
            //从上向下移动
            if(tp.y > p.y){
                start = p.y + 1;
                end = tp.y;
            }else if(tp.y <p.y){
                //从下向上移动
                start = tp.y+1;
                end = p.y;
            }
            System.out.println(start+" "+end);
            for (int i = start; i < end; i++){
                np.x = p.x;
                np.y = i;
                if(gamePanel.getChessByP(np) != null){
                    count++;
                }
            }

        }
        System.out.println("棋子数"+count);
        return count;
    }

    @Override
    public String toString() {
        return "Chess{" +
                "x=" + x +
                ", y=" + y +
                ", initIndex=" + initIndex +
                ", player=" + player +
                ", name='" + name + '\'' +
                ", p=" + p +
                ", initP=" + initP +
                ", suffix='" + suffix + '\'' +
                '}';
    }
}
