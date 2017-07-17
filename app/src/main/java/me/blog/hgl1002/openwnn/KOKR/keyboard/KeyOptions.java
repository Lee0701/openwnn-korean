package me.blog.hgl1002.openwnn.KOKR.keyboard;

/**
 * Defines key options (e.g. vibration).
 * Created by graphene on 16/07/17.
 */

public class KeyOptions {
    private final boolean isRepeatable;
    private final boolean isModifier;

    public KeyOptions(boolean isRepeatable, boolean isModifier) {
        this.isRepeatable = isRepeatable;
        this.isModifier = isModifier;
    }

    public boolean isRepeatable() {
        return isRepeatable;
    }

    public boolean isModifier() {
        return isModifier;
    }
}
