package com.example.roomreservation.services;

import com.example.roomreservation.entities.Reservation;
import com.example.roomreservation.repositories.RoomReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class RoomReservationService {

    private RoomReservationRepository roomReservationRepository;


    public RoomReservationService(RoomReservationRepository roomReservationRepository) {
        this.roomReservationRepository = roomReservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return roomReservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return roomReservationRepository.findById(id).orElse(null);
    }

    public Reservation saveReservation(Reservation reservation) {
        return roomReservationRepository.save(reservation);
    }

    public List<Reservation> getRoomReservations(String roomName, Instant start, Instant end) {
        return roomReservationRepository.findReservationsByRoomNameAndReservedAtBetween(roomName, start, end);
    }

    public void deleteReservation(Long id) {
        roomReservationRepository.deleteById(id);
    }

    public Reservation updateReservation(Long id, Reservation reservation) {
        Reservation existingReservation = roomReservationRepository.findById(id).orElse(null);
        if (existingReservation == null) {
            return null;
        }
        existingReservation.setRoomName(reservation.getRoomName());
        existingReservation.setReservedBy(reservation.getReservedBy());
        existingReservation.setReservedAt(reservation.getReservedAt());
        existingReservation.setReservedUntil(reservation.getReservedUntil());
        return roomReservationRepository.save(existingReservation);
    }
}
