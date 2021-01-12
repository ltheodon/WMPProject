INSERT INTO ROOM(id, name, level, current_temperature, target_temperature) VALUES(1, 'Room1', 1, 15.3, 20.0);
INSERT INTO ROOM(id, name, level, current_temperature, target_temperature) VALUES(2, 'Room2', 2, 22.3, 22.0);
INSERT INTO ROOM(id, name, level, current_temperature, target_temperature) VALUES(3, 'Room3', 3, 28.3, 23.0);

INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(-10, 'ON', 'Heater1', 2000, 1);
INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(-9, 'ON', 'Heater2', null, 1);

INSERT INTO RWINDOW(id, status, name, room_id) VALUES(1, 'CLOSED', 'Window 1', 1);
INSERT INTO RWINDOW(id, status, name, room_id) VALUES(2, 'CLOSED', 'Window 2', 1);
INSERT INTO RWINDOW(id, status, name, room_id) VALUES(3, 'OPEN', 'Window 3', 1);
INSERT INTO RWINDOW(id, status, name, room_id) VALUES(4, 'OPEN', 'Window 1', 2);
INSERT INTO RWINDOW(id, status, name, room_id) VALUES(5, 'CLOSED', 'Window 2', 2);
INSERT INTO RWINDOW(id, status, name, room_id) VALUES(6, 'CLOSED', 'Window 3', 2);
INSERT INTO RWINDOW(id, status, name, room_id) VALUES(7, 'OPEN', 'Window 1', 3);
INSERT INTO RWINDOW(id, status, name, room_id) VALUES(8, 'OPEN', 'Window 2', 3);
INSERT INTO RWINDOW(id, status, name, room_id) VALUES(9, 'CLOSED', 'Window 3', 3);
