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

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;

import lombok.*;

@Data
public class HeaterDto {
    private Long id;
    private String name;
    private HeaterStatus heaterStatus;
    private String roomName;
    private Long roomId;
    private Long power;

    public HeaterDto() {
    }

    public HeaterDto(Heater heater) {
        this.id = heater.getId();
        this.name = heater.getName();
        this.heaterStatus = heater.getHeaterStatus();
        this.roomName = heater.getRoom().getName();
        this.roomId = heater.getRoom().getId();
        this.power = heater.getPower();
    }

}
