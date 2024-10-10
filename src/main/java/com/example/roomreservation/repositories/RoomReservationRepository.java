package com.example.roomreservation.repositories;

import com.example.roomreservation.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface RoomReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findReservationsByRoomNameAndReservedAtBetween(String roomName, Instant start, Instant end);
}
