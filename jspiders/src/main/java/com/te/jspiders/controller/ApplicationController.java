package com.te.jspiders.controller;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.jspiders.dto.EmployeeDto;
import com.te.jspiders.dto.LoginDto;
import com.te.jspiders.dto.StudentDto;
import com.te.jspiders.dto.TrainerDto;
import com.te.jspiders.response.ApiResponse;
import com.te.jspiders.service.EmployeeService;
import com.te.jspiders.service.StudentService;
import com.te.jspiders.service.TrainerService;
import com.te.jspiders.utils.jwt.JwtUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/public")
public class ApplicationController {
	private final JwtUtils jwtUtils;
	private final AuthenticationManager authenticationManager;
	private final EmployeeService employeeService;
	public final StudentService studentService;
	private final TrainerService trainerService;

	@PostMapping(path = "/login")
	public ApiResponse<Object> login(@RequestBody LoginDto loginDto) {
		// ToDo
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

		String token = jwtUtils.generateToken(loginDto.getUsername());
		return new ApiResponse<Object>("login Successfull", token, loginDto.getUsername());
	}

	@PostMapping(path = "/employee/register")
	public ApiResponse<String> registerEmployee(@RequestBody EmployeeDto employeeDto) {
		Optional<String> empId = employeeService.registerEmployee(employeeDto);
		if (empId.isPresent()) {
			return new ApiResponse<String>("Employee Registration is Successfull", null, empId.get());
		}
		throw new RuntimeException("Employee Registration is UnSuccessfull");
	}

	@PostMapping(path = "/student/register")
	public ApiResponse<String> registerStudent(@RequestBody StudentDto studentDto) {
		Optional<String> stuId = studentService.register(studentDto);
		if (stuId.isPresent()) {
			return new ApiResponse<String>("Student Registration is Successfull", null, stuId.get());
		}
		throw new RuntimeException("Student Registration is UnSuccessfull");

	}

	@PostMapping(path = "/trainer/register")
	public ApiResponse<String> registerTrainer(@RequestBody TrainerDto trainerDto) {
		Optional<String> trainerId = trainerService.trainerRegister(trainerDto);
		if (trainerId.isPresent()) {
			return new ApiResponse<String>("Trainer Registration is successfull", null, trainerId.get());
		}
		throw new RuntimeException("Trainer Registration is UnSussessfull");
	}
}