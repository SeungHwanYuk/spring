package DW.firstapp.service;

import DW.firstapp.model.Employee;
import DW.firstapp.repository.EmployeeRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

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

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long id) {
        // ID로 해당 데이터 찾기
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()){
            // 예외처리
            return null;
        } else {
            return employee.get();
        }
    }

    public Employee updateEmployeeById(long id,Employee employee) {
      Optional<Employee> employee1 = employeeRepository.findById(id);
      if (employee1.isPresent()) {
          // 데이터 업데이트
          employee1.get().setEmail(employee.getEmail());
          employee1.get().setFirstName(employee.getFirstName());
          employee1.get().setLastName(employee.getLastName());
          // 실제로 DB에 저장하기
          employeeRepository.save(employee1.get());

          return employee1.get();
      } else {
          return null;
      }
    }


    public Employee deleteEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
            return employee.get();
        } else {
            return null;
        }
    }
}
