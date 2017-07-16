package me.blog.hgl1002.openwnn.KOKR.inputmethod.impl;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class KeyMappings {

	protected Map<Integer, Pair<Long, Long>> mappings;

	public KeyMappings() {
		this.mappings = new HashMap<>();
	}

	public KeyMappings(Map<Integer, Pair<Long, Long>> mappings) {
		this.mappings = mappings;
	}

	public long get(int keyCode, boolean shift) {
		if(mappings == null || mappings.get(keyCode) == null) return 0;
		Pair<Long, Long> pair = mappings.get(keyCode);
		return shift ? pair.second : pair.first;
	}

}
