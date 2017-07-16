package me.blog.hgl1002.openwnn.KOKR.inputmethod.impl;

import me.blog.hgl1002.openwnn.KOKR.inputmethod.CodeSystem;

public class BasicCodeSystem implements CodeSystem {

	public static final long CODE_PLAIN_CHARACTER = 0x0001000000000000L;

	public static final long CODE_HANGUL_3BEOL = 0x0003000000000000L;

	public static final long CODE_HANGUL_2BEOL = 0x0002000000000000L;

	public static final long MASK_CODE_TYPE = 0xffff000000000000L;

	public static final long MASK_CODE_VALUE = 0x0000ffffffffffffL;

	public static final long MASK_CHO = 0x0000ffff00000000L;

	public static final long MASK_JUNG = 0x00000000ffff0000L;

	public static final long MASK_JONG = 0x000000000000ffffL;

	@Override
	public char convertToUnicode(long jamoCode) {
		long type = jamoCode & MASK_CODE_TYPE;
		if(type == CODE_PLAIN_CHARACTER) {
			return (char) (jamoCode & MASK_CODE_VALUE);
		} else if(type == CODE_HANGUL_3BEOL) {

		} else if(type == CODE_HANGUL_2BEOL) {

		}
		return 0;
	}

}
