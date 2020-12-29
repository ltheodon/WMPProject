package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomDao extends JpaRepository<Room, Long> {

    List<Room> findById(String id); // (1).

    @Query("select c from Room c where c.name=:name")  // (2)
    Room findByName(@Param("name") String name);

    @Modifying // (3)
    @Query("delete from Room c where c.name = ?1")
    void deleteByName(String name);
}
