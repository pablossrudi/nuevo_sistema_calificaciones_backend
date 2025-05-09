package com.pablossrudi.calificaciones.controllers;

import com.pablossrudi.calificaciones.dtos.UserChangeStatusDTO;
import com.pablossrudi.calificaciones.dtos.UserResponseDTO;
import com.pablossrudi.calificaciones.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> changeStatus(@PathVariable String userId, @RequestBody UserChangeStatusDTO userChangeStatusDTO) {
        UserResponseDTO user = userService.changeUserStatus(userId, userChangeStatusDTO);

        return ResponseEntity.ok(user);
    }
}
