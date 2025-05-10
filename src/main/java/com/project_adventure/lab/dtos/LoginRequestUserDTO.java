package com.project_adventure.lab.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestUserDTO {
    private String username;
    private String password;

    public void LoginRequest() {}
}
