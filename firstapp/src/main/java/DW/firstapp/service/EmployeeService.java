package DW.firstapp.service;

import DW.firstapp.model.Employee;
import DW.firstapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    // 의존성 주입
    @Autowired
    EmployeeRepository employeeRepository;
    public Employee saveEmployee(Employee employee){
        // repository 코드 위치 (save)
        employeeRepository.save(employee);
        return employee;
    }
}
