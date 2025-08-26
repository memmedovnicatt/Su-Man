package com.nicat.suman.controller;


import com.nicat.suman.model.dto.request.CustomerAddRequest;
import com.nicat.suman.model.dto.request.CustomerUpdateRequest;
import com.nicat.suman.model.dto.response.CustomerAddResponse;
import com.nicat.suman.model.dto.response.CustomerListResponse;
import com.nicat.suman.model.dto.response.CustomerResponse;
import com.nicat.suman.model.dto.response.CustomerSearchResponse;
import com.nicat.suman.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.config.RepositoryConfigurationSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "Customer Controller", description = "Handles customer operations")
@MyRestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {
    CustomerService customerService;

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

    @GetMapping("/search-by-name-surname")
    public List<CustomerSearchResponse> searchByNameAndSurname(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname) {
        return customerService.searchByNameAndSurname(name, surname);
    }

    @GetMapping("/search-by-phone")
    public List<CustomerSearchResponse> searchByPhoneNumber(
            @RequestParam(required = false) String phoneNumber) {
        return customerService.searchByPhoneNumber(phoneNumber);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable Long id) {
        CustomerResponse customerResponse = customerService.getById(id);
        return ResponseEntity.ok(customerResponse);
    }


    @GetMapping("/export")
    public void generateExcel(HttpServletResponse response) throws IOException {
        LocalDate today = LocalDate.now();
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=customers_" + today + ".xls";
        response.setHeader(headerKey, headerValue);
        customerService.generateExcel(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerListResponse>> getAll() {
        List<CustomerListResponse> customerListResponses = customerService.getAll();
        return ResponseEntity.ok(customerListResponses);
    }

    //borcu olan musterileri getiren endpoint
}