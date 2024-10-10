package com.example.roomreservation.controllers;

import com.example.roomreservation.entities.Reservation;
import com.example.roomreservation.services.RoomReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class RoomReservationController {

    private RoomReservationService roomReservationService;


    public RoomReservationController(RoomReservationService roomReservationService) {
        this.roomReservationService = roomReservationService;
    }


    @GetMapping
    public List<Reservation> getAllReservations() {
        return roomReservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable long id) {
        Reservation reservation = roomReservationService.getReservationById(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {

        Reservation savedReservation = roomReservationService.saveReservation(reservation);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(savedReservation);

    }

    @GetMapping({"/{roomName}"})
    public List<Reservation> getRoomReservations(@PathVariable String roomName,
                                                 @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant start,
                                                 @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant end) {
        return roomReservationService.getRoomReservations(roomName, start, end);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable long id) {
        roomReservationService.deleteReservation(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable long id, @RequestBody Reservation updatedReservation) {
        Reservation updateReservation = roomReservationService.updateReservation(id, updatedReservation);
        return new ResponseEntity<>(updateReservation, HttpStatus.OK);

    }

}
