package com.service.mapper;


import com.dto.request.IncidentReportVO;
import com.dto.request.UserVO;
import com.model.IncidentReport;
import com.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    UserVO toDto(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserVO userVO);

    List<UserVO> toDtoList(List<User> userList);
}

