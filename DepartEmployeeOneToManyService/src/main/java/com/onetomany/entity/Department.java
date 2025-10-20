package com.onetomany.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Department {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@OneToMany(cascade =CascadeType.ALL)
	@JoinColumn(name="department_id")
    private List<Employee> employees;

	

}
