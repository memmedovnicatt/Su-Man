package com.nicat.suman.mapper;

import com.nicat.suman.dao.entity.Order;
import com.nicat.suman.model.dto.response.OrderAddResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    @Mapping(source = "customer.name", target = "customerName")
    @Mapping(source = "customer.surname", target = "customerSurname")
    @Mapping(source = "customer.address", target = "customerAddress")
    @Mapping(source = "customer.phoneNumber", target = "customerPhoneNumber")
    @Mapping(source = "carboyCount", target = "totalCarboyCount")
    @Mapping(source = "price", target = "totalPrice")
    OrderAddResponse toOrderAddResponse(Order order);
}