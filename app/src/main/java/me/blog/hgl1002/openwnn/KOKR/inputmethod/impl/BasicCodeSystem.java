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

	public static final int[] COMPATIBILITY_CHO = {
			0x0000,
			0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 0x3142,
			0x3143, 0x3145, 0x3146, 0x3147, 0x3148, 0x3149, 0x314a, 0x314b,
			0x314c, 0x314d, 0x314e,
	};

	public static final int[] COMPATIBILITY_JUNG = {
			0x0000,
			0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156,
			0x3157, 0x3158, 0x3159, 0x315a, 0x315b, 0x315c, 0x315d, 0x315e,
			0x315f, 0x3160, 0x3161, 0x3162, 0x3163,
	};

	public static final int[] COMPATIBILITY_JONG = {
			0x0000,
			0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136, 0x3137, 0x3139,
			0x313a, 0x313b, 0x313c, 0x313d, 0x313e, 0x313f, 0x3140, 0x3141,
			0x3142, 0x3144, 0x3145, 0x3146, 0x3147, 0x3148, 0x314a, 0x314b,
			0x314c, 0x314d, 0x314e,
	};

	public static String convertHangul(long jamoCode) {
		if((jamoCode & MASK_CODE_TYPE) != CODE_HANGUL_2BEOL
				&& (jamoCode & MASK_CODE_TYPE) != CODE_HANGUL_3BEOL) {
			return "";
		}
		int cho = (int) (jamoCode & MASK_CHO) >> 0x20;
		int jung = (int) (jamoCode & MASK_JUNG) >> 0x10;
		int jong = (int) (jamoCode & MASK_JONG) >> 0x00;
		System.out.println(cho + " " + jung + " " + jong);
		if(hasCho(jamoCode) && hasJung(jamoCode)) {
			return new String(new char[] {(char) (((((cho-1) * 21) + jung-1) * 28) + jong + 0xac00)});
		} else if(hasCho(jamoCode)) {
			return new String(new char[] {(char) COMPATIBILITY_CHO[cho]});
		} else if(hasJung(jamoCode)) {
			return new String(new char[] {(char) COMPATIBILITY_JUNG[jung]});
		} else if(hasJong(jamoCode)) {
			return new String(new char[] {(char) COMPATIBILITY_JONG[jong]});
		} else {
			return "";
		}
	}

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

	public boolean isCho(long jamoCode) {
		return ((jamoCode & MASK_CHO) != 0) && ((jamoCode & MASK_JUNG) == 0) && ((jamoCode & MASK_JONG) == 0);
	}

	public static boolean isJung(long jamoCode) {
		return ((jamoCode & MASK_CHO) == 0) && ((jamoCode & MASK_JUNG) != 0) && ((jamoCode & MASK_JONG) == 0);
	}

	public static boolean isJong(long jamoCode) {
		return ((jamoCode & MASK_CHO) == 0) && ((jamoCode & MASK_JUNG) == 0) && ((jamoCode & MASK_JONG) != 0);
	}

	public static boolean hasCho(long jamoCode) {
		return (jamoCode & MASK_CHO) != 0;
	}

	public static boolean hasJung(long jamoCode) {
		return (jamoCode & MASK_JUNG) != 0;
	}

	public static boolean hasJong(long jamoCode) {
		return (jamoCode & MASK_JONG) != 0;
	}

}
