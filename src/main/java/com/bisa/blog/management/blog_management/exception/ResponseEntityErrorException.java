package com.bisa.blog.management.blog_management.exception;

import org.springframework.http.ResponseEntity;

import com.bisa.blog.management.blog_management.payload.response.ApiResponse;

public class ResponseEntityErrorException extends RuntimeException {

	private transient ResponseEntity<ApiResponse> apiResponse;

	public ResponseEntityErrorException(ResponseEntity<ApiResponse> apiResponse) {
		this.apiResponse = apiResponse;
	}

	public ResponseEntity<ApiResponse> getApiResponse() {
		return apiResponse;
	}
}
