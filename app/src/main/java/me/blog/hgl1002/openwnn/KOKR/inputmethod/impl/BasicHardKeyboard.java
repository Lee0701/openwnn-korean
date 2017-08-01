package me.blog.hgl1002.openwnn.KOKR.inputmethod.impl;

import android.util.Pair;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;

import java.util.HashMap;

import me.blog.hgl1002.openwnn.KOKR.inputmethod.CharacterGenerator;
import me.blog.hgl1002.openwnn.KOKR.inputmethod.HardKeyboard;
import me.blog.hgl1002.openwnn.OpenWnnKOKR;

import static me.blog.hgl1002.openwnn.KOKR.inputmethod.impl.BasicCodeSystem.*;

public class BasicHardKeyboard implements HardKeyboard, CharacterGenerator.CharacterGeneratorListener {

	public static final long[][] MAPPING_SEBEOL_FINAL = {
			{},		// 0	KEYCODE_UNKNOWN
			{},		// 1	KEYCODE_MENU
			{},		// 2	KEYCODE_SOFT_RIGHT
			{},		// 3	KEYCODE_HOME
			{},		// 4	KEYCODE_BACK
			{},		// 5	KEYCODE_CALL
			{},		// 6	KEYCODE_ENDCALL
			{H3|K_, '~'},		// 7
			{H3|_H, H3|_GG},		// 8
			{H3|_SS, H3|_RG},		// 9
			{H3|_B, H3|_J},		// 10
			{H3|YO, H3|_RP},		// 11
			{H3|YU, H3|_RT},		// 12
			{H3|YA, '='},		// 13
			{H3|YE, '“'},		// 14
			{H3|EUI, '”'},		// 15
			{H3|U_, '\''},		// 16
			{},		// 17
			{},		// 18
			{},		// 19
			{},		// 20
			{},		// 21
			{},		// 22
			{},		// 23
			{},		// 24
			{},		// 25
			{},		// 26
			{},		// 27
			{},		// 28
			{H3|_Q, H3|_D},
			{H3|U_, '?'},
			{H3|E_, H3|_K},
			{H3|I_, H3|_RB},
			{H3|YEO, H3|_NJ},
			{H3|A_, H3|_RM},
			{H3|EU, H3|YE},
			{H3|N_, '0'},
			{H3|M_, '7'},
			{H3|Q_, '1'},
			{H3|G_, '2'},
			{H3|J_, '3'},
			{H3|H_, '"'},
			{H3|S_, '-'},
			{H3|C_, '8'},
			{H3|P_, '9'},
			{H3|_S, H3|_P},
			{H3|AE, H3|_RH},
			{H3|_N, H3|_NH},
			{H3|EO, H3|_RS},
			{H3|D_, '6'},
			{H3|O_, H3|_GS},
			{H3|_R, H3|_T},
			{H3|_G, H3|_BS},
			{H3|R_, '5'},
			{H3|_M, H3|_C},
	};

	protected OpenWnnKOKR parent;

	protected KeyMappings mappings;

	protected CharacterGenerator generator;

	public BasicHardKeyboard(OpenWnnKOKR parent) {
		this.parent = parent;
		// Temporary code for testing.
		generator = new BasicCharacterGenerator();
		generator.addEventListener(this);
		mappings = new KeyMappings(MAPPING_SEBEOL_FINAL);
	}

	@Override
	public void onInit() {
		generator.onInit();
	}

	@Override
	public void onDestroy() {
		generator.onDestroy();
	}

	@Override
	public boolean onKeyEvent(KeyEvent event, boolean softKey) {
		InputConnection ic = parent.getCurrentInputConnection();

		if(event.getAction() != KeyEvent.ACTION_DOWN) return true;

		boolean shift = event.isShiftPressed();

		int code = event.getKeyCode();

		switch(code) {
		case KeyEvent.KEYCODE_DEL:
			if(!generator.onBackspace(0)) {
				ic.deleteSurroundingText(1, 0);
			}
			return true;
		}

		if(mappings == null) {
			return false;
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
