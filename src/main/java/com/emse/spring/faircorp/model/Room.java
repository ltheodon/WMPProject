/**
 *
 *                      UJM * EMSE
 *
 *                  * Aleksei PASHININ *
 *
 *                     WMP Project
 *
 */

package com.emse.spring.faircorp.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

import lombok.*;

@Data
@Entity
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "level")
    private Integer floor;

    private Double targetTemperature;

    private Double currentTemperature;

    @OneToMany(targetEntity = Heater.class, mappedBy = "room")
    private Set<Heater> heaters;

    @OneToMany(mappedBy = "room")
    private Set<Window> windows;

    @OneToMany(mappedBy = "room")
    private Set<RoomWindows> room_ids;

    @ManyToOne
    @NotNull
    private Building building;


    public Room() {
    }

    public Room(String name, Double currentTemperature, Double targetTemperature, Integer floor, Building building) {
        this.floor = floor;
        this.name = name;
        this.currentTemperature = currentTemperature;
        this.targetTemperature = targetTemperature;
        this.building = building;
    }


    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Set<RoomWindows> getRoomIds() {
        return room_ids;
    }

    public void setRoomIds(Set<RoomWindows> room_ids) {
        this.room_ids = room_ids;
    }

    public void setName(String name) {
        this.name = name;
    }

}
