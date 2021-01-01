package com.emse.spring.faircorp.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
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

    @OneToMany(mappedBy="window")
    private Set<RoomWindows> windows_ids;


    public Window() {
    }

    public Window(String name, WindowStatus WindowStatus, Room room) {
        this.WindowStatus = WindowStatus;
        this.name = name;
        this.room = room;
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

    public WindowStatus getWindowStatus() {
        return WindowStatus;
    }

    public void setWindowStatus(WindowStatus WindowStatus) {
        this.WindowStatus = WindowStatus;
    }

    public Room getRoom() {
        return room;
    }
}
