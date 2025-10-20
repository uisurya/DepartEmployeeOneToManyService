package com.onetomany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onetomany.dto.DeptRequestDTO;
import com.onetomany.dto.DeptResponseDTO;
import com.onetomany.entity.Department;
import com.onetomany.service.DeptEmpService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/DepartmentEmployee")
@Slf4j
public class DeptEmpController {
	
	@Autowired
	DeptEmpService deptEmpService;
	
	@PostMapping("/insert")
	public DeptResponseDTO insert(@RequestBody DeptRequestDTO dto) {
		log.info("insert begins....");

		return deptEmpService.insert(dto);
	}
	@GetMapping("/displayAll")
	public List<DeptResponseDTO> displayAll() {
		log.info("display begins....");
		//log.trace("TraceTesting");
		log.error("Error testing");
		return deptEmpService.displayAll();

	}
	@PostMapping("/updateDepartment")
    public Department updateDepartment(@RequestParam int id,@RequestBody DeptRequestDTO dto) {
		log.info("updateDepartment begins....");

    	return deptEmpService.updateDepartment(id, dto);
    }

}
