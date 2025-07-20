package com.nicat.suman.mapper;

import com.nicat.suman.dao.entity.Customer;
import com.nicat.suman.model.dto.request.CustomerUpdateRequest;
import com.nicat.suman.model.dto.response.CustomerAddResponse;
import com.nicat.suman.model.dto.response.CustomerSearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    CustomerAddResponse toCustomerAddResponse(Customer customer);

    void updateEntityField(CustomerUpdateRequest customerUpdateRequest,
                           @MappingTarget Customer customer);

    List<CustomerSearchResponse> toCustomerSearchResponse(List<Customer> customerList);
}
