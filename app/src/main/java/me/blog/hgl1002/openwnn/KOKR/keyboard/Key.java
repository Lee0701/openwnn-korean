package me.blog.hgl1002.openwnn.KOKR.keyboard;


import java.util.EnumMap;

/**
 * Defines pressable key.
 * Created by graphene on 16/07/17.
 */

public class Key extends Component {
    private final EnumMap<KeyEventType, KeyCode> normalKeyCodes;
    private final EnumMap<KeyEventType, KeyCode> shiftKeyCodes;
    private final EnumMap<KeyEventType, KeyCode> altKeyCodes;
    private final KeyOptions keyOptions;

    public Key(int x, int y, int width, int height, int horizontalGap, int edgeFlags,
               EnumMap<KeyEventType, KeyCode> normalKeyCodes, EnumMap<KeyEventType, KeyCode> shiftKeyCodes, EnumMap<KeyEventType, KeyCode> altKeyCodes, KeyOptions keyOptions) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setHorizontalGap(horizontalGap);
        setEdgeFlags(edgeFlags);
        this.normalKeyCodes = normalKeyCodes;
        this.shiftKeyCodes = shiftKeyCodes;
        this.altKeyCodes = altKeyCodes;
        this.keyOptions = keyOptions;
    }

    public EnumMap<KeyEventType, KeyCode> getKeyCodes(KeyState keyState) {
        EnumMap<KeyEventType, KeyCode> keyCodes = new EnumMap<>(KeyEventType.class);
        switch (keyState) {
            case NORMAL:
                keyCodes = normalKeyCodes;
                break;
            case SHIFT:
                keyCodes = shiftKeyCodes;
                break;
            case ALT:
                keyCodes = altKeyCodes;
                break;
        }
        return keyCodes;
    }

    public KeyOptions getKeyOptions() {
        return keyOptions;
    }
}
