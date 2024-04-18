package com.sr_qlp.main;

import java.awt.*;

/**
 * @author sr
 * * @date Create at 9:15 2024/4/18
 */
public class Record {
    //记录当前的棋子
    private Chess chess;
    //记录起始位置
    private Point start;
    //记录结束位置
    private Point end;
    //记录吃子
    private Chess eatedChess;
    public Record(){

    }

    public Record(Chess chess, Point start, Point end) {
        this.chess = chess;
        this.start = start;
        this.end = end;
    }

    public Record(Chess chess, Point start, Point end, Chess eatedChess) {
        this.chess = chess;
        this.start = start;
        this.end = end;
        this.eatedChess = eatedChess;
    }

    public Chess getChess() {
        return chess;
    }

    public void setChess(Chess chess) {
        this.chess = chess;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public Chess getEatedChess() {
        return eatedChess;
    }

    public void setEatedChess(Chess eatedChess) {
        this.eatedChess = eatedChess;
    }

    @Override
    public String toString() {
        return "Record{" +
                "chess=" + chess +
                ", start=" + start +
                ", end=" + end +
                ", eatChess=" + eatedChess +
                '}'+"\n";
    }
}
