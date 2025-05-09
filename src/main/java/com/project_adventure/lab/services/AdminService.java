package com.project_adventure.lab.services;

import com.project_adventure.lab.models.Admin;
import com.project_adventure.lab.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminRepository adminRepository;

}
