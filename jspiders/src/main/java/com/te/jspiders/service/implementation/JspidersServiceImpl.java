package com.te.jspiders.service.implementation;

import org.springframework.stereotype.Service;

import com.te.jspiders.repository.EmployeeRepository;
import com.te.jspiders.service.JspidersService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JspidersServiceImpl implements JspidersService{

	private final EmployeeRepository employeeRepository;


}
