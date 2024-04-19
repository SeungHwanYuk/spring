package DW.firstapp.controller;

import DW.firstapp.model.Employee;
import DW.firstapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {

        // service 코드 위치
        return new ResponseEntity<>(employeeService.saveEmployee(employee),
                HttpStatus.OK);
    }

    @GetMapping("api/employee")
    public ResponseEntity<List<Employee>> getAllEmployees() {

        return new ResponseEntity<>(employeeService.getAllEmployees(),HttpStatus.OK);
    }

    @GetMapping("api/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.OK);
    }

    @PutMapping("api/employee/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable long id,
                                       @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.updateEmployeeById(id, employee),HttpStatus.OK);
    }

    @DeleteMapping("api/employee/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable long id) {
        return new ResponseEntity<>(employeeService.deleteEmployeeById(id),HttpStatus.OK);
    }
}
