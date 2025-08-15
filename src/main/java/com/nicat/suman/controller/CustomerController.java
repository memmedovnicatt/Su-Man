package com.nicat.suman.controller;


import com.nicat.suman.model.dto.request.CustomerAddRequest;
import com.nicat.suman.model.dto.request.CustomerUpdateRequest;
import com.nicat.suman.model.dto.response.CustomerAddResponse;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "Customer Controller", description = "Handles customer operations")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {
    CustomerService customerService;

    @Operation(summary = "Add a new customer", description = "Creates a new customer with provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid customer data")
    })
    @PostMapping("/add")
    public ResponseEntity<CustomerAddResponse> add(@RequestBody CustomerAddRequest customerAddRequest) {
        CustomerAddResponse customerAddResponse = customerService.add(customerAddRequest);
        return ResponseEntity.ok(customerAddResponse);
    }

    @Operation(summary = "Delete a customer", description = "Deletes the customer by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Customer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }

    @Operation(summary = "Update customer info", description = "Partially updates customer fields by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody CustomerUpdateRequest customerUpdateRequest,
                       @PathVariable Long id) {
        customerService.update(customerUpdateRequest, id);
    }

    @Operation(summary = "Get total customer count",
            description = "Returns the number of customers in the system")
    @ApiResponse(responseCode = "200", description = "Count retrieved successfully")
    @GetMapping("/count")
    public Long getCustomerCount() {
        return customerService.countUsers();
    }


    @Operation(summary = "Search customers by name and surname",
            description = "Returns customers matching optional name and/or surname parameters")
    @ApiResponse(responseCode = "200", description = "Search completed successfully")
    @GetMapping("/search-by-name-surname")
    public List<CustomerSearchResponse> searchByNameAndSurname(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname) {
        return customerService.searchByNameAndSurname(name, surname);
    }

    @Operation(summary = "Search customer by phone number",
            description = "Returns customers matching given phone number")
    @ApiResponse(responseCode = "200", description = "Search completed successfully")
    @GetMapping("/search-by-phone")
    public List<CustomerSearchResponse> searchByPhoneNumber(
            @RequestParam(required = false) String phoneNumber) {
        return customerService.searchByPhoneNumber(phoneNumber);
    }

    @Operation(summary = "Get customer by ID", description = "Fetches a single customer by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable Long id) {
        CustomerResponse customerResponse = customerService.getById(id);
        return ResponseEntity.ok(customerResponse);
    }

    @Operation(summary = "Export customers to Excel",
            description = "This endpoint exports a customer list in " +
                    ".xls format and presents it to the user as a download.")
    @GetMapping("/export")
    public void generateExcel(HttpServletResponse response) throws IOException {
        LocalDate today = LocalDate.now();
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=customers_" + today + ".xls";
        response.setHeader(headerKey, headerValue);
        customerService.generateExcel(response);
    }
}