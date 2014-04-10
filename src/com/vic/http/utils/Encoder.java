package com.vic.http.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Encoder {
	/**
	 * @param value
	 *            string to be encoded
	 * @return encoded parameters string
	 */
	public static String encode(String value) {
	    String encoded = null;
	    try {
	        encoded = URLEncoder.encode(value, "UTF-8");
	    } catch (UnsupportedEncodingException ignore) {
	    }
	    StringBuffer buf = new StringBuffer(encoded.length());
	    char focus;
	    for (int i = 0; i < encoded.length(); i++) {
	        focus = encoded.charAt(i);
	        if (focus == '*') {
	            buf.append("%2A");
	        } else if (focus == '+') {
	            buf.append("%20");
	        } else if (focus == '%' && (i + 1) < encoded.length() && encoded.charAt(i + 1) == '7'
	                && encoded.charAt(i + 2) == 'E') {
	            buf.append('~');
	            i += 2;
	        } else {
	            buf.append(focus);
	        }
	    }
	    return buf.toString();
	}
}

