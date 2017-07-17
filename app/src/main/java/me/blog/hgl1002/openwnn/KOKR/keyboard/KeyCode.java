package me.blog.hgl1002.openwnn.KOKR.keyboard;

/**
 * Defines value of {@code Key} when pressed in certain {@code KeyEventType}.
 * Created by graphene on 17/07/17.
 */

public class KeyCode {
    public static final long INVALID_KEY_CODE = Long.MIN_VALUE;
    private final long keyCode;

    public KeyCode(long keyCode) {
        this.keyCode = keyCode;
    }

    public long getKeyCode() {
        return keyCode;
    }
}
