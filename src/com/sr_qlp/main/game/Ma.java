package com.sr_qlp.main.game;

import java.awt.*;

/**
 * @author sr
 * * @date Create at 15:10 2024/4/17
 */
public class Ma extends Chess{
    public Ma(Point p, int player){
        super("ma",p,player);
    }

    public Ma(int px, int player){
        this(new Point(px,1),player);
    }
    @Override
    public boolean isAbleMove(Point tp, GamePanel_SR gamePanel) {
        return (line(tp) == 0 || line(tp) == -1) && !isBieJiao(tp,gamePanel);
    }
}
