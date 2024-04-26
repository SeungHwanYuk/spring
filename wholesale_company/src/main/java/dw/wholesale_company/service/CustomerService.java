package dw.wholesale_company.service;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Mileage;
import dw.wholesale_company.repository.CustomerRepository;
import dw.wholesale_company.repository.MileageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {
    CustomerRepository customerRepository;
    MileageRepository mileageRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, MileageRepository mileageRepository) {
        this.customerRepository = customerRepository;
        this.mileageRepository = mileageRepository;
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
        double avg = (double) sum / (double) customerList.size();
        return customerList
                .stream()
                .filter(customer -> customer.getMileage() > avg)
                .collect(Collectors.toList());
    }

    public List<Customer> getCustomerByMileageGrade(String grade) {
        List<Customer> customerList = customerRepository.findAll();
        Optional<Mileage> mileageList = mileageRepository.findById(grade);
//        long lowLimit = 0;
//        long highLimit = 0;
//        for (int i = 0; i < mileageList.size(); i++) {
//            if (mileageList.get(i).getMileageGrade().equals(grade))
//                lowLimit = mileageList.get(i).getLowLimit();
//                highLimit = mileageList.get(i).getHighLimit();
//        }
//
//        long finalLowLimit = lowLimit;
//        long finalHighLimit = highLimit;
        return customerList.stream()
                .filter(customer -> customer.getMileage() >= mileageList.get().getLowLimit()
                        && customer.getMileage() <= mileageList.get().getHighLimit())
                .collect(Collectors.toList());
    }
}

