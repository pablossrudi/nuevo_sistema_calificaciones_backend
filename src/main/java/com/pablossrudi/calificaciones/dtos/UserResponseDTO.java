package com.pablossrudi.calificaciones.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Data
@Builder
public class UserResponseDTO {
    private String userId;
    private String userName;
    private String rut;
    private String password;
    private String email;
    private Boolean isActive;
    private Set<String> roles;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDTO other = (UserResponseDTO) o;
        return Objects.equals(userId, other.userId) && Objects.equals(userName, other.userName) && Objects.equals(rut, other.rut) && Objects.equals(email, other.email) && Objects.equals(isActive, other.isActive) && roles.equals(other.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, rut, email, isActive, roles);
    }
}


