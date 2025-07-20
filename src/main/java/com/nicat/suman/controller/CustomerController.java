package com.nicat.suman.controller;


import com.nicat.suman.dao.entity.Customer;
import com.nicat.suman.model.dto.request.CustomerAddRequest;
import com.nicat.suman.model.dto.request.CustomerUpdateRequest;
import com.nicat.suman.model.dto.response.CustomerAddResponse;
import com.nicat.suman.model.dto.response.CustomerSearchResponse;
import com.nicat.suman.service.CustomerService;
import com.nicat.suman.util.SecurityUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {
    CustomerService customerService;
    private final SecurityUtil securityUtil;

    @PostMapping("/add")
    public ResponseEntity<CustomerAddResponse> add(@RequestBody CustomerAddRequest customerAddRequest) {
        CustomerAddResponse customerAddResponse = customerService.add(customerAddRequest);
        return ResponseEntity.ok(customerAddResponse);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody CustomerUpdateRequest customerUpdateRequest,
                       @PathVariable Long id) {
        customerService.update(customerUpdateRequest, id);
    }

    @GetMapping("/count")
    public Long getCustomerCount() {
        return customerService.countUsers();
    }

    @GetMapping("/customers/search-by-name")
    public List<CustomerSearchResponse> searchByNameAndSurname(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname) {
        return customerService.searchByNameAndSurname(name, surname);
    }

    @GetMapping("/customers/search-by-phone")
    public List<CustomerSearchResponse> searchByPhoneNumber(
            @RequestParam(required = false) String phoneNumber) {
        return customerService.searchByPhoneNumber(phoneNumber);
    }
}