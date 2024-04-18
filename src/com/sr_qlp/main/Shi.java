package com.sr_qlp.main;

import java.awt.*;

/**
 * @author sr
 * * @date Create at 15:10 2024/4/17
 */
public class Shi extends Chess{
    public Shi(Point p, int player){
        super("shi",p,player);
    }

    public Shi(int px, int player){
        this(new Point(px,1),player);
    }
    @Override
    public boolean isAbleMove(Point tp, GamePanel_SR gamePanel) {
        return line(tp) == 1 && isHome(tp) && getStep(tp) == 1;
    }
}
