package com.varun.RestApi_SpringBoot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.varun.RestApi_SpringBoot.model.Student;

public interface StudentDAO extends JpaRepository<Student, Integer> {

}
