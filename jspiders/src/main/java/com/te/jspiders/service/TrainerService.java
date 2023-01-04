package com.te.jspiders.service;

import java.util.Optional;

import com.te.jspiders.dto.TrainerDto;

public interface TrainerService {

	Optional<String> trainerRegister(TrainerDto trainerDto);

}
