package org.vaadin.aes.model.mapper;

import org.vaadin.aes.model.concrete.User;
import org.vaadin.aes.model.dto.UserDataDto;

public class UserMapper {
    public static UserDataDto userToUserDataDto(User userData) {
        UserDataDto dto = new UserDataDto();

        dto.setId(userData.getId());
        dto.setFirstName(userData.getFirstName());
        dto.setLastName(userData.getLastName());
        dto.setEmail(userData.getEmail());
        dto.setPhoneNo(userData.getPhoneNo());
        return dto;
    }
}
