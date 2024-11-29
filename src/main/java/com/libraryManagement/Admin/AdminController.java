package com.libraryManagement.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        return adminService.login(email, password);
    }
}
