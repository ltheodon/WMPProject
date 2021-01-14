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
import java.util.Set;

import lombok.*;

@Data
@Entity
@Table(name = "RWINDOW")
public class Window {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    private String name;


    @Column(nullable = false, name = "status")
    @Enumerated(EnumType.STRING)
    private WindowStatus WindowStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;

    @OneToMany(mappedBy = "window")
    private Set<RoomWindows> windows_ids;


    public Window() {
    }

    public Window(String name, WindowStatus WindowStatus, Room room) {
        this.WindowStatus = WindowStatus;
        this.name = name;
        this.room = room;
    }

    public WindowStatus getWindowStatus() {
        return WindowStatus;
    }

    public void setWindowStatus(WindowStatus WindowStatus) {
        this.WindowStatus = WindowStatus;
    }
    public void setName(String name) {
        this.name = name;
    }
}
