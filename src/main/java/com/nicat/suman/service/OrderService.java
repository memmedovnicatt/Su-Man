package com.nicat.suman.service;

import com.nicat.suman.dao.entity.Customer;
import com.nicat.suman.dao.entity.Order;
import com.nicat.suman.dao.entity.User;
import com.nicat.suman.dao.repository.CustomerRepository;
import com.nicat.suman.dao.repository.OrderRepository;
import com.nicat.suman.dao.repository.UserRepository;
import com.nicat.suman.mapper.OrderMapper;
import com.nicat.suman.model.dto.request.OrderAddRequest;
import com.nicat.suman.model.dto.request.OrderUpdateRequest;
import com.nicat.suman.model.dto.response.OrderAddResponse;
import com.nicat.suman.model.dto.response.OrderResponse;
import com.nicat.suman.model.dto.response.OrderUpdateResponse;
import com.nicat.suman.model.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {
    CustomerRepository customerRepository;
    UserRepository userRepository;
    OrderRepository orderRepository;
    OrderMapper orderMapper;

    public OrderAddResponse add(OrderAddRequest orderAddRequest) {
        log.info("add method was started for OrderService with this request:{}",
                orderAddRequest);
        isValidCount(orderAddRequest.getCarboyCount());
        Customer currentCustomer = customerRepository.findById(orderAddRequest.getCustomerId())
                .orElseThrow(() -> new NotFoundException
                        ("customer was not found with id:" + orderAddRequest.getCustomerId()));
        log.info("customer was found with id:{}", orderAddRequest.getCustomerId());
        User courier = userRepository.findById(orderAddRequest.getCourierId())
                .orElseThrow(() -> new NotFoundException
                        ("courier was not found with id:" + orderAddRequest.getCourierId()));
        log.info("courier was found with id:{}", orderAddRequest.getCourierId());
        Long totalCarboyCount = orderAddRequest.getCarboyCount();
        Order order = Order.builder()
                .price(totalCarboyCount * currentCustomer.getPrice())
                .customer(currentCustomer)
                .carboyCount(totalCarboyCount)
                .user(courier)
                .orderDate(orderAddRequest.getOrderDate())
                .build();
        orderRepository.save(order);
        return orderMapper.toOrderAddResponse(order);
    }

    public void delete(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("order was not found with id:" + id));
        log.info("order was found with id:{}", id);
        orderRepository.deleteById(id);
    }

    public OrderUpdateResponse update(Long id, OrderUpdateRequest orderUpdateRequest) {
        Order currentOrder = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("order was not found with id:" + id));
        isValidCount(orderUpdateRequest.getCarboyCount());

        Long customerId = currentOrder.getCustomer().getId();
        Double carboyPrice = currentOrder.getCustomer().getPrice();
        Double totalCarboyPrice = orderUpdateRequest.getCarboyCount() * carboyPrice;
        log.info("customerId:{} ", customerId);
        log.info("carboyPrice:{} ", carboyPrice);

        currentOrder.setCarboyCount(orderUpdateRequest.getCarboyCount());
        currentOrder.setPrice(orderUpdateRequest.getCarboyCount() * carboyPrice);
        orderRepository.save(currentOrder);

        return OrderUpdateResponse.builder()
                .customerAddress(currentOrder.getCustomer().getAddress())
                .customerName(currentOrder.getCustomer().getName())
                .customerSurname(currentOrder.getCustomer().getSurname())
                .customerPhoneNumber(currentOrder.getCustomer().getPhoneNumber())
                .totalPrice(totalCarboyPrice)
                .totalCarboyCount(orderUpdateRequest.getCarboyCount())
                .build();
    }

    public OrderResponse getById(Long id) {
        Order currentOrder = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("order was not found with id:" + id));
        log.info("order id:{} was found", id);

        return OrderResponse.builder()
                .customerAddress(currentOrder.getCustomer().getAddress())
                .customerName(currentOrder.getCustomer().getName())
                .customerSurname(currentOrder.getCustomer().getSurname())
                .customerPhoneNumber(currentOrder.getCustomer().getPhoneNumber())
                .totalPrice(currentOrder.getPrice())
                .totalCarboyCount(currentOrder.getCarboyCount())
                .build();
    }

    // util methods
    public static boolean isValidCount(Long carboyCount) {
        if (carboyCount == null || carboyCount < 0) {
            log.warn("carboy count is null");
            throw new NullPointerException("carboyCount is null");
        }
        return true;
    }
}