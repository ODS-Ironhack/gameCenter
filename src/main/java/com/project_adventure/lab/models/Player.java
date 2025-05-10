package com.project_adventure.lab.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@ToString(callSuper = true)//al hacer print de player saldrán los atributos del super también.
public class Player extends User {

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Game> games = new ArrayList<>();

    public Player() {
        this.setRole(ERole.ROLE_PLAYER);
    }

    private int credit;

}
