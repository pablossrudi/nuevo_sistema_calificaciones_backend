package com.pablossrudi.calificaciones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HashController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("hash-password")
    public String hashPassword(@RequestParam String password) {
        return passwordEncoder.encode(password);
    }
}
