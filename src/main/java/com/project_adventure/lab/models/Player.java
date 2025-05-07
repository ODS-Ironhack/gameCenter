package com.project_adventure.lab.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player extends User {

    @Enumerated(EnumType.STRING)
    private PlayerType playerType;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Game> games = new ArrayList<>();

}
