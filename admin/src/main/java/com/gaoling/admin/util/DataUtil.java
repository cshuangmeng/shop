package com.gaoling.admin.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class DataUtil {
	
	public static void main(String[] args) {
		try {
			System.out.println(encodeWithSalt("1","admin"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static final String[] chars = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };

	// 生成指定位数的随即数字
	public static String createNums(int count) {
		String code = "";
		Random ram = new Random();
		for (int i = 0; i < count; i++) {
			code += ram.nextInt(10);
		}
		return code;
	}

	// 盐值加密
	public static String encodeWithSalt(String credentials, String salt) {
		String hashAlgorithmName = "MD5";// 加密算法
		int hashIterations = 1024;// 加密次数
		ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
		Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
		return obj.toString();
	}

	// 生成指定位数的随机字母字符串
	public static String createLetters(int count) {
		String code = "";
		int index = 0;
		Random ram = new Random();
		for (int i = 0; i < count; i++) {
			index = ram.nextInt(chars.length);
			while (index < 10) {
				index = ram.nextInt(chars.length);
			}
			code += chars[index];
		}
		return code;
	}

	// 验证手机号码
	public static boolean isPhoneNum(String mdn) {
		String reg = "^1\\d{10}$";
		return Pattern.matches(reg, null != mdn ? mdn : "");
	}

	// 验证图片格式
	public static boolean isImgFile(String imgName) {
		return Pattern.matches("^[\\w|\u4e00-\u9fa5]+\\.(gif|jpe?g|png)$", imgName);
	}

	// 拼装Map
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> mapOf(Object... v) {
		Map<K, V> ret = new HashMap<K, V>();
		if (null == v) {
			return ret;
		}
		for (int i = 0; i < v.length; i++) {
			ret.put((K) v[i], (V) v[++i]);
		}
		return ret;
	}

	// 删除多余Key
	public static <K, V> void removeKeys(Map<K, V> map, String[] keys) {
		for (String key : keys) {
			map.remove(key);
		}
	}

	// MD5 32位和16位加密
	public static String md5(String str, int count) {
		String result = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes("UTF-8"));
			byte[] bytes = md5.digest();
			int b = 0;
			StringBuffer mdString = new StringBuffer();
			for (int j = 0; j < bytes.length; j++) {
				b = bytes[j];
				if (b < 0) {
					b += 256;
				}
				if (b < 16) {
					mdString.append("0");
				}
				mdString.append(Integer.toHexString(b));
			}
			if (count == 16) {
				result = mdString.toString().substring(8, 24);
			} else if (count == 32) {
				result = mdString.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// Map转换成Object
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		Object obj = beanClass.newInstance();
		if (null != map) {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				Method setter = property.getWriteMethod();
				if (setter != null) {
					setter.invoke(obj, map.get(property.getName()));
				}
			}
		}
		return obj;
	}

	// Object转换成Map
	public static Map<String, Object> objectToMap(Object obj) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != obj) {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (key.compareToIgnoreCase("class") == 0) {
					continue;
				}
				Method getter = property.getReadMethod();
				Object value = getter != null ? getter.invoke(obj) : null;
				map.put(key, value);
			}
		}
		return map;
	}

	// 将对象存储在Map中返回
	public static HashMap<String, Object> saveObjectToMap(String key, Object value) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return map;
	}

	// UUID生成
	public static String buildUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	// 将汉字转换为全拼
	public static String getPingYin(String src) {
		char[] t1 = null;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断是否为汉字字符
				if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					t4 += t2[0];
				} else
					t4 += java.lang.Character.toString(t1[i]);
			}
			return t4;
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();
		}
		return t4;
	}

	// 返回中文的首字母
	public static String getPinYinHeadChar(String str) {
		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			}
		}
		return convert;
	}

}
