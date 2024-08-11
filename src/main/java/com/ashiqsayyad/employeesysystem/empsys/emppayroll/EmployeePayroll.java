package com.ashiqsayyad.employeesysystem.empsys.emppayroll;

import java.math.BigDecimal;


import com.ashiqsayyad.employeesysystem.empsys.empenroll.Employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_payroll")
public class EmployeePayroll {
	
	public static  enum PAY_FREQ { MONTHLY , HALF_MONTHLY};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @Column(name = "pay_date", nullable = false)
    private String payFrequency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",referencedColumnName="empid", nullable = false)
    private Employee employee;

    // Constructors
    public EmployeePayroll() {
    }

    public EmployeePayroll(BigDecimal salary, String payFrequency, Employee employee) {
        this.salary = salary;
        this.payFrequency = payFrequency;
        this.employee = employee;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPayFrequency() {
        return payFrequency;
    }

    public void setPayFrequency(String payFrequency) {
        this.payFrequency = payFrequency;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
