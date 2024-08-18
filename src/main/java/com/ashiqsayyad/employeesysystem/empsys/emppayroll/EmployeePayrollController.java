package com.ashiqsayyad.employeesysystem.empsys.emppayroll;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ashiqsayyad.employeesysystem.empsys.empenroll.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payroll")
public class EmployeePayrollController {

    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Get all payroll records
    @GetMapping
    public List<EmployeePayroll> getAllPayrolls() {
        return employeePayrollRepository.findAll();
    }

    // Get a payroll record by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeePayroll> getPayrollById(@PathVariable Long id) {
        Optional<EmployeePayroll> payroll = employeePayrollRepository.findById(id);
        return payroll.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Create a new payroll record
    @PostMapping
    public ResponseEntity<EmployeePayroll> createPayroll(@RequestBody EmployeePayroll employeePayroll) {
        // Ensure the associated employee exists
        if (employeeRepository.existsById(employeePayroll.getEmployee().getEmpid())) {
            EmployeePayroll savedPayroll = employeePayrollRepository.save(employeePayroll);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPayroll);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Update an existing payroll record
    @PutMapping("/{id}")
    public ResponseEntity<EmployeePayroll> updatePayroll(
            @PathVariable Long id,
            @RequestBody EmployeePayroll updatedPayroll) {

        Optional<EmployeePayroll> existingPayroll = employeePayrollRepository.findById(id);
        if (existingPayroll.isPresent()) {
            updatedPayroll.setId(id);  // Ensure the ID remains the same
            EmployeePayroll savedPayroll = employeePayrollRepository.save(updatedPayroll);
            return ResponseEntity.ok(savedPayroll);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete a payroll record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayroll(@PathVariable Long id) {
        if (employeePayrollRepository.existsById(id)) {
            employeePayrollRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
