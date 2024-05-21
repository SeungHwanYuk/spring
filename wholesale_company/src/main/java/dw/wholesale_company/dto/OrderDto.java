package dw.wholesale_company.dto;

import dw.wholesale_company.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
