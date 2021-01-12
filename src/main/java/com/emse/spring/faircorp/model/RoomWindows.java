package com.emse.spring.faircorp.model;

import lombok.Getter;
/**
 *
 *                      UJM * EMSE
 *
 *                  * Aleksei PASHININ *
 *
 *                     WMP Project
 *
 */

import javax.persistence.*;

import lombok.*;

@Data
@Entity
@IdClass(MultiKeys.class)
@Table(name = "ROOM_WINDOWS")

public class RoomWindows {

    @Id
    @GeneratedValue
    @Column(nullable = false, insertable = false, updatable = false)
    private Long room_id;

    @ManyToOne(optional = false)
    private Room room;

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long windows_id;

    @ManyToOne(optional = false)
    private Window window;

}
