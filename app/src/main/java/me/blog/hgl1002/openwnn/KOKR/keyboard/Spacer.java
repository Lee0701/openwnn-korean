package me.blog.hgl1002.openwnn.KOKR.keyboard;

/**
 * Used as a placeholder inside the KeyboardView.
 * Created by graphene on 16/07/17.
 */

public class Spacer extends Component {
    private final InputDelegation delegateTo;

    public Spacer(int x, int y, int width, int height, int horizontalGap, int edgeFlags,
                  InputDelegation delegateTo) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setHorizontalGap(horizontalGap);
        setEdgeFlags(edgeFlags);
        this.delegateTo = delegateTo;
    }

    public InputDelegation getDelegateTo() {
        return delegateTo;
    }

    enum InputDelegation {TO_LEFT, TO_RIGHT, TO_BOTH}
}
