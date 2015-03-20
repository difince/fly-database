package com.db.utils;

public class ServerSideError extends RuntimeException {
	public ServerSideError(String errMsg) {
		super(errMsg);
	}
}
