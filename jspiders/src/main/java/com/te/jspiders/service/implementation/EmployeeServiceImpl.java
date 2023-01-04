
package com.te.jspiders.service.implementation;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.te.jspiders.dto.EmployeeDto;
import com.te.jspiders.entity.AppUser;
import com.te.jspiders.entity.Employee;
import com.te.jspiders.entity.Roles;
import com.te.jspiders.repository.AppUserRepository;
import com.te.jspiders.repository.EmployeeRepository;
import com.te.jspiders.repository.RolesRepository;
import com.te.jspiders.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final RolesRepository rolesRepository;
	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Optional<String> registerEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDto, employee);
		Optional<Roles> employeeRole = rolesRepository.findByRoleName("ROLE_EMPLOYEE");
		if (employeeRole.isPresent()) {
			Roles roles = employeeRole.get();
			AppUser appUser = AppUser.builder().username(employee.getEmployeeId())
					.password(passwordEncoder.encode(employeeDto.getPassword())).roles(Lists.newArrayList()).build();
			roles.getAppUser().add(appUser);
			appUser.getRoles().add(roles);
			appUserRepository.save(appUser);

		}
		return Optional.ofNullable(employeeRepository.save(employee).getEmployeeId());
	}

}
