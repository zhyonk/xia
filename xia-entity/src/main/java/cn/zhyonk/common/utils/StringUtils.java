package cn.zhyonk.common.utils;

public class StringUtils {

	public static byte[] getBytes(String object) {
		byte[] bytes = object.getBytes();
		return bytes;
	}

	public static String toString(byte[] key) {
		return new String(key);
	}

	public static boolean isNotBlank(String value) {
		if (value!="") {
			return true;
		}
		if (value==null) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(String str) {
		if (str==null) {
			return false;
		}
		return str.isEmpty();
	}

	public static boolean equals(String uid, String uid2) {
		if (uid.equals(uid2)) {
			return true;
		}
		return false;
	}

}
