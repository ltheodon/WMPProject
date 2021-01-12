/**
 *
 *                      UJM * EMSE
 *
 *                  * Aleksei PASHININ *
 *
 *                     WMP Project
 *
 */

package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.model.Building;

import lombok.*;

@Data
public class BuildingDto {
    private Long id;
    private String name;

    public BuildingDto() {
    }

    public BuildingDto(Building building) {
        this.id = building.getId();
        this.name = building.getName();
    }

}
