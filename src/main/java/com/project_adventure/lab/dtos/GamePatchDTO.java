package com.project_adventure.lab.dtos;

import com.project_adventure.lab.models.Franchise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamePatchDTO {
    private String name;
    private int duration;
    private String description;
    private Franchise franchise;
}
