package com.ashiqsayyad.employeesysystem.empsys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ashiqsayyad.employeesysystem.empsys.empenroll.EmployeeBean;
import com.ashiqsayyad.employeesysystem.empsys.empenroll.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class EmpCommandLineRunner implements CommandLineRunner {
	private static final Logger log = 
			LoggerFactory.getLogger(EmpCommandLineRunner.class);
	@Autowired
	EmployeeRepository empRepository;

	@Override
	public void run(String... args) throws Exception {
		
		EmployeeBean emp = new EmployeeBean();
		emp.setFirstName("Ashiq");
		emp.setLastName("Sayyad");
		emp.setDepartment("CTS");
		emp.setMobileNo("9010260000");
		emp.setTitle("Cloud Architect");
		emp.setAddress("lanco hils");
		emp.setEmail("ash@gmail.com");
		log.info("New Emp is created 1 : " + emp);
		empRepository.save(emp);
		
	     emp = new EmployeeBean();
		emp.setFirstName("Ashiq");
		emp.setLastName("Sayyad");
		emp.setDepartment("CTS");
		emp.setMobileNo("8790878144");
		emp.setTitle("Cloud Architect");
		emp.setAddress("lanco hils");
		emp.setEmail("ashk@gmail.com");
		log.info("New Emp is created 2 : " + emp);
		empRepository.save(emp);
		
		emp = new EmployeeBean();
		emp.setFirstName("Ashiq");
		emp.setLastName("Sayyad");
		emp.setDepartment("CTS");
		emp.setMobileNo("8790878144");
		emp.setTitle("Cloud Architect");
		emp.setAddress("lanco hils");
		emp.setEmail("ashk@gmail.com");
		log.info("New Emp is created  3: " + emp);
		empRepository.save(emp);
		
	}

}
