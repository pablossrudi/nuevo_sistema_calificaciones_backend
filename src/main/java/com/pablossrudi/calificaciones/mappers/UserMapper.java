package com.pablossrudi.calificaciones.mappers;

import com.pablossrudi.calificaciones.dtos.UserResponseDTO;
import com.pablossrudi.calificaciones.models.Role;
import com.pablossrudi.calificaciones.models.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public static UserResponseDTO toDTO(User user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .rut(user.getRut())
                .email(user.getEmail())
                .isActive(user.getIsActive())
                .roles(user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet()))
                .build();
    }
}
