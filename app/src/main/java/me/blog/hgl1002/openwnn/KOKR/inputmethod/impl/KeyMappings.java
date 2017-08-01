package me.blog.hgl1002.openwnn.KOKR.inputmethod.impl;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class KeyMappings {

	protected long[][] mappings;

	public KeyMappings() {
		this.mappings = new long[0x100][2];
	}

	public KeyMappings(long[][] mappings) {
		this.mappings = mappings;
	}

	public long get(int keyCode, boolean shift) {
		if(mappings == null) return 0;
		try {
			return shift ? mappings[keyCode][1] : mappings[keyCode][0];
		} catch(ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

}
