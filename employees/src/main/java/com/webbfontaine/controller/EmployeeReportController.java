package com.webbfontaine.controller;

import com.webbfontaine.service.EmployeeReportsService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeReportController {

    @Autowired
    private EmployeeReportsService employeeReportsService;

    @GetMapping()
    public ResponseEntity<byte[]> getEmployeesReport() throws JRException {
        byte[] pdfContent = employeeReportsService.exportEmployeesReport();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
               ContentDisposition.builder("inline")
                       .filename("employees.pdf").build()
        );

        return new  ResponseEntity<>(pdfContent, headers, HttpStatus.OK );
    }

}
