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

	public static final long H3 = CODE_HANGUL_3BEOL;
	public static final long H2 = CODE_HANGUL_2BEOL;

	public static final long G_ = 0x0000000100000000L;
	public static final long GG_= 0x0000000200000000L;
	public static final long N_ = 0x0000000300000000L;
	public static final long D_ = 0x0000000400000000L;
	public static final long DD_= 0x0000000500000000L;
	public static final long R_ = 0x0000000600000000L;
	public static final long M_ = 0x0000000700000000L;
	public static final long B_ = 0x0000000800000000L;
	public static final long BB_= 0x0000000900000000L;
	public static final long S_ = 0x0000000a00000000L;
	public static final long SS_= 0x0000000b00000000L;
	public static final long Q_ = 0x0000000c00000000L;
	public static final long J_ = 0x0000000d00000000L;
	public static final long C_ = 0x0000000e00000000L;
	public static final long K_ = 0x0000000f00000000L;
	public static final long T_ = 0x0000001000000000L;
	public static final long P_ = 0x0000001100000000L;
	public static final long H_ = 0x0000001200000000L;

	public static final long A_ = 0x0000000000010000L;
	public static final long AE = 0x0000000000020000L;
	public static final long YA = 0x0000000000030000L;
	public static final long YAE= 0x0000000000040000L;
	public static final long EO = 0x0000000000050000L;
	public static final long E_ = 0x0000000000060000L;
	public static final long YEO= 0x0000000000070000L;
	public static final long YE = 0x0000000000080000L;
	public static final long O_ = 0x0000000000090000L;
	public static final long WA = 0x00000000000a0000L;
	public static final long WAE= 0x00000000000b0000L;
	public static final long OI = 0x00000000000c0000L;
	public static final long YO = 0x00000000000d0000L;
	public static final long U_ = 0x00000000000e0000L;
	public static final long UEO= 0x00000000000f0000L;
	public static final long WE = 0x0000000000100000L;
	public static final long WI = 0x0000000000110000L;
	public static final long YU = 0x0000000000120000L;
	public static final long EU = 0x0000000000130000L;
	public static final long EUI= 0x0000000000140000L;
	public static final long I_ = 0x0000000000150000L;

	public static final long _G = 0x0000000000000001L;
	public static final long _GG= 0x0000000000000002L;
	public static final long _GS= 0x0000000000000003L;
	public static final long _N = 0x0000000000000004L;
	public static final long _NJ= 0x0000000000000005L;
	public static final long _NH= 0x0000000000000006L;
	public static final long _D = 0x0000000000000007L;
	public static final long _R = 0x0000000000000008L;
	public static final long _RG= 0x0000000000000009L;
	public static final long _RM= 0x000000000000000aL;
	public static final long _RB= 0x000000000000000bL;
	public static final long _RS= 0x000000000000000cL;
	public static final long _RT= 0x000000000000000dL;
	public static final long _RP= 0x000000000000000eL;
	public static final long _RH= 0x000000000000000fL;
	public static final long _M = 0x0000000000000010L;
	public static final long _B = 0x0000000000000011L;
	public static final long _BS= 0x0000000000000012L;
	public static final long _S = 0x0000000000000013L;
	public static final long _SS= 0x0000000000000014L;
	public static final long _Q = 0x0000000000000015L;
	public static final long _J = 0x0000000000000016L;
	public static final long _C = 0x0000000000000017L;
	public static final long _K = 0x0000000000000018L;
	public static final long _T = 0x0000000000000019L;
	public static final long _P = 0x000000000000001aL;
	public static final long _H = 0x000000000000001bL;

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
		int cho = (int) ((jamoCode & MASK_CHO) >> 0x20);
		int jung = (int) ((jamoCode & MASK_JUNG) >> 0x10);
		int jong = (int) ((jamoCode & MASK_JONG) >> 0x00);
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
