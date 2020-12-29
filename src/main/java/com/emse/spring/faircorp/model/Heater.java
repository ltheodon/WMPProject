package com.emse.spring.faircorp.model;

import javax.persistence.*;

@Entity
public class Heater {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String name;

    private Long power;

    @ManyToOne(optional = false)
    private Room room;


    @Column(nullable=false)
    @Enumerated(value = EnumType.STRING)
    private HeaterStatus heaterStatus;


    public Heater() {
    }

    public Heater(Long id, String name, Long power, Room room, HeaterStatus heaterStatus) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.room = room;
        this.heaterStatus= heaterStatus;


    }

    public Long getId() {
        return this.id;
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

    public HeaterStatus getHeaterStatus() {
        return heaterStatus;
    }

    public void setHeaterStatus(HeaterStatus heaterStatus) {
        this.heaterStatus = heaterStatus;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public Long getPower() {
        return power;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }
}
