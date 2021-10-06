package controller.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class editFormat {
	private static final Pattern emailRegex = Pattern
			.compile("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
	private static final Pattern passwdRegex = Pattern.compile("^\\w{8,16}$");
	private static final Pattern usernameRegex = Pattern.compile("^\\w{1,16}$");

	// 檢查是否為數字
	public static boolean chkNum(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str.trim());
		if (!isNum.matches()) {
			return false;
		} else {
			return true;
		}
	}

	// 檢查EMAIL
	public static boolean validateEmail(String email) {
		return email != null && emailRegex.matcher(email).find();
	}

	// 檢查username
	public static boolean validateUserName(String usrName) {
		return usrName != null && usernameRegex.matcher(usrName).find();
		// return usrName != null ;
	}

	// 檢查PWD
	public static boolean validatePWD(String pwd) {
		// return pwd != null && passwdRegex.matcher(pwd).find() ;
		return pwd != null && !pwd.trim().equalsIgnoreCase("");
	}
}
