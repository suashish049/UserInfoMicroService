package com.swiggato.user.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
	private LocalDateTime timestamp;
	private String message;
	private String endpoint;

	public ErrorDetails(LocalDateTime timestamp, String message, String endpoint) {
		this.timestamp = timestamp;
		this.message = message;
		this.endpoint = endpoint;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getEndpoint() {
		return endpoint;
	}
}
