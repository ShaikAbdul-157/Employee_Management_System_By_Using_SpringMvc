package com.te.jspiders.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.jspiders.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth/trainer")
public class TrainerController {

	@PutMapping(path = "/update")
	public ApiResponse<String> updateTrainer() {
		return new ApiResponse<String>("Trainer update successfull!", null, "update api being used");
	}

	@DeleteMapping(path = "/delet")
	public ApiResponse<String> deletTrainer() {
		return new ApiResponse<String>("Trainer delet successfull!", null, "delet api being used");
	}

	@PutMapping(path = "/checkpassword")
	public ApiResponse<String> checkpassword() {
		return new ApiResponse<String>("Trainer checkpassword successfull!", null, "checkpassword api being used");
	}
}
