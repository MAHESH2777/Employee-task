package com.employee.service;

import java.util.List;

import com.employee.entity.Employee;
import com.employee.modal.EmployeeTaxInfodto;
import com.employee.modal.Employeedto;

public interface EmployeeService {

	Employee addEmployee(Employeedto employeeDto);

	List<EmployeeTaxInfodto> calculateTaxDeduction();

}
