package com.ashiqsayyad.employeesysystem.empsys.empenroll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("hello")
	public String hello() {
		return "Welcome to Emplyee Management System";
	}
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp) {
		Employee empCreated =  empRepo.save(emp);
		return new ResponseEntity<>(empCreated, HttpStatus.CREATED);
		 
	}
	
	@GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = empRepo.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
	
	// Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Optional<Employee> employee = empRepo.findById(id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
 // Update an existing employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employeeDetails) {
    	Employee updatedEmployee = null;        
    	Optional<Employee> optionalEmployee = empRepo.findById(id);
    	if (optionalEmployee.isPresent()) {
    		updatedEmployee = optionalEmployee.get();
    		updatedEmployee.setFirstName(employeeDetails.getFirstName());
    		updatedEmployee.setLastName(employeeDetails.getLastName());
    		updatedEmployee.setEmail(employeeDetails.getEmail());
    		updatedEmployee.setDepartment(employeeDetails.getDepartment());
    		updatedEmployee = empRepo.save(updatedEmployee);
    		return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}       

    }
    
 // Delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Long id) {
    	boolean isDeleted = false ;
    	if (empRepo.existsById(id)) {
    		empRepo.deleteById(id);
    		isDeleted = true;
    	}
    	if (isDeleted) {
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	} else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }

}
