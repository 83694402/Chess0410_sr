package com.sr_qlp.main;

import java.awt.*;

/**
 * @author sr
 * * @date Create at 15:10 2024/4/17
 */
public class Che extends Chess{
    public Che (Point p,int player){
        super("che",p,player);
    }

    public Che(int px,int player){
        this(new Point(px,1),player);
    }
    @Override
    public boolean isAbleMove(Point tp, GamePanel_SR gamePanel) {
        return line(tp) > 1 && getCount(tp,gamePanel) == 0;
    }
}
