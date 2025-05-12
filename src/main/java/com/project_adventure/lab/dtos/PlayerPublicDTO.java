package com.project_adventure.lab.dtos;

import com.project_adventure.lab.models.Game;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerPublicDTO {
    private String username;
    private List<Game> games;
}
