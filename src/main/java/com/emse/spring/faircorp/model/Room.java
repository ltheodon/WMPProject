package com.emse.spring.faircorp.model;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private Integer floor;

    private Double targetTemperature;

    private Double currentTemperature;

    @OneToMany(mappedBy="room")
    private Set<Heater> heaters;

    @OneToMany(mappedBy="room")
    private Set<Window> windows;

    public Room() {
    }

    public Room(String name, Integer floor) {
        this.floor = floor;
        this.name = name;
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

    public int getFloor() {
        return floor;
    }

    public Double getcurrentTemperature() {
        return currentTemperature;
    }

    public Double gettargetTemperature() {
        return targetTemperature;
    }

    public Set<Heater> getHeaters() {
        return heaters;
    }

    public Set<Window> getWindows() {
        return windows;
    }


    public void setFloor(int floor) {
        this.floor = floor;
    }


    public void setcurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public void settargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public void setHeaters(Set<Heater> heaters) {
        this.heaters = heaters;
    }

    public void setWindows(Set<Window> windows) {
        this.windows = windows;
    }
}
