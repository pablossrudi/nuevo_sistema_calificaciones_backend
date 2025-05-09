package com.pablossrudi.calificaciones.services;

import com.pablossrudi.calificaciones.dtos.UserChangeStatusDTO;
import com.pablossrudi.calificaciones.dtos.UserCreateDTO;
import com.pablossrudi.calificaciones.dtos.UserResponseDTO;
import com.pablossrudi.calificaciones.mappers.UserMapper;
import com.pablossrudi.calificaciones.models.Role;
import com.pablossrudi.calificaciones.models.User;
import com.pablossrudi.calificaciones.repositores.RoleRepository;
import com.pablossrudi.calificaciones.repositores.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserResponseDTO createUser(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setUserName(userCreateDTO.getUserName());
        user.setRut(userCreateDTO.getRut());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setEmail(userCreateDTO.getEmail());
        user.setIsActive(true);
        user.setRoles(mapRoles(userCreateDTO.getRoles()));

        User savedUser = userRepository.save(user);

        return UserMapper.toDTO(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDTO(user);
    }

    @Override
    @Transactional
    public UserResponseDTO changeUserStatus(String id, UserChangeStatusDTO userChangeStatusDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (userChangeStatusDTO.getIsActive() != null){
            user.setIsActive(userChangeStatusDTO.getIsActive());
            User updatedUser = userRepository.save(user);
            return UserMapper.toDTO(updatedUser);
        } else {
            return UserMapper.toDTO(user);
        }
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    private Set<Role> mapRoles(Set<String> roles) {
        return roles.stream()
                .map(nombre -> roleRepository.findByName(nombre.toUpperCase())
                        .orElseThrow(() -> new RuntimeException("Role not found: " + nombre)))
                .collect(Collectors.toSet());
    }
}
