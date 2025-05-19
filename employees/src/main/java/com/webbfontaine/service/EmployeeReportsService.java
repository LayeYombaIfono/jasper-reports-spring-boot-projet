package com.webbfontaine.service;

import com.webbfontaine.model.Employee;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeReportsService {
    public byte[]exportEmployeesReport() throws JRException{
        List<Employee> employees = List.of(
                new Employee("Laye Yomba", "IFONO", "layeyomba@example.com", "IT", "Développeur", "621952082", "Guinée", 4000.0),
                new Employee("Jean", "IFONO", "jean@example.com", "IT", "Développeur", "0987654321", "Guinée", 4000.0),
                new Employee("Marie", "Kamano", "marie@example.com", "Finance", "Analyste", "0612345678", "Guinée", 3700.0),
                new Employee("Christine", "Mansaré", "christine@example.com", "Marketing", "Communication", "0789456123", "Guinée", 3500.0)
        );

        InputStream inputStream = getClass().getResourceAsStream("/reports/employees_list.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);


        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CreatedBy", "Laye Yomba IFONO");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
