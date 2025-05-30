package com.webbfontaine.controller;

import com.webbfontaine.model.Employee;
import com.webbfontaine.service.EmployeeReportsServiceImpl;
import lombok.AllArgsConstructor;

import net.sf.jasperreports.engine.JRException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeReportController {

    private final EmployeeReportsServiceImpl employeeReportsServiceImpl;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){

        Employee savedEmployee = employeeReportsServiceImpl.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

    }


    @GetMapping()
    public ResponseEntity<byte[]> getEmployeesReport() throws JRException {
        byte[] pdfContent = employeeReportsServiceImpl.exportEmployeesReport();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "employees.pdf");

        return ResponseEntity.ok().headers(headers).body(pdfContent);
    }

}
