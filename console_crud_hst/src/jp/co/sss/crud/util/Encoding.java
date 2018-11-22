package jp.co.sss.crud.util;

public class Encoding {

	public static String GenderEncoding(String s) {
		String gender = null;
		switch (s) {
		case "1":
			gender = "男性";
			break;
		default:
			gender = "女性";
			break;
		}
		return gender;
	}
}
