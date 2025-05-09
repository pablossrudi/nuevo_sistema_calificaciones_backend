package com.pablossrudi.calificaciones.services;

import com.pablossrudi.calificaciones.dtos.UserChangeStatusDTO;
import com.pablossrudi.calificaciones.dtos.UserCreateDTO;
import com.pablossrudi.calificaciones.dtos.UserResponseDTO;

import java.util.List;

public interface IUserService {
    UserResponseDTO createUser(UserCreateDTO userCreateDTO);
    UserResponseDTO getUserById(String id);
    UserResponseDTO changeUserStatus(String id, UserChangeStatusDTO userChangeStatusDTO);
    List<UserResponseDTO> getAllUsers();
}
