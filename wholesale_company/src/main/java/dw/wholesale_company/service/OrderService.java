package dw.wholesale_company.service;

import dw.wholesale_company.Dto.OrderDto;
import dw.wholesale_company.exception.ResourceNotFoundException;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public List<OrderDto> getOrderListByDate(LocalDate localDate) {
        List<Order> orderList = orderRepository.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();
//        LocalDate localDate = LocalDate.of(2021,05,01);
        for (int i = 0; i < orderList.size(); i++) {
            OrderDto orderDto = new OrderDto();
            if (orderList.get(i).getOrderDate().isAfter(localDate)) {
                orderDtoList.add(orderDto.toOrderDtoFromOrder(orderList.get(i)));
            }
        }
        if(orderDtoList.isEmpty()) {
            throw new ResourceNotFoundException("Order","Date"," ");
        } else {
            return orderDtoList;
        }
    }
}
