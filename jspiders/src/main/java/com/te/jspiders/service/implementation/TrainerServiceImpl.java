package com.te.jspiders.service.implementation;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.te.jspiders.dto.TrainerDto;
import com.te.jspiders.entity.AppUser;
import com.te.jspiders.entity.Roles;
import com.te.jspiders.entity.Trainer;
import com.te.jspiders.repository.AppUserRepository;
import com.te.jspiders.repository.RolesRepository;
import com.te.jspiders.repository.TrainerRepository;
import com.te.jspiders.service.TrainerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TrainerServiceImpl implements TrainerService{

	private final RolesRepository rolesRepository;
	private final AppUserRepository appUserRepository;
	private final TrainerRepository trainerRepository;
	private final PasswordEncoder passwordEncoder;
	@Override
	public Optional<String> trainerRegister(TrainerDto trainerDto) {
		Trainer trainer = new Trainer();
		BeanUtils.copyProperties(trainerDto, trainer);
		Optional<Roles> trainerRole = rolesRepository.findByRoleName("ROLE_TRAINER");
		if(trainerRole.isPresent()) {
			Roles roles = trainerRole.get();
			AppUser appUser = AppUser.builder().username(trainer.getTrainerId()).password(passwordEncoder.encode(trainerDto.getPassword())).roles(Lists.newArrayList()).build();
			roles.getAppUser().add(appUser);
			appUser.getRoles().add(roles);
			appUserRepository.save(appUser);
		}
		
		return Optional.ofNullable(trainerRepository.save(trainer).getTrainerId());
	}

}
