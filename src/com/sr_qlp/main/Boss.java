package com.sr_qlp.main;

import java.awt.*;

/**
 * @author sr
 * * @date Create at 15:10 2024/4/17
 */
public class Boss extends Chess{
    public Boss(Point p, int player){
        super("boss",p,player);
    }

    public Boss(int px, int player){
        this(new Point(px,1),player);
    }
    @Override
    public boolean isAbleMove(Point tp, GamePanel_SR gamePanel) {
        return line(tp) > 1 && isHome(tp) && getStep(tp) == 1;
    }
}
