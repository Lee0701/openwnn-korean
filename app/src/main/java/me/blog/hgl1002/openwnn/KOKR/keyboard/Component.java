package me.blog.hgl1002.openwnn.KOKR.keyboard;

/**
 * Base class for items inside keyboard.
 * Created by graphene on 16/07/17.
 */

abstract class Component {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final int horizontalGap;
    private final int edgeFlags;

    Component(int x, int y, int width, int height, int horizontalGap, int edgeFlags) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.horizontalGap = horizontalGap;
        this.edgeFlags = edgeFlags;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getHorizontalGap() {
        return horizontalGap;
    }

    public int getEdgeFlags() {
        return edgeFlags;
    }
}
