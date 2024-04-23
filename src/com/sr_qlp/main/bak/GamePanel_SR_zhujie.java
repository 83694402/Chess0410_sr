package com.sr_qlp.main.bak;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * @author sr
 * * @date 12:02 2024/4/10
 */
public class GamePanel_SR_zhujie extends JPanel {

    @Override//重写注解
    public void paint(Graphics g){
    /*
    * 常用方法：
    *   g.drawImage:画图片
    *   g.drawChars:画文字
    *   g.drawLine:画直线
    *   g.drawOval:画圆或椭圆
    *
    * 如何在JPanel画一张图
    * 1、准备图片路径
    * 2、通过图片路径得到图片对象
    * 3、使用g.drawImage方法将图片绘制到面板上
    * */
        //1、准备图片路径
        String bgPath = "pic"+ File.separator+"qipan.jpg";

        //2、通过图片路径得到图片对象
        Image bgImg = Toolkit.getDefaultToolkit().getImage(bgPath);

        //3、使用draw.Image方法将图片绘制到面板上
        g.drawImage(bgImg,0,0,this);

        String [] names = new String[]{"che","ma","xiang","shi","boss",
                "shi","xiang","ma","che","pao","pao","bing","bing","bing","bing","bing"};
        String suffix = ".png";
        int margin = 20;//边距
        int size = 30;//棋子大小
        int space = 40;//棋子间距
        Point [] ps = new Point[]{new Point(1,1),new Point(2,1),
                new Point(3,1),new Point(4,1),new Point(5,1),new Point(6,1),
                new Point(7,1),new Point(8,1),new Point(9,1),
                new Point(2,3),new Point(8,3),
                new Point(1,4),new Point(3,4),new Point(5,4),new Point(7,4),new Point(9,4)};
        for (int i = 0; i < names.length;i++){

        }

        for (int i = 0; i < names.length;i++){

        }


    }

}
