package me.blog.hgl1002.openwnn.KOKR.keyboard;

/**
 * Base class for items inside keyboard.
 * Created by graphene on 16/07/17.
 */

abstract class Component {
    private int x;
    private int y;
    private int width;
    private int height;
    private int horizontalGap;
    private int verticalGap;
    private int edgeFlags;

    public int getX() {
        return x;
    }

    protected void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    protected void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    protected void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    protected void setHeight(int height) {
        this.height = height;
    }

    public int getHorizontalGap() {
        return horizontalGap;
    }

    protected void setHorizontalGap(int horizontalGap) {
        this.horizontalGap = horizontalGap;
    }

    public int getVerticalGap() {
        return verticalGap;
    }

    protected void setVerticalGap(int verticalGap) {
        this.verticalGap = verticalGap;
    }

    public int getEdgeFlags() {
        return edgeFlags;
    }

    protected void setEdgeFlags(int edgeFlags) {
        this.edgeFlags = edgeFlags;
    }
}
