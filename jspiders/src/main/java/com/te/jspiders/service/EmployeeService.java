package com.te.jspiders.service;

import java.util.Optional;

import com.te.jspiders.dto.EmployeeDto;
import com.te.jspiders.entity.Employee;

public interface EmployeeService {

	Optional<String> registerEmployee(EmployeeDto employeeDto);

}
