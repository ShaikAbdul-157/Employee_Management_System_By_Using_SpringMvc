package com.te.jspiders.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.jspiders.dto.AdminDto;
import com.te.jspiders.response.ApiResponse;

@RestController
@RequestMapping(path = "/api/admin")
public class AdminController {

	@PostMapping(path = "/register")
	public ApiResponse<Integer> adminRegister(@RequestBody AdminDto adminDto){
		return new ApiResponse<Integer>("Admin Registration Successfull",null, 1);
	}
}
