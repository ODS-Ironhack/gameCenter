package com.project_adventure.lab.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
public class Player extends User {

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Game> games = new ArrayList<>();

    public Player() {
        this.setRole(ERole.ROLE_PLAYER);
    }

}
