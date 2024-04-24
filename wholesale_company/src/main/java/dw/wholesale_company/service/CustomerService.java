package dw.wholesale_company.service;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {
    CustomerRepository customerRepository;
@Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomer() {
    return customerRepository.findAll();
    }


    //고객 전체의 평균마일리지보다 마일리지가 큰 고객 정보

    public List<Customer> getCustomerListByOverAvgMileage() {
    List<Customer> customerList = customerRepository.findAll();
    double sum = 0;
        for (int i = 0; i < customerList.size(); i++) {
            sum = sum + customerList.get(i).getMileage();
        }
        double avg = (double)sum / (double)customerList.size();
        return customerList
                .stream()
                .filter(customer -> customer.getMileage() > avg)
                .collect(Collectors.toList());
    }

}
