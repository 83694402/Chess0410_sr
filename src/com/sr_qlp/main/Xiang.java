package com.sr_qlp.main;

import java.awt.*;

/**
 * @author sr
 * * @date Create at 15:10 2024/4/17
 */
public class Xiang extends Chess{
    public Xiang(Point p, int player){
        super("xiang",p,player);
    }

    public Xiang(int px, int player){
        this(new Point(px,1),player);
    }
    @Override
    public boolean isAbleMove(Point tp, GamePanel_SR gamePanel) {
        return line(tp) == 1 && getStep(tp) == 2 && !isOverRiver(tp) && !isBieJiao(tp,gamePanel);
    }
}
