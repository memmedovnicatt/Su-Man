package com.nicat.suman.mapper;

import com.nicat.suman.dao.entity.Customer;
import com.nicat.suman.model.dto.request.CustomerUpdateRequest;
import com.nicat.suman.model.dto.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    CustomerAddResponse toCustomerAddResponse(Customer customer);

    void updateEntityField(CustomerUpdateRequest customerUpdateRequest,
                           @MappingTarget Customer customer);

    List<CustomerSearchResponse> toCustomerSearchResponse(List<Customer> customerList);

    CustomerResponse toCustomerResponse(Customer currenCustomer);

    List<CustomerListResponse> toCustomerListResponse(List<Customer> customerList);

    List<CustomerLoanResponse> toLCustomerLoanResponse(List<Customer> listCustomer);
}