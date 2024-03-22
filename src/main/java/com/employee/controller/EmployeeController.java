package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.employee.entity.Employee;
import com.employee.modal.EmployeeTaxInfodto;
import com.employee.modal.Employeedto;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("/employees") 
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employeedto employeeDto) {
		return employeeService.addEmployee(employeeDto);
	}

	@GetMapping("/tax-deduction")
	public List<EmployeeTaxInfodto> getTaxDeduction() {
		return employeeService.calculateTaxDeduction();
	}
}