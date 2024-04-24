package dw.wholesale_company.service;

import dw.wholesale_company.model.Employee;
import dw.wholesale_company.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {
    EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    //사원의 직위가 '사원'인 사람들 중에서 가장 최근에 입사한 사원의 정보
    public List<Employee> getEmployeeByPositionWithLastHireDate(String position) {
        return employeeRepository.findAll()
                .stream().filter(employee -> employee.getPosition().equals(position))
                .sorted(Comparator.comparing(Employee::getHireDate).reversed())
                .collect(Collectors.toList());

    }
}
