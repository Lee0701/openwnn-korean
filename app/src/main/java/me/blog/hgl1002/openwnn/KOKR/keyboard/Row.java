package me.blog.hgl1002.openwnn.KOKR.keyboard;

import java.util.List;

/**
 * Defines row inside the Keyboard class.
 * Created by graphene on 16/07/17.
 */

public class Row extends Component {
    private final List<? extends Component> keyList;

    Row(int height, int verticalGap, List<? extends Component> keyList) {
        this.keyList = keyList;
        setHeight(height);
        setVerticalGap(verticalGap);
    }

    public List<? extends Component> getKeyList() {
        return keyList;
    }
}
