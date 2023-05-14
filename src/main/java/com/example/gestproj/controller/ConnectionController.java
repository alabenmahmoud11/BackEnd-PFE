package com.example.gestproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectionController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/testconnection")
    public String testConnection() {
        try {
            String result = jdbcTemplate.queryForObject("SELECT 'Connection Successful'", String.class);
            return result;
        } catch (Exception e) {
            return "Connection Failed: " + e.getMessage();
        }
    }
}