package com.example.roomreservation.controllers;

import com.example.roomreservation.entities.Reservation;
import com.example.roomreservation.requests.EventData;
import com.example.roomreservation.services.RoomReservationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class RoomReservationController {

    private RoomReservationService roomReservationService;


    public RoomReservationController(RoomReservationService roomReservationService) {
        this.roomReservationService = roomReservationService;
    }

    @GetMapping(value = {"/getRoomReservations"})
    public ResponseEntity<List<EventData>> getRoomReservations() {
        List<EventData> roomReservations = roomReservationService.getRoomReservations();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(roomReservations);
    }

    @PostMapping(value = {"/addRoomReservation"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> addRoomReservation(@RequestBody EventData eventData) {
        Reservation reservation = roomReservationService.addRoomReservation(eventData);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(reservation);
    }

    @DeleteMapping("deleteRoomReservation/{id}")
    public void deleteReservation(@PathVariable long id) {
        roomReservationService.deleteReservation(id);
    }


}
