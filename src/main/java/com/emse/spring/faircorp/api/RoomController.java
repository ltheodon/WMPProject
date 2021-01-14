/**
 *
 *                      UJM * EMSE
 *
 *                  * Aleksei PASHININ *
 *
 *                     WMP Project
 *
 */

package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.model.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {

    private final RoomDao roomDao;
    private final HeaterDao heaterDao;
    private final WindowDao windowDao;
    private final BuildingDao buildingDao;


    public RoomController(RoomDao roomDao, BuildingDao buildingDao, HeaterDao heaterDao, WindowDao windowDao) {
        this.roomDao = roomDao;
        this.heaterDao = heaterDao;
        this.windowDao = windowDao;
        this.buildingDao = buildingDao;
    }

    @GetMapping
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomDto::new).orElse(null);
    }

    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto) {
        Room room = null;
        if (dto.getId() == null) {
            room = roomDao.save(new Room(dto.getName(), dto.getCurrentTemperature(), dto.getTargetTemperature(), dto.getFloor(), dto.getBuilding()));
        } else {
            room = roomDao.getOne(dto.getId());
            room.setCurrentTemperature(dto.getCurrentTemperature());
            room.setTargetTemperature(dto.getTargetTemperature());
            room.setFloor(dto.getFloor());
            room.setBuilding(dto.getBuilding());
            room.setName(dto.getName());

        }
        return new RoomDto(room);
    }

    @PutMapping(path = "/{room_id}/switchWindow")
    public RoomDto switchStatusWindow(@PathVariable Long room_id, Long windowId) {
        Room room = roomDao.findById(room_id).orElseThrow(IllegalArgumentException::new);
        Set<Window> windows = room.getWindows();
        for (Window window : windows) {
            if (window.getId() == windowId) {
                window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED : WindowStatus.OPEN);
            }
        }
        room.setWindows(windows);
        return new RoomDto(room);
    }

    @PutMapping(path = "/{room_id}/switchHeaters")
    public RoomDto switchStatusHeater(@PathVariable Long room_id, Long heaterId) {
        Room room = roomDao.findById(room_id).orElseThrow(IllegalArgumentException::new);
        Set<Heater> heaters = room.getHeaters();
        for (Heater heater : heaters) {
            if (heater.getId() == heaterId) {
                heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF : HeaterStatus.ON);
            }
        }
        room.setHeaters(heaters);
        return new RoomDto(room);
    }

    @PutMapping(path = "/{id}/updateName/{name}")
    public RoomDto updateNameByID(@PathVariable Long id, @PathVariable String name) {
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        room.setName(name);
        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        heaterDao.deleteHeaters(id);
        windowDao.deleteWindows(id);
        roomDao.deleteById(id);
    }
}
