package com.sr_qlp.main;

import java.awt.*;

/**
 * @author sr
 * * @date Create at 15:10 2024/4/17
 */
public class Bing extends Chess{
    public Bing(Point p, int player){
        super("bing",p,player);
    }

    public Bing(int px, int player){
        this(new Point(px,4),player);
    }
    @Override
    public boolean isAbleMove(Point tp, GamePanel_SR gamePanel) {
        //没过河之前只能向前走
        if(!isOverRiver(tp)){
            return isAhead(tp) && getStep(tp) == 1 && !isLeftOrRight(tp) && line(tp)>1;
        }else if(isOverRiver(tp)){
            //过河之后可以左右，可以前后
            return isAhead(tp) && getStep(tp) == 1 && line(tp)>1;
        }
        return false;
    }
}
