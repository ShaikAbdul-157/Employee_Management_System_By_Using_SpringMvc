package com.te.jspiders.service.implementation;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.te.jspiders.dto.StudentDto;
import com.te.jspiders.entity.AppUser;
import com.te.jspiders.entity.Roles;
import com.te.jspiders.entity.Student;
import com.te.jspiders.repository.AppUserRepository;
import com.te.jspiders.repository.RolesRepository;
import com.te.jspiders.repository.StudentRepository;
import com.te.jspiders.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	private final RolesRepository rolesRepository;
	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;
	@Override
	public Optional<String> register(StudentDto studentDto) {
		Student student = new Student();
		BeanUtils.copyProperties(studentDto, student);
		Optional<Roles> studentRole = rolesRepository.findByRoleName("ROLE_STUDENT");
		if (studentRole.isPresent()) {
			Roles roles = studentRole.get();
			AppUser appUser = AppUser.builder().username(student.getStudentId()).password(passwordEncoder.encode(studentDto.getPassword()))
					.roles(Lists.newArrayList()).build();
			roles.getAppUser().add(appUser);
			appUser.getRoles().add(roles);
			appUserRepository.save(appUser);
		}
		return Optional.ofNullable(studentRepository.save(student).getStudentId());
	}

}
