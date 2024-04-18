package com.sr_qlp.main;

import java.awt.*;

/**
 * @author sr
 * * @date Create at 15:10 2024/4/17
 */
public class Pao extends Chess{
    public Pao(Point p, int player){
        super("pao",p,player);
    }

    public Pao(int px, int player){
        this(new Point(px,3),player);
    }
    @Override
    public boolean isAbleMove(Point tp, GamePanel_SR gamePanel) {
        //目标点point对象
        Chess chessTmp = gamePanel.getChessByP(tp);
        if(chessTmp != null){
            //吃子
            if(chessTmp.getPlayer() != this.getPlayer())
                return line(tp) > 1 && getCount(tp,gamePanel) == 1;
        }else{
            //移动
            return line(tp) > 1 && getCount(tp,gamePanel) == 0;
        }
        return false;
    }
}
