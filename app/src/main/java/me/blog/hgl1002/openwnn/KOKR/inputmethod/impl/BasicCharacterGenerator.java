package me.blog.hgl1002.openwnn.KOKR.inputmethod.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import me.blog.hgl1002.openwnn.KOKR.inputmethod.CharacterGenerator;

import static me.blog.hgl1002.openwnn.KOKR.inputmethod.impl.BasicCodeSystem.*;

public class BasicCharacterGenerator implements CharacterGenerator {

	protected JamoHandler jamoHandler;

	protected Stack<Status> history;

	Status currentStatus;

	protected List<CharacterGeneratorListener> listeners = new ArrayList<>();

	@Override
	public void onInit() {
		history = new Stack<>();
		currentStatus = new Status();
	}

	@Override
	public void onDestroy() {

	}

	@Override
	public boolean onJamoCode(long jamoCode) {

		if((jamoCode & MASK_CODE_TYPE) == CODE_HANGUL_3BEOL) {

			history.push(currentStatus);
			try {
				currentStatus = (Status) currentStatus.clone();
			} catch (CloneNotSupportedException e) {};

			jamoCode = jamoCode & MASK_CODE_VALUE;

			if(hasCho(jamoCode)) {
				if(hasCho(currentStatus.syllable)) {
					commitComposing();
					// TODO: 낱자 조합 만들기
				}
				long choCode = (jamoCode & MASK_CHO);
				currentStatus.cho = choCode >> 0x20;
				currentStatus.syllable |= choCode;
			}
			if(hasJung(jamoCode)) {
				if(hasJung(currentStatus.syllable)) {
					commitComposing();
				}
				long jungCode = (jamoCode & MASK_JUNG);
				currentStatus.jung = jungCode >> 0x10;
				currentStatus.syllable |= jungCode;
			}
			if(hasJong(jamoCode)) {
				if(hasJong(currentStatus.syllable)) {
					commitComposing();
				}
				long jongCode = (jamoCode & MASK_JONG);
				currentStatus.jong = jongCode >> 0;
				currentStatus.syllable |= jongCode;
			}
			System.out.println(Long.toHexString(currentStatus.syllable));
			for(CharacterGeneratorListener listener : listeners) {
				String composing = getComposing(currentStatus.syllable);
				listener.onCompose(composing);
			}

			return true;
		}

		return false;
	}

	@Override
	public boolean onBackspace(int mode) {
		if(history.isEmpty()) {
			if(currentStatus.syllable != 0) {
				for(CharacterGeneratorListener listener : listeners) {
					currentStatus.syllable = 0;
					listener.onCompose("");
				}
				return true;
			}
			else return false;
		}
		Status status = history.pop();
		this.currentStatus = status;
		for(CharacterGeneratorListener listener : listeners) {
			listener.onCompose(getComposing(currentStatus.syllable));
		}
		return true;
	}

	public void commitComposing() {
		history.clear();
		for(CharacterGeneratorListener listener : listeners) {
			listener.onCommit(getComposing(currentStatus.syllable));
		}
		currentStatus = new Status();
	}

	public String getComposing(long syllable) {

		return BasicCodeSystem.convertHangul(syllable | CODE_HANGUL_3BEOL);

	}

	@Override
	public void addEventListener(CharacterGeneratorListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void removeEventListener(CharacterGeneratorListener listener) {
		this.listeners.remove(listener);
	}

	public static class Status implements Cloneable {
		protected int automataStatus;
		protected long cho, jung, jong, syllable;

		@Override
		protected Object clone() throws CloneNotSupportedException {
			Status status = new Status();
			status.automataStatus = automataStatus;
			status.cho = cho;
			status.jung = jung;
			status.jong = jong;
			status.syllable = syllable;
			return status;
		}

		public int getAutomataStatus() {
			return automataStatus;
		}

		public void setAutomataStatus(int automataStatus) {
			this.automataStatus = automataStatus;
		}

		public long getCho() {
			return cho;
		}

		public void setCho(long cho) {
			this.cho = cho;
		}

		public long getJung() {
			return jung;
		}

		public void setJung(long jung) {
			this.jung = jung;
		}

		public long getJong() {
			return jong;
		}

		public void setJong(long jong) {
			this.jong = jong;
		}

		public long getSyllable() {
			return syllable;
		}

		public void setSyllable(long syllable) {
			this.syllable = syllable;
		}
	}

}
