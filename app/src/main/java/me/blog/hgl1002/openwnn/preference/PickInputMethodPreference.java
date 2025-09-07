package me.blog.hgl1002.openwnn.preference;

import android.app.Service;
import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;

import androidx.preference.Preference;

public class PickInputMethodPreference extends Preference {
	
	Context context;
	
	public PickInputMethodPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	@Override
	protected void onClick() {
		InputMethodManager inputMethodManager
		 = (InputMethodManager) context.getSystemService(Service.INPUT_METHOD_SERVICE);
		inputMethodManager.showInputMethodPicker();
	}

}
