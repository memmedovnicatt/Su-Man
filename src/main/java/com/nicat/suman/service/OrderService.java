package com.nicat.suman.service;

import com.nicat.suman.dao.entity.Customer;
import com.nicat.suman.dao.entity.Order;
import com.nicat.suman.dao.entity.User;
import com.nicat.suman.dao.repository.CustomerRepository;
import com.nicat.suman.dao.repository.OrderRepository;
import com.nicat.suman.dao.repository.UserRepository;
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

    public OrderAddResponse add(OrderAddRequest orderAddRequest) {
        Customer currentCustomer = customerRepository.findById(orderAddRequest.getCustomerId())
                .orElseThrow(() -> new NotFoundException
                        ("customer was not found with id:" + orderAddRequest.getCustomerId()));
        log.info("customer was found with id:{}", orderAddRequest.getCustomerId());
        User courier = userRepository.findById(orderAddRequest.getCourierId())
                .orElseThrow(() -> new NotFoundException
                        ("courier was not found with id:" + orderAddRequest.getCourierId()));
        log.info("courier was found with id:{}", orderAddRequest.getCourierId());

        Long totalCarboyCount = orderAddRequest.getCarboyCount();
        Double totalCarboyPrice = totalCarboyCount * currentCustomer.getPrice();
        Order order = Order.builder()
                .price(totalCarboyPrice)
                .customer(currentCustomer)
                .carboyCount(totalCarboyCount)
                .user(courier)
                .build();
        orderRepository.save(order);
        OrderAddResponse orderAddResponse = new OrderAddResponse();
        orderAddResponse.setCustomerAddress(currentCustomer.getAddress());
        orderAddResponse.setCustomerName(currentCustomer.getName());
        orderAddResponse.setCustomerSurname(currentCustomer.getSurname());
        orderAddResponse.setCustomerPhoneNumber(currentCustomer.getPhoneNumber());
        orderAddResponse.setTotalCarboyCount(totalCarboyCount);
        orderAddResponse.setTotalPrice(totalCarboyPrice);
        return orderAddResponse;
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
        if (orderUpdateRequest.getCarboyCount() == null) {
            log.warn("carboyCount is null");
            throw new NullPointerException("carboyCount is null");
        }
        Long customerId = currentOrder.getCustomer().getId();
        Double carboyPrice = currentOrder.getCustomer().getPrice();
        Double totalCarboyPrice = orderUpdateRequest.getCarboyCount() * carboyPrice;
        log.info("customerId:{} ", customerId);
        log.info("carboyPrice:{} ", carboyPrice);
        currentOrder.setCarboyCount(orderUpdateRequest.getCarboyCount());
        currentOrder.setPrice(orderUpdateRequest.getCarboyCount() * carboyPrice);
        orderRepository.save(currentOrder);
        OrderUpdateResponse orderUpdateResponse = new OrderUpdateResponse();
        orderUpdateResponse.setCustomerAddress(currentOrder.getCustomer().getAddress());
        orderUpdateResponse.setCustomerName(currentOrder.getCustomer().getName());
        orderUpdateResponse.setCustomerPhoneNumber(currentOrder.getCustomer().getPhoneNumber());
        orderUpdateResponse.setTotalPrice(totalCarboyPrice);
        orderUpdateResponse.setTotalCarboyCount(orderUpdateRequest.getCarboyCount());
        orderUpdateResponse.setCustomerSurname(currentOrder.getCustomer().getSurname());
        return orderUpdateResponse;
    }

    public OrderResponse getById(Long id) {
        Order currentOrder = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("order was not found with id:" + id));
        log.info("order id:{} was found", id);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setCustomerAddress(currentOrder.getCustomer().getAddress());
        orderResponse.setCustomerName(currentOrder.getCustomer().getName());
        orderResponse.setCustomerPhoneNumber(currentOrder.getCustomer().getPhoneNumber());
        orderResponse.setTotalPrice(currentOrder.getPrice());
        orderResponse.setTotalCarboyCount(currentOrder.getCarboyCount());
        orderResponse.setCustomerSurname(currentOrder.getCustomer().getSurname());
        return orderResponse;
    }
}