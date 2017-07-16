package me.blog.hgl1002.openwnn.KOKR.inputmethod;

public interface CharacterGenerator {

	public boolean onJamoCode(long code);

	public void addEventListener(CharacterGeneratorListener listener);

	public void removeEventListener(CharacterGeneratorListener listener);

	public static interface CharacterGeneratorListener {

		public void onCompose(String composing);

		public void onCommit(String composing);
	}

}
