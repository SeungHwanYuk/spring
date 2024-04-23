package dw.wholesale_company.Dto;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Employee;
import dw.wholesale_company.model.Order;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderDto {


    private String orderId;

    private String customer;

    private String employee;

    private LocalDate orderDate;

    private LocalDate requestDate;

    private LocalDate shippingDate;

    public OrderDto toOrderDtoFromOrder(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setCustomer(order.getCustomer().getCustomerId());
        orderDto.setEmployee(order.getEmployee().getEmployeeId());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setRequestDate(order.getRequestDate());
        orderDto.setShippingDate(order.getShippingDate());
        return orderDto;
    }
}
