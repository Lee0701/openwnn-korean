package me.blog.hgl1002.openwnn.event;

import me.blog.hgl1002.openwnn.hangul.EngineMode;

public class EngineModeChangeEvent extends SebeolHangulIMEEvent {

	EngineMode engineMode;

	public EngineModeChangeEvent(EngineMode engineMode) {
		this.engineMode = engineMode;
	}

	public EngineMode getEngineMode() {
		return engineMode;
	}

}
