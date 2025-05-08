package com.project_adventure.lab.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
public class Admin extends User {


    public Admin(){
        var roles = new ArrayList<Role>();
        var role = new Role();
        role.setName(ERole.ROLE_EDITOR);
        roles.add(role);
        setRoles(roles);
    };
}
