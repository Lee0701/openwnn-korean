import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.RequiresApi;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import me.blog.hgl1002.openwnn.KOKR.layout.LayoutGongSebul;

@RequiresApi(api = Build.VERSION_CODES.N)
public class LayoutExporter {

    private Map<Integer, Integer> keyCodeMap = new HashMap<Integer, Integer>() {{
        put(0x20, 62);
        put(0x21, 8);
        put(0x22, 75);
        put(0x23, 10);
        put(0x24, 11);
        put(0x25, 12);
        put(0x26, 14);
        put(0x27, 75);
        put(0x28, 16);
        put(0x29, 7);
        put(0x2a, 15);
        put(0x2b, 78);
        put(0x2c, 55);
        put(0x2d, 69);
        put(0x2e, 56);
        put(0x2f, 76);

        put(0x3a, 74);
        put(0x3b, 74);
        put(0x3c, 55);
        put(0x3d, 78);
        put(0x3e, 56);
        put(0x3f, 76);

        put(0x40, 9);
        put(0x5b, 71);
        put(0x5c, 73);
        put(0x5d, 72);
        put(0x5e, 13);
        put(0x5f, 69);

        put(0x60, 68);
        put(0x7b, 71);
        put(0x7c, 73);
        put(0x7d, 72);
        put(0x7e, 68);
    }};

    public int convertKeycode(int keyCode) {
        if(keyCode >= '0' && keyCode <= '9') return keyCode - '0' + KeyEvent.KEYCODE_0;
        if(keyCode >= 'a' && keyCode <= 'z') return keyCode - 'a' + KeyEvent.KEYCODE_A;
        if(keyCodeMap.containsKey(keyCode)) return keyCodeMap.get(keyCode);
        return 0;
    }

    public String exportJamo(int[][] table) {
        return Arrays.stream(table).map((entry) -> {
            int keyCode = entry[0];
            int base = entry[1];
            int shifted = entry[2];
            String comment = String.format("%c, %c", base, shifted);
            String name = KeyEvent.keyCodeToString(convertKeycode(keyCode));
            return String.format("KeyEvent.%s to Entry(0x%04x, 0x%04x), // %s", name, base, shifted, comment);
        }).collect(Collectors.joining("\n"));
    }

    @Test
    public void main() {
        LayoutExporter exporter = new LayoutExporter();
        String result = exporter.exportJamo(LayoutGongSebul.JAMO_SEBUL_391);
        Log.d("output", "\n" + result);
    }
}
