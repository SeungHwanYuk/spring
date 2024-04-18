package DW.firstapp.controller;

import DW.firstapp.model.Employee;
import DW.firstapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    // @Autowired  // 의존성 주입 (필드 주입)
    // 각 필드마다 @Autowired를 달아줘야한다
    EmployeeService employeeService;

    // 매개변수를 사용한 생성자
    @Autowired // 생성자 주입
    // 생성자 하나에 각 필드를 한꺼번에 묶어서 @Autowired는 한번만 선언해도 된다
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/api/employee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        // service 코드 위치
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("api/employee")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("api/employee/{id}")
    public Employee getEmployeeById(@PathVariable long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("api/employee/{id}")
    public Employee updateEmployeeById(@PathVariable long id,
                                       @RequestBody Employee employee) {
        return employeeService.updateEmployeeById(id, employee);
    }

    @DeleteMapping("api/employee/{id}")
    public Employee deleteEmployeeById(@PathVariable long id) {
        return employeeService.deleteEmployeeById(id);
    }
}
