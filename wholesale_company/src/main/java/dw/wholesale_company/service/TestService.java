package dw.wholesale_company.service;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Employee;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.repository.CustomerRepository;
import dw.wholesale_company.repository.EmployeeRepository;
import dw.wholesale_company.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TestService {

    OrderRepository orderRepository;
    EmployeeRepository employeeRepository;
    CustomerRepository customerRepository;

    @Autowired
    public TestService(OrderRepository orderRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
    }

    //1. 도시이름(city)을 매개변수로 받아 그 도시출신의 사원 정보를 보이시오.
    public List<Employee> getEmployeeByCity(String city) {
        return employeeRepository.findAll()
                .stream()
                .filter(employee -> employee.getCity().contains(city))
                .collect(Collectors.toList());
    }

    //2. 주문번호를 매개변수(orderId)로 받아 주문한 고객의 정보를 보이시오.
    public Customer getCustomerByOrderId(String id) {

        return orderRepository.findById(id)
                .filter(order -> order.getOrderId().equals(id))
                .map(Order::getCustomer)
                .get();
    }

    //3. 주문년도(orderYear)를 매개변수로 받아 그 해의 주문건수(int)를 보이시오.
    public int getOrderNumByOrderYear(int orderYear) {
        List<Order> orderList = orderRepository.findAll();
        List<String> integerList = new ArrayList<>();
        String year = String.valueOf(orderYear);
        System.out.println(year);
        int count = 0;
        for (int i = 0; i < orderList.size(); i++) {
            integerList.add(orderList.get(i).getOrderDate().toString());
        }
        for (int i = 0; i < integerList.size(); i++) {
            if (integerList.get(i).contains(year)) {
                count = count + 1;
            }
        }
        return count;
    }

    //4. 직위(position)와 나이대(year)를 매개변수로 받아 해당정보에 맞는 사원들의 정보를 보이시오.
    // 예를 들어 20대는 매개변수 year=20 이고 나이가 20살이상 30살미만을 의미함.
    // 나이계산은 (올해 - 태어난해)로 계산.
    public List<Employee> getEmployeeByPositionAndYear(String position, int year) {
        List<Employee> employeeList = employeeRepository.findAll();
        int yearLayers = year + 9;
        return employeeRepository.findAll()
                .stream()
                .filter(employee -> employee.getPosition().equals(position))
                .filter(employee -> (LocalDate.now().getYear() - employee.getBirthDate().getYear() >= year) &&
                        (LocalDate.now().getYear() - employee.getBirthDate().getYear()) <= yearLayers)
                .collect(Collectors.toList());
    }
}