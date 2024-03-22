package com.employee.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employee.entity.Employee;
import com.employee.entity.PhoneNumber;
import com.employee.modal.EmployeeTaxInfodto;
import com.employee.modal.Employeedto;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employeedto employeeDto) {
        validateEmployeeData(employeeDto);
        Employee emp = new Employee();
        emp.setEmployeeId(employeeDto.getEmployeeID());
        emp.setFirstName(employeeDto.getFirstName());
        emp.setLastName(employeeDto.getLastName());
        emp.setEmail(employeeDto.getEmail());
        emp.setDateOfJoining(employeeDto.getDOJ());
        emp.setSalary(employeeDto.getSalary());

        // Adding phone numbers
        List<String> phoneNumbers = employeeDto.getPhoneNumbers();
        List<PhoneNumber> phoneEntities = new ArrayList<>();
        if (phoneNumbers != null && !phoneNumbers.isEmpty()) {
            for (String phoneNumber : phoneNumbers) {
                PhoneNumber phoneEntity = new PhoneNumber();
                phoneEntity.setPhoneNumber(phoneNumber);
                phoneEntity.setEmployee(emp);
                phoneEntities.add(phoneEntity);
            }
        }
        emp.setPhoneNumbers(phoneEntities);

        return employeeRepository.save(emp);
    }

    @Override
    public List<EmployeeTaxInfodto> calculateTaxDeduction() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeTaxInfodto> taxInfoList = new ArrayList<>();
        for (Employee employee : employees) {
            double yearlySalary = calculateYearlySalary(employee.getDateOfJoining(), employee.getSalary());
            double taxAmount = calculateTaxAmount(yearlySalary);
            double cessAmount = calculateCessAmount(yearlySalary);
            EmployeeTaxInfodto taxInfo = new EmployeeTaxInfodto();
            taxInfo.setEmployeeID(employee.getEmployeeId());
            taxInfo.setFirstName(employee.getFirstName());
            taxInfo.setLastName(employee.getLastName());
            taxInfo.setYearlySalary(yearlySalary);
            taxInfo.setTaxAmount(taxAmount);
            taxInfo.setCessAmount(cessAmount);
            taxInfoList.add(taxInfo);
        }
        return taxInfoList;
    }


    private void validateEmployeeData(Employeedto employeeDto) {
        // Perform validation here
        if (employeeDto.getEmployeeID() == null) {
            throw new IllegalArgumentException("Employee ID cannot be null");
        }
        // Similarly, validate other fields
    }

    private double calculateYearlySalary(Date doj, BigDecimal monthlySalary) {
        LocalDate joinDate = doj.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        int monthsWorked = (currentDate.getYear() - joinDate.getYear()) * 12 + currentDate.getMonthValue() - joinDate.getMonthValue() + 1;
        return monthlySalary.doubleValue() * monthsWorked;
    }

    private double calculateTaxAmount(double yearlySalary) {
        if (yearlySalary <= 250000) {
            return 0;
        } else if (yearlySalary > 250000 && yearlySalary <= 500000) {
            return (yearlySalary - 250000) * 0.05;
        } else if (yearlySalary > 500000 && yearlySalary <= 1000000) {
            return 12500 + (yearlySalary - 500000) * 0.1;
        } else {
            return 62500 + (yearlySalary - 1000000) * 0.2;
        }
    }

    private double calculateCessAmount(double yearlySalary) {
        if (yearlySalary > 2500000) {
            return (yearlySalary - 2500000) * 0.02; // Collect 2% cess for the amount more than 2500000
        }
        return 0;
    }
}

