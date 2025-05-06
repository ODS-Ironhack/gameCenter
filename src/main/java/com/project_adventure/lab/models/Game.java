package com.project_adventure.lab.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private int duration;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;
}
