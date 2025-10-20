package com.onetomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onetomany.entity.Department;

public interface DeptRepository extends JpaRepository<Department, Integer>{

}
