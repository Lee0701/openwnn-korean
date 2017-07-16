package me.blog.hgl1002.openwnn.KOKR.inputmethod;

import android.view.KeyEvent;

public interface HardKeyboard {

	public void onInit();
	public void onDestroy();

	public boolean onKeyEvent(KeyEvent event, boolean softKey);

}
