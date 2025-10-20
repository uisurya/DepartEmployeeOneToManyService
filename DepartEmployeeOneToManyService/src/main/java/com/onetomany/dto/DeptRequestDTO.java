package com.onetomany.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeptRequestDTO {
	private String name;
    private List<EmployeeRequestDTO> employees;


}
