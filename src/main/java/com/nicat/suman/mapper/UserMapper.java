package com.nicat.suman.mapper;

import com.nicat.suman.dao.entity.User;
import com.nicat.suman.model.dto.response.LoginResponse;
import com.nicat.suman.model.dto.response.RegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    RegisterResponse toRegisterResponseDto(User user);

    LoginResponse toLoginResponse(User user);
}