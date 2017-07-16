package me.blog.hgl1002.openwnn.KOKR.keyboard;

/**
 * Used as a placeholder inside the KeyboardView.
 * Created by graphene on 16/07/17.
 */

public final class Spacer extends Component {
    private final InputDelegation delegateTo;

    public Spacer(int x, int y, int width, int height, int horizontalGap, int edgeFlags,
                  InputDelegation delegateTo) {
        super(x, y, width, height, horizontalGap, edgeFlags);
        this.delegateTo = delegateTo;
    }

    public InputDelegation getDelegateTo() {
        return delegateTo;
    }

    enum InputDelegation {TO_LEFT, TO_RIGHT, TO_BOTH}
}
