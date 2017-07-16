package me.blog.hgl1002.openwnn.KOKR.keyboard;


import java.util.List;

/**
 * Defines pressable key.
 * Created by graphene on 16/07/17.
 */

public final class Key extends Component {
    private final List<KeyState> keyStates;
    private final boolean isRepeatable;
    private final boolean isModifier;

    public Key(int x, int y, int width, int height, int horizontalGap, int edgeFlags,
               List<KeyState> keyStates, boolean isRepeatable, boolean isModifier) {
        super(x, y, width, height, horizontalGap, edgeFlags);
        this.keyStates = keyStates;
        this.isRepeatable = isRepeatable;
        this.isModifier = isModifier;
    }

    public List<KeyState> getKeyStates() {
        return keyStates;
    }

    public boolean isRepeatable() {
        return isRepeatable;
    }

    public boolean isModifier() {
        return isModifier;
    }
}
