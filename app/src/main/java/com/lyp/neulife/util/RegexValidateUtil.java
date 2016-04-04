package com.lyp.neulife.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 格式验证
 * @author lyp
 */

public class RegexValidateUtil {

	/**
	 * 验证邮箱
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
//			String regex = "^[a-zA-Z][a-zA-Z0-9_\\-]*@([a-zA-Z0-9\\-]+\\.)+[a-z]{2,3}$";
			String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码
	 * @param phoneNumber
	 * @return
	 */
	public static boolean checkPhoneNumber(String phoneNumber) {
		boolean flag = false;
		try {
			String regex = "^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(phoneNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
}
