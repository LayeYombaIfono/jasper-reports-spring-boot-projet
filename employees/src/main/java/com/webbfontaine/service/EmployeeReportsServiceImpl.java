package com.webbfontaine.service;

import com.webbfontaine.model.Employee;
import com.webbfontaine.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@AllArgsConstructor
@Service
public class EmployeeReportsServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;



    public byte[]exportEmployeesReport() throws JRException{

        List<Employee> employees = employeeRepository.findAll();



        InputStream inputStream = getClass().getResourceAsStream("/reports/employees_list.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);


        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CreatedBy", "Laye Yomba IFONO");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
}
