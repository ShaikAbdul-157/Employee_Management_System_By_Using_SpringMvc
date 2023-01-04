package com.te.jspiders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.jspiders.entity.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, String>{

}
