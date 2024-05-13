package com.ashiqsayyad.employeesysystem.empsys.empenroll;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpRegisterController {
	
	
	@GetMapping("hello")
	public String hello() {
		return "Welcome to Employee Management System";
	}
	
	@PostMapping("employees")
	public EmployeeBean registerEmployee(@RequestBody EmployeeBean empBean) {
		 return empBean;
	}

}
