package me.blog.hgl1002.openwnn.KOKR.inputmethod.impl;

import java.util.ArrayList;
import java.util.List;

import me.blog.hgl1002.openwnn.KOKR.inputmethod.CharacterGenerator;

import static me.blog.hgl1002.openwnn.KOKR.inputmethod.impl.BasicCodeSystem.*;

public class BasicCharacterGenerator implements CharacterGenerator {

	protected JamoHandler jamoHandler;

	protected long syllable;

	protected List<CharacterGeneratorListener> listeners = new ArrayList<>();

	@Override
	public boolean onJamoCode(long jamoCode) {

		if((jamoCode & MASK_CODE_TYPE) == CODE_HANGUL_3BEOL) {
			jamoCode = jamoCode & MASK_CODE_VALUE;

			if(hasCho(jamoCode)) {
				if(hasCho(syllable)) {
					commitComposing();
					// TODO: 낱자 조합 만들기
				}
				syllable |= (jamoCode & MASK_CHO);
			}
			if(hasJung(jamoCode)) {
				if(hasJung(syllable)) {
					commitComposing();
				}
				syllable |= (jamoCode & MASK_JUNG);
			}
			if(hasJong(jamoCode)) {
				if(hasJong(syllable)) {
					commitComposing();
				}
				syllable |= (jamoCode & MASK_JONG);
			}
			for(CharacterGeneratorListener listener : listeners) {
				listener.onCompose(getComposing(syllable));
			}
		}

		return false;
	}

	/**
	 * Send events to commit currently composing {@link #syllable}.
	 */
	public void commitComposing() {
		for(CharacterGeneratorListener listener : listeners) {
			listener.onCommit(getComposing(syllable));
		}
		syllable = 0;
	}

	/**
	 * Get the {@link String} value for {@code syllable}.
	 * @param syllable Hangul Syllable Code to get value from.
	 * @return Corresponding {@link String} value of given {@code syllable}.
	 */
	public String getComposing(long syllable) {
		String composing = "";
		long cho = (syllable & MASK_CHO) >> 0x20;
		long jung = (syllable & MASK_JUNG) >> 0x10;
		long jong = (syllable & MASK_JONG) >> 0x00;

		if(cho != 0 && jung != 0) {
			// 초성과 중성이 모두 있는 경우 (음절 조합 가능)
			return (char) (((((cho-1) * 21) + jung-1) * 28) + jong + 0xac00) + "";
		} else if(cho == 0 && jung != 0 && jong != 0) {
			// 중성과 종성만 있는 경우
			return (char) 0x115f + "" + ((char) (jung + 0x1160)) + "" + ((char) (jong + 0x11a7));
		} else if(cho != 0 && jung == 0 && jong != 0) {
			// 초성과 종성만 있는 경우
			return ((char) (cho + 0x10ff)) + "" + (char) 0x1160 + "" + ((char) (jong + 0x11a7));
		} else if(cho != 0) {
			return ((char) (cho + 0x10ff)) + "";
		} else if(jung != 0) {
			return ((char) (jung + 0x1160)) + "";
		} else if(jong != 0) {
			return ((char) (jong + 0x11a7)) + "";
		}
		return "";
	}

	public boolean isCho(long jamoCode) {
		return ((jamoCode & MASK_CHO) != 0) && ((jamoCode & MASK_JUNG) == 0) && ((jamoCode & MASK_JONG) == 0);
	}

	public boolean isJung(long jamoCode) {
		return ((jamoCode & MASK_CHO) == 0) && ((jamoCode & MASK_JUNG) != 0) && ((jamoCode & MASK_JONG) == 0);
	}

	public boolean isJong(long jamoCode) {
		return ((jamoCode & MASK_CHO) == 0) && ((jamoCode & MASK_JUNG) == 0) && ((jamoCode & MASK_JONG) != 0);
	}

	public boolean hasCho(long jamoCode) {
		return (jamoCode & MASK_CHO) != 0;
	}

	public boolean hasJung(long jamoCode) {
		return (jamoCode & MASK_JUNG) != 0;
	}

	public boolean hasJong(long jamoCode) {
		return (jamoCode & MASK_JONG) != 0;
	}

	@Override
	public void addEventListener(CharacterGeneratorListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void removeEventListener(CharacterGeneratorListener listener) {
		this.listeners.remove(listener);
	}

}
