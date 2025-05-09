package com.pablossrudi.calificaciones.services;

import com.pablossrudi.calificaciones.models.Role;
import com.pablossrudi.calificaciones.models.User;
import com.pablossrudi.calificaciones.repositores.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    public Collection<? extends GrantedAuthority> mapRolesAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName().toUpperCase())).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                mapRolesAuthorities(user.getRoles())
        );
    }

    @Transactional(readOnly = true)
    public boolean userExists(String username) {
        return userRepository.findByUserName(username).isPresent();
    }

    @Transactional(readOnly = true)
    public boolean isUserActive(String username) {
        return userRepository.findByUserName(username)
                .map(User::getIsActive)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    @Transactional(readOnly = true)
    public String getUserRoles(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        if (!user.getIsActive()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return user.getRoles().stream()
                .findFirst()
                .map(Role::getName)
                .orElse("ROLE_CLIENT");
    }
}
