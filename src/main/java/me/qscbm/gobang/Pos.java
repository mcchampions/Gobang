package me.qscbm.gobang;

public class Pos {
    private final int x;

    private final int y;

    private final double value;

    public Pos(int x,int y,double value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
