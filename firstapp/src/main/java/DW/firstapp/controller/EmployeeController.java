package DW.firstapp.controller;

import DW.firstapp.model.Employee;
import DW.firstapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    // 의존성 주입
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/api/employee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        // service 코드 위치
        return employeeService.saveEmployee(employee);
    }
}
