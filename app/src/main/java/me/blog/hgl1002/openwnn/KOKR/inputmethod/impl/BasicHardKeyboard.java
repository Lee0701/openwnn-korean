package me.blog.hgl1002.openwnn.KOKR.inputmethod.impl;

import android.util.Pair;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;

import java.util.HashMap;

import me.blog.hgl1002.openwnn.KOKR.inputmethod.CharacterGenerator;
import me.blog.hgl1002.openwnn.KOKR.inputmethod.HardKeyboard;
import me.blog.hgl1002.openwnn.OpenWnnKOKR;

public class BasicHardKeyboard implements HardKeyboard, CharacterGenerator.CharacterGeneratorListener {

	protected OpenWnnKOKR parent;

	protected KeyMappings mappings;

	protected CharacterGenerator generator;

	public BasicHardKeyboard(OpenWnnKOKR parent) {
		this.parent = parent;
		// Temporary code for testing.
		generator = new BasicCharacterGenerator();
		generator.addEventListener(this);
		mappings = new KeyMappings(new HashMap<Integer, Pair<Long, Long>>() {{
			put(29, new Pair<Long, Long>(0x0003000100000000L, 0L));
			put(30, new Pair<Long, Long>(0x0003000000010000L, 0L));
			put(31, new Pair<Long, Long>(0x0003000000000001L, 0L));
			put(32, new Pair<Long, Long>(0x0003000000000002L, 0L));
		}});
	}

	@Override
	public boolean onKeyEvent(KeyEvent event, boolean softKey) {
		InputConnection ic = parent.getCurrentInputConnection();

		if(event.getAction() != KeyEvent.ACTION_DOWN) return true;

		boolean shift = event.isShiftPressed();

		int code = event.getKeyCode();
		if(mappings == null) {
			ic.commitText(code + "", 1);
		}
		long jamoCode = mappings.get(code, shift);
		if(generator == null) {
			ic.commitText(new BasicCodeSystem().convertToUnicode(jamoCode) + "", 1);
		}

		generator.onJamoCode(jamoCode);

		return true;
	}

	@Override
	public void onCompose(String composing) {
		parent.getCurrentInputConnection().setComposingText(composing, 1);
	}

	@Override
	public void onCommit(String composing) {
		parent.getCurrentInputConnection().finishComposingText();
	}

	/**
	 * @see #mappings
	 */
	public KeyMappings getMappings() {
		return mappings;
	}

	/**
	 * @see #mappings
	 */
	public void setMappings(KeyMappings mappings) {
		this.mappings = mappings;
	}

	/**
	 * @see #generator
	 */
	public CharacterGenerator getGenerator() {
		return generator;
	}

	/**
	 * @see #generator
	 */
	public void setGenerator(CharacterGenerator generator) {
		this.generator = generator;
	}

}
