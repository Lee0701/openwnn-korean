package me.blog.hgl1002.openwnn.KOKR.inputmethod.impl;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class KeyLabels {

	Map<Integer, Pair<String, String>> labels;

	public KeyLabels() {
		labels = new HashMap<>();
	}

	public KeyLabels(Map<Integer, Pair<String, String>> labels) {
		this.labels = labels;
	}

	public String get(int keyCode, boolean shift) {
		if(labels == null) return null;
		Pair<String, String> pair = labels.get(keyCode);
		return shift ? pair.second : pair.first;
	}

}
