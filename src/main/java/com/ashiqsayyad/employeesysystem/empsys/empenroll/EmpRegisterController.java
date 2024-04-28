package com.ashiqsayyad.employeesysystem.empsys.empenroll;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpRegisterController {
	
	
	@GetMapping("hello")
	public String hello() {
		return "Welcome to Employee Management System";
	}

}
