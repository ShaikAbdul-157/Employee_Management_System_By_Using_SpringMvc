package com.te.jspiders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.jspiders.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{

}
