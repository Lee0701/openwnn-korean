package me.blog.hgl1002.openwnn.KOKR.event;

public class CommitCharEvent extends Event {

	private char character;
	private int cursorPosition;

	public CommitCharEvent(char character, int cursorPosition) {
		this.character = character;
		this.cursorPosition = cursorPosition;
	}

	public char getCharacter() {
		return character;
	}

	public int getCursorPosition() {
		return cursorPosition;
	}
}
