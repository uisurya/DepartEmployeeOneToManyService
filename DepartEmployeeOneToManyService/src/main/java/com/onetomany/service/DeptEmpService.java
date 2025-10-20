package com.onetomany.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onetomany.dto.DeptRequestDTO;
import com.onetomany.dto.DeptResponseDTO;
import com.onetomany.entity.Department;
import com.onetomany.entity.Employee;
import com.onetomany.repository.DeptRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class DeptEmpService {
	@Autowired
	DeptRepository deptRepository;
	@Autowired
	ModelMapper modelMapper;

	public DeptResponseDTO insert(DeptRequestDTO dto) {
		log.info("insert begins....");

		return modelMapper.map(deptRepository.save(modelMapper.map(dto, Department.class)), DeptResponseDTO.class);

	}

	public List<DeptResponseDTO> displayAll() {
		log.info("display begins....");

		return deptRepository.findAll().stream().map(e -> modelMapper.map(e, DeptResponseDTO.class))
				.collect(Collectors.toList());

	}
	
	/*@Transactional
    public Department updateDepartment(int id, DeptRequestDTO dto) {

        // 1️⃣ Find existing department
        Department existingDepartment = deptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        // 2️⃣ Update department name
        existingDepartment.setName(dto.getName());

        // 3️⃣ Clear and replace employee list
        existingDepartment.getEmployees().clear();

        if (dto.getEmployees() != null) {
            for (EmployeeRequestDTO empDTO : dto.getEmployees()) {
                // Use ModelMapper to map DTO → Entity
                Employee employee = modelMapper.map(empDTO, Employee.class);
                existingDepartment.getEmployees().add(employee);
            }
        }

        // 4️⃣ Save and return updated department
        return deptRepository.save(existingDepartment);
    }*/
	
	@Transactional
	public Department updateDepartment(int id, DeptRequestDTO dto) {
		log.info("Update begins....");
	    // 1 Find existing department and handle not found case
	    Department existingDepartment = deptRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

	    // 2 Update department name (and potentially other fields)
	    existingDepartment.setName(dto.getName());

	    // 3 Clear and replace employee list using a streamlined approach

	    // Clear the existing employees
	    existingDepartment.getEmployees().clear();

	    // Map new Employee DTOs to Entities and add them to the list
	    if (dto.getEmployees() != null) {
	        // Stream the DTOs, map each to an Employee Entity, and collect into a List
	        List<Employee> newEmployees = dto.getEmployees().stream()
	            .map(empDTO -> modelMapper.map(empDTO, Employee.class))
	            .toList(); // Using .toList() from Java 16+ or .collect(Collectors.toList()) for older versions

	        // Add all mapped employees to the existing list
	        existingDepartment.getEmployees().addAll(newEmployees);
	    }
	    
	    // 4 Save and return updated department
	    // Note: Since the method is @Transactional, the .save() might be redundant
	    // if the changes to 'existingDepartment' are automatically flushed/merged.
	    // However, it's often included for clarity or to ensure immediate persistence/return value.
		log.info("Update ends....");

	    return deptRepository.save(existingDepartment);
	}
}
