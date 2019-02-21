package com.qinghuan.util;

import java.io.UnsupportedEncodingException;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * ��Base64�ӽ��ܱ�����Cookie�е���Ϣ
 * ����RFC2045�Ķ��壬Base64������Ϊ��Base64���ݴ��ͱ��뱻����������������е�8λ�ֽ�����Ϊһ�ֲ��ױ���ֱ��ʶ�����ʽ��
 * 
 * @author QST����ʵѵ
 *
 */
public class CookieEncryptTool {

	/**
	 * Base64����
	 * 
	 * @param cleartext
	 * @return
	 */
	public static String encodeBase64(String cleartext) {

		try {
			cleartext = new String(Base64.encodeBase64(cleartext
					.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return cleartext;
	}

	/**
	 * Base64����
	 * 
	 * @param ciphertext
	 * @return
	 */
	public static String decodeBase64(String ciphertext) {
		try {
			ciphertext = new String(Base64.decodeBase64(ciphertext.getBytes()),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ciphertext;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "����abc123";
		String encode = CookieEncryptTool.encodeBase64(str);
		System.out.println(encode);

		String decode = CookieEncryptTool.decodeBase64(encode);
		System.out.println(decode);
	}
}
