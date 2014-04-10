package com.vic.http.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 自定义Log输出
 * @author chen
 * @date 2012-8-30 下午2:57:23
 */
public class Log {

	/** 是否输出日志 */
	public static final boolean ISPRINT = true;
	/** 返回值 */
	private static final int RETURN = 6;

    private Log() {
    }

    /**
     * Send a {@link #android.util.Log.VERBOSE} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
	public static int v(String tag, String msg) {
		try {
			if (ISPRINT)
				return android.util.Log.v(tag, msg);
		} catch (Exception e) {
		}
		return RETURN;
    }

    /**
     * Send a {@link #VERBOSE} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int v(String tag, String msg, Throwable tr) {
		try {
			if (ISPRINT)
				return android.util.Log.v(tag, msg, tr);
		} catch (Exception e) {
		}
		return RETURN;
    }

    /**
     * Send a {@link #DEBUG} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int d(String tag, String msg) {
		try {
			if (ISPRINT)
				return android.util.Log.d(tag, msg);
		} catch (Exception e) {
		}
		return RETURN;
    }

    /**
     * Send a {@link #DEBUG} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int d(String tag, String msg, Throwable tr) {
		try {
			if (ISPRINT)
				return android.util.Log.d(tag, msg, tr);
		} catch (Exception e) {
		}
		return RETURN;
    }

    /**
     * Send an {@link #INFO} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int i(String tag, String msg) {
		try {
			if (ISPRINT)
				return android.util.Log.i(tag, msg);
		} catch (Exception e) {
		}
		return RETURN;
    }

    /**
     * Send a {@link #INFO} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int i(String tag, String msg, Throwable tr) {
		try {
			if (ISPRINT)
				return android.util.Log.i(tag, msg, tr);
		} catch (Exception e) {
		}
		return RETURN;
    }

    /**
     * Send a {@link #WARN} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int w(String tag, String msg) {
		try {
			if (ISPRINT)
				return android.util.Log.w(tag, msg);
		} catch (Exception e) {
		}
		return RETURN;
    }

    /**
     * Send a {@link #WARN} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int w(String tag, String msg, Throwable tr) {
		try {
			if (ISPRINT)
				return android.util.Log.w(tag, msg, tr);
		} catch (Exception e) {
		}
		return RETURN;
    }

    /*
     * Send a {@link #WARN} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param tr An exception to log
     */
    public static int w(String tag, Throwable tr) {
		try {
			if (ISPRINT)
				return android.util.Log.w(tag, tr);
		} catch (Exception e) {
		}
		return RETURN;
    }

    /**
     * Send an {@link #ERROR} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int e(String tag, String msg) {
		try {
			if (ISPRINT)
				return android.util.Log.e(tag, msg);
		} catch (Exception e) {
		}
		return RETURN;
    }

    /**
     * Send a {@link #ERROR} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static int e(String tag, String msg, Throwable tr) {
		try {
			if (ISPRINT)
				return android.util.Log.e(tag, msg, tr);
		} catch (Exception e) {
		}
		return RETURN;
    }

    /**
     * Handy function to get a loggable stack trace from a Throwable
     * @param tr An exception to log
     */
    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        return sw.toString();
    }
}