package com.project_adventure.lab.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Admin extends User {

    public Admin(){
        setRole(ERole.ROLE_CREATOR);
    };

    public void setRole(ERole role){
        if (role == ERole.ROLE_EDITOR || role == ERole.ROLE_CREATOR) {
            this.role = role;
        } else {
            throw new IllegalArgumentException("Invalid role for Admin: " + role);
        }
    }
}
