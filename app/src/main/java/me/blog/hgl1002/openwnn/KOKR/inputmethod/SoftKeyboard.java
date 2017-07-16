package me.blog.hgl1002.openwnn.KOKR.inputmethod;

import android.view.View;

public interface SoftKeyboard {

	public void onInit();
	public void onDestroy();

	public View createInputView();

	public View createCandidatesView();

}
