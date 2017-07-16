package me.blog.hgl1002.openwnn;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.inputmethodservice.InputMethodService;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import me.blog.hgl1002.openwnn.KOKR.inputmethod.HardKeyboard;
import me.blog.hgl1002.openwnn.KOKR.inputmethod.impl.BasicHardKeyboard;

public class OpenWnnKOKR extends InputMethodService {

	private boolean mConsumeDownEvent;

	protected HardKeyboard mHardKeyboard;

	private static OpenWnnKOKR mSelf;
	public static OpenWnnKOKR getInstance() {
		return mSelf;
	}
	
	public OpenWnnKOKR() {
		super();
		mSelf = this;

		mHardKeyboard = new BasicHardKeyboard(this);

	}
	
	public OpenWnnKOKR(Context context) {
		this();
		attachBaseContext(context);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mHardKeyboard.onInit();
	}

	@Override
	public View onCreateInputView() {
		int hiddenState = getResources().getConfiguration().hardKeyboardHidden;
		boolean hidden = (hiddenState == Configuration.HARDKEYBOARDHIDDEN_YES);
		return super.onCreateInputView();
	}

	@Override
	public void onStartInputView(EditorInfo attribute, boolean restarting) {

		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

	}

	@Override
	public void onStartInput(EditorInfo attribute, boolean restarting) {
		super.onStartInput(attribute, restarting);
	}

	@Override
	public View onCreateCandidatesView() {
		return super.onCreateCandidatesView();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onFinishInput() {
		super.onFinishInput();
	}

	@Override
	public void onViewClicked(boolean focusChanged) {
		super.onViewClicked(focusChanged);
	}

	@Override
	public void hideWindow() {
		mHardKeyboard.onDestroy();
		super.hideWindow();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		mConsumeDownEvent = mHardKeyboard.onKeyEvent(event, false);
		if (!mConsumeDownEvent) {
			return super.onKeyDown(keyCode, event);
		}
		return mConsumeDownEvent;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		boolean ret = mConsumeDownEvent;
		if (!ret) {
			ret = super.onKeyUp(keyCode, event);
		}else{
			mHardKeyboard.onKeyEvent(event, false);
		}
		return ret;
	}

	@Override
	public boolean onEvaluateFullscreenMode() {
		return false;
	}

	@Override
	public boolean onEvaluateInputViewShown() {
		return true;
	}

}
