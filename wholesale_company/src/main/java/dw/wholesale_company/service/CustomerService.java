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
        List<Mileage> mileageList = mileageRepository.findAll();
        List<Customer> customerListFinal = new ArrayList<>();
        long lowlimit = 0;
        long highlimit = 0;
        for (int i = 0; i < mileageList.size(); i++) {
            if (grade.equalsIgnoreCase(mileageList.get(i).getMileageGrade()))
                lowlimit = mileageList.get(i).getLowLimit();
            highlimit = mileageList.get(i).getHighLimit();
        }

        long finalLowlimit = lowlimit;
        long finalHighlimit = highlimit;

        return customerList.stream()
                .filter(customer -> customer.getMileage() >= finalLowlimit && customer.getMileage() <= finalHighlimit)
                .collect(Collectors.toList());



//
//        for (int i = 0; i < customerList.size(); i++) {
//            if (customerList.get(i).getMileage() > low) &&
//                    customerList.get(i).getMileage() < mileageList
//
//        }


//        customerList.stream()
//                .filter()
//                .map()

    }
}

