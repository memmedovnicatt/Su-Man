package com.nicat.suman.service;

import com.nicat.suman.dao.entity.Customer;
import com.nicat.suman.dao.repository.CustomerRepository;
import com.nicat.suman.mapper.CustomerMapper;
import com.nicat.suman.model.dto.request.CustomerAddRequest;
import com.nicat.suman.model.dto.request.CustomerUpdateRequest;
import com.nicat.suman.model.dto.response.CustomerAddResponse;
import com.nicat.suman.model.dto.response.CustomerResponse;
import com.nicat.suman.model.dto.response.CustomerSearchResponse;
import com.nicat.suman.model.exception.NotFoundException;
import com.nicat.suman.util.ExcelExport;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;
    ExcelExport excelExport;

    public CustomerAddResponse add(CustomerAddRequest customerAddRequest) {
        //phone number mutleq ferqli olmalidir
        log.info("Starting to add a new customer");
        Customer customer = Customer.builder()
                .name(customerAddRequest.getName())
                .surname(customerAddRequest.getSurname())
                .phoneNumber(customerAddRequest.getPhoneNumber())
                .price(customerAddRequest.getPrice())
                .address(customerAddRequest.getAddress())
                .build();
        customerRepository.save(customer);
        log.info("Customer successfully saved with ID: {}", customer.getId());
        return customerMapper.toCustomerAddResponse(customer);
    }

    public void delete(Long id) {
        log.info("Attempting to delete customer with ID: {}", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer id:" + id + " was not found"));
        customerRepository.deleteById(id);
        log.info("Customer with ID: {} successfully deleted", id);
    }

    public void update(CustomerUpdateRequest customerUpdateRequest, Long id) {
        log.info("Starting update for customer with ID: {}", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer id:" + id + " was not found"));
        customerMapper.updateEntityField(customerUpdateRequest, customer);
        customerRepository.save(customer);
        log.info("Customer with ID: {} successfully updated", id);
    }

    public Long countUsers() {
        return customerRepository.count();
    }

    public List<CustomerSearchResponse> searchByNameAndSurname(String name, String surname) {
        log.info("Searching for customers by name: '{}' and surname: '{}'", name, surname);
        List<Customer> customerList = customerRepository.findByNameAndSurname(name, surname)
                .orElseThrow(() -> new NotFoundException("not found"));
        if (customerList == null || customerList.isEmpty()) {
            log.warn("Customer list is empty or null. Cannot proceed with operation.");
            throw new NotFoundException("No customers found.");
        }
        log.info("Found {} customer(s)", customerList.size());
        return customerMapper.toCustomerSearchResponse(customerList);
    }

    public List<CustomerSearchResponse> searchByPhoneNumber(String phoneNumber) {
        log.info("Searching for customers by phone number: {}", phoneNumber);
        List<Customer> customerList = customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new NotFoundException("not found"));
        if (customerList == null || customerList.isEmpty()) {
            log.warn("Customer list was empty or null. Can not proceed with operation.");
            throw new NotFoundException("No customers found.");
        }
        log.info("Found {} customer(s) with phone number {}", customerList.size(), phoneNumber);
        return customerMapper.toCustomerSearchResponse(customerList);
    }

    public CustomerResponse getById(Long id) {
        log.info("getById method was started for CustomerService with id:{}", id);
        Customer currenCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("customer id:" + id + " was not found"));
        log.info("customer was found with id:{}", id);
        return customerMapper.toCustomerResponse(currenCustomer);
    }

    public void generateExcel(HttpServletResponse response) throws IOException {
        List<Customer> customers = customerRepository.findAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Customers Info");
        excelExport.createHeaderRow(workbook, sheet);
        excelExport.createDataRows(workbook, sheet, customers);
        excelExport.autoSizeColumns(sheet);
        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }
}