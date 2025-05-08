package com.project_adventure.lab.controllers;

import com.project_adventure.lab.models.Admin;
import com.project_adventure.lab.models.Franchise;
import com.project_adventure.lab.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Admin createAdmin(@Valid @RequestBody Admin admin){
        return adminService.createAdmin(admin);
    }
}
