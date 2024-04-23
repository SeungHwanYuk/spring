package dw.wholesale_company.controller;

import dw.wholesale_company.Dto.OrderDto;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OrderController {
    OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrder(){
        return new ResponseEntity<>(orderService.getAllOrder(), HttpStatus.OK);
    }

    @GetMapping("/orders/orderlist_by_date/{localDate}")
    public ResponseEntity<List<OrderDto>> getOrderListByDate(@PathVariable LocalDate localDate) {
        return new ResponseEntity<>(orderService.getOrderListByDate(localDate),HttpStatus.OK);
    }
}
