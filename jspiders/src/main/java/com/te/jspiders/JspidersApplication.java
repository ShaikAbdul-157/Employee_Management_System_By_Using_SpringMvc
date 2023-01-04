package com.te.jspiders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.common.collect.Lists;
import com.te.jspiders.entity.Admin;
import com.te.jspiders.entity.AppUser;
import com.te.jspiders.entity.Roles;
import com.te.jspiders.repository.AdminRepository;
import com.te.jspiders.repository.AppUserRepository;
import com.te.jspiders.repository.RolesRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class JspidersApplication {

	private final AdminRepository adminRepository;
	private final RolesRepository rolesRepository;
	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(JspidersApplication.class, args);

	}

	@Bean
	public CommandLineRunner runner() {
		return (args) -> {
			Roles student = Roles.builder().roleName("ROLE_STUDENT").build();
			Roles trainer = Roles.builder().roleName("ROLE_TRAINER").build();
			Roles employee = Roles.builder().roleName("ROLE_EMPLOYEE").build();
			Roles admin = Roles.builder().roleName("ROLE_ADMIN").appUser(Lists.newArrayList()).build();

			Admin admin01 = Admin.builder().adminId("ADMIN01").adminName("Admin01").build();

			AppUser adminCredentials = AppUser.builder().username(admin01.getAdminId())
					.password(
							passwordEncoder.encode("123456")
							)
					.roles(Lists.newArrayList()).build();

			rolesRepository.save(student);
			rolesRepository.save(trainer);
			rolesRepository.save(employee);

			adminRepository.save(admin01);

			adminCredentials.getRoles().add(admin);
			admin.getAppUser().add(adminCredentials);

			rolesRepository.save(admin);
			appUserRepository.save(adminCredentials);

		};

	}

}
