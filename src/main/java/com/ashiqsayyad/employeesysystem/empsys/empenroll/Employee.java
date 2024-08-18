package com.ashiqsayyad.employeesysystem.empsys.empenroll;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import com.ashiqsayyad.employeesysystem.empsys.emppayroll.EmployeePayroll;

@Entity
@Table(name = "employees")
public class Employee {
	
	@Column(name = "first_name")
	public String firstName;
	
	@Column(name = "last_name")
	public String lastName;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long empid;
	public String title;
	public String department;
	public String address;
	 @Column(nullable = false, unique = true)
	public String mobileNo;
	 @Column(nullable = false, unique = true)
	public String email;
	 
	 @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	 private Set<EmployeePayroll> payrolls = new HashSet<>();
	
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
	public Long getEmpid() {
		return empid;
	}
	public void setEmpid(Long empid) {
		this.empid = empid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public Set<EmployeePayroll> getPayrolls() {
        return payrolls;
    }

    public void setPayrolls(Set<EmployeePayroll> payrolls) {
        this.payrolls = payrolls;
    }

    public void addPayroll(EmployeePayroll payroll) {
        payrolls.add(payroll);
        payroll.setEmployee(this);
    }

    public void removePayroll(EmployeePayroll payroll) {
        payrolls.remove(payroll);
        payroll.setEmployee(null);
    }
    
	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", empid=" + empid + ", title="
				+ title + ", department=" + department + ", address=" + address + ", mobileNo=" + mobileNo + ", email=\" + email + \"]";
	}

}
