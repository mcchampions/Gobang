package me.qscbm.gobang;

public class Pos {
    private final int x;

    private final int y;

    private final int value;

    public Pos(int x,int y,int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
