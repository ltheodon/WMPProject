package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Room;

public class RoomDto {
    private Long id;
    private String name;
    private Double currentTemperature;
    private Double targetTemperature;
    private Integer floor;
    private Building building;

    public RoomDto() {
    }

    public RoomDto(Room Room) {
        this.id = Room.getId();
        this.name = Room.getName();
        this.currentTemperature = Room.getCurrentTemperature();
        this.targetTemperature = Room.getTargetTemperature();
        this.floor = Room.getFloor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

}
