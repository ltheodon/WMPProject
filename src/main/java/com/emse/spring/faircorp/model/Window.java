package com.emse.spring.faircorp.model;
import javax.persistence.*;

@Entity
@Table(name = "RWINDOW")
public class Window {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    private String name;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WindowStatus WindowStatus;

    @ManyToOne(optional = false) //Notification to create window
    private Room room;


    public Window() {
    }

    public Window(String name, WindowStatus status, Room room) {
        this.WindowStatus = status;
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

    public void setRoom_id(Room room_id) {
        this.room = room_id;

    }
}
