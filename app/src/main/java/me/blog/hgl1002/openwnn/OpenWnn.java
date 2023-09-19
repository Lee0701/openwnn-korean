/*
 * Copyright (C) 2008,2009  OMRON SOFTWARE Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.blog.hgl1002.openwnn;

import android.inputmethodservice.InputMethodService;
import android.view.WindowManager;
import android.content.Context;
import android.view.View;
import android.view.KeyEvent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import android.view.inputmethod.*;

import org.greenrobot.eventbus.EventBus;

import me.blog.hgl1002.openwnn.event.InputKeyEvent;
import me.blog.hgl1002.openwnn.event.KeyUpEvent;
import me.blog.hgl1002.openwnn.event.OpenWnnEvent;

/**
 * The OpenWnn IME's base class.
 *
 * @author Copyright (C) 2009 OMRON SOFTWARE CO., LTD.  All Rights Reserved.
 */
public class OpenWnn extends InputMethodService {

    /** Input view (software keyboard) */
    protected InputViewManager  mInputViewManager = null;
    /** The input connection */
    protected InputConnection mInputConnection = null;
    /** Auto hide candidate view */
    protected boolean mAutoHideMode = true;
    /** Direct input mode */
    protected boolean mDirectInputMode = true;
     
    /** Flag for checking if the previous down key event is consumed by OpenWnn  */
    private boolean mConsumeDownEvent;

    /**
     * Constructor
     */
    public OpenWnn() {
        super();
    }

    /***********************************************************************
     * InputMethodService 
     **********************************************************************/
    /** @see android.inputmethodservice.InputMethodService#onCreate */
    @Override public void onCreate() {
        super.onCreate();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
    }

    /** @see android.inputmethodservice.InputMethodService#onCreateCandidatesView */
    @Override public View onCreateCandidatesView() {
        return super.onCreateCandidatesView();
    }

    /** @see android.inputmethodservice.InputMethodService#onCreateInputView */
    @Override public View onCreateInputView() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);


        if (mInputViewManager != null) {
            WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
            return mInputViewManager.initView(this,
                                              wm.getDefaultDisplay().getWidth(),
                                              wm.getDefaultDisplay().getHeight());
        } else {
            return super.onCreateInputView();
        }
    }

    /** @see android.inputmethodservice.InputMethodService#onDestroy */
    @Override public void onDestroy() {
        super.onDestroy();

        close();
    }

    /** @see android.inputmethodservice.InputMethodService#onStartInput */
    @Override public void onStartInput(EditorInfo attribute, boolean restarting) {
        super.onStartInput(attribute, restarting);
        mInputConnection = getCurrentInputConnection();
    }

    /** @see android.inputmethodservice.InputMethodService#onStartInputView */
    @Override public void onStartInputView(EditorInfo attribute, boolean restarting) {
        super.onStartInputView(attribute, restarting);
        mInputConnection = getCurrentInputConnection();

        setCandidatesViewShown(false);
        if (mInputConnection != null) {
            mDirectInputMode = false;
        } else {
            mDirectInputMode = true;
        }
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        if (mInputViewManager != null) { mInputViewManager.setPreferences(pref, attribute);  }
    }

    /** @see android.inputmethodservice.InputMethodService#requestHideSelf */
    @Override public void requestHideSelf(int flag) {
        super.requestHideSelf(flag);
        if (mInputViewManager == null) {
            hideWindow();
        }
    }

    /** @see android.inputmethodservice.InputMethodService#setCandidatesViewShown */
    @Override public void setCandidatesViewShown(boolean shown) {
        super.setCandidatesViewShown(shown);
        if (shown) {
            showWindow(true);
        } else {
            if (mAutoHideMode && mInputViewManager == null) {
                hideWindow();
            }
        }
    }

    /** @see android.inputmethodservice.InputMethodService#hideWindow */
    @Override public void hideWindow() {
        super.hideWindow();
        mDirectInputMode = true;
        hideStatusIcon();
    }
    /** @see android.inputmethodservice.InputMethodService#onComputeInsets */
    @Override public void onComputeInsets(InputMethodService.Insets outInsets) {
        super.onComputeInsets(outInsets);
        outInsets.contentTopInsets = outInsets.visibleTopInsets;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        InputKeyEvent inputKeyEvent = new InputKeyEvent(event);
        EventBus.getDefault().post(inputKeyEvent);
        mConsumeDownEvent = inputKeyEvent.isCancelled();
        if(!mConsumeDownEvent) {
        	return super.onKeyDown(keyCode, event);
		}
        return mConsumeDownEvent;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        boolean ret = mConsumeDownEvent;
        if(!ret) {
            ret = super.onKeyUp(keyCode, event);
        } else {
            EventBus.getDefault().post(new KeyUpEvent(event));
        }
        return ret;
    }
/**********************************************************************
     * OpenWnn
     **********************************************************************/
    /**
     * Process an event.
     *
     * @param  ev  An event
     * @return  {@code true} if the event is processed in this method; {@code false} if not.
     */
    public boolean onEvent(OpenWnnEvent ev) {
        return false;
    }

    /**
     * Search a character for toggle input.
     *
     * @param prevChar     The character input previous
     * @param toggleTable  Toggle table
     * @param reverse      {@code false} if toggle direction is forward, {@code true} if toggle direction is backward
     * @return          A character ({@code null} if no character is found)
     */
    protected String searchToggleCharacter(String prevChar, String[] toggleTable, boolean reverse) {
        for (int i = 0; i < toggleTable.length; i++) {
            if (prevChar.equals(toggleTable[i])) {
                if (reverse) {
                    i--;
                    if (i < 0) {
                        return toggleTable[toggleTable.length - 1];
                    } else {
                        return toggleTable[i];
                    }
                } else {
                    i++;
                    if (i == toggleTable.length) {
                        return toggleTable[0];
                    } else {
                        return toggleTable[i];
                    }
                }
            }
        }
        return null;
    }

    /**
     * Processing of resource open when IME ends.
     */
    protected void close() {
    }
}
