package com.te.jspiders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.jspiders.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {


}
