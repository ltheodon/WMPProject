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

import javax.persistence.*;

import lombok.*;

@Data
@Entity
public class Heater {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    private Long power;

    @ManyToOne(optional = false)
    private Room room;


    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private HeaterStatus heaterStatus;


    public Heater() {
    }

    public Heater(String name, Long power, Room room, HeaterStatus heaterStatus) {
        this.name = name;
        this.power = power;
        this.room = room;
        this.heaterStatus = heaterStatus;


    }

}
