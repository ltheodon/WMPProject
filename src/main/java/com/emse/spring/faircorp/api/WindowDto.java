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

import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;

import lombok.*;

@Data
public class WindowDto {
    private Long id;
    private String name;
    private WindowStatus windowStatus;
    private Long roomId;
    private RoomDto roomD;

    public WindowDto() {
    }

    public WindowDto(Window window) {
        this.id = window.getId();
        this.name = window.getName();
        this.windowStatus = window.getWindowStatus();
        this.roomId = window.getRoom().getId();
        this.roomD = castRoomDto(window.getRoom());
    }

    public RoomDto getRoomDto() {
        return roomD;
    }

    public void setRoomDto(RoomDto roomDto) {
        this.roomD = roomDto;
    }

    public RoomDto castRoomDto(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setName(room.getName());
        roomDto.setTargetTemperature(room.getTargetTemperature());
        roomDto.setCurrentTemperature(room.getCurrentTemperature());
        return roomDto;
    }


}
