package com.mcoding.base.core;

/**
 * 业务类异常
 * @author hzy
 * @param <T>
 *
 */
public class CommonException extends RuntimeException {

	private static final long serialVersionUID = -6472269668399755099L;
	
	private Object data;
	
	public CommonException() {
		super();
	}

	public CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CommonException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommonException(String message) {
		super(message);
	}
	
	public CommonException(String message, String code) {
		super(message);
		this.data = code;
	}
	
	public CommonException(String message, Object data) {
		super(message);
		this.data = data;
	}

	public CommonException(Throwable cause) {
		super(cause);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
