package com.sr_qlp.main;

/**
 * @author sr
 * * @date Create at 15:35 2024/4/17
 */
public class ChessFactory {
    private ChessFactory(){

    }
    public static Chess create(String name,int player,int px){
        if("boss".equals(name)){
            return new Boss(px,player);
        }else if("shi".equals(name)){
            return new Shi(px,player);
        }else if("xiang".equals(name)){
            return new Xiang(px,player);
        }else if("ma".equals(name)){
            return new Ma(px,player);
        }else if("che".equals(name)){
            return new Che(px,player);
        }else if("pao".equals(name)){
            return new Pao(px,player);

        }else if("bing".equals(name)){
            return new Bing(px,player);
        }
        return null;
    }

}
