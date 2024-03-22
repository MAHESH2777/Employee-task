package com.employee.modal;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;



public class Employeedto {
    private Integer employeeID;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> phoneNumbers;
    private Date DOJ;
    private BigDecimal salary;

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Date getDOJ() {
        return DOJ;
    }

    public void setDOJ(Date dOJ) {
        DOJ = dOJ;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}



 

