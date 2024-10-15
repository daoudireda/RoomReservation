package com.example.roomreservation.services;

import com.example.roomreservation.entities.Reservation;
import com.example.roomreservation.repositories.RoomReservationRepository;
import com.example.roomreservation.requests.EventData;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomReservationService {

    private RoomReservationRepository roomReservationRepository;


    public RoomReservationService(RoomReservationRepository roomReservationRepository) {
        this.roomReservationRepository = roomReservationRepository;
    }


    public Reservation saveReservation(Reservation reservation) {
        return roomReservationRepository.save(reservation);
    }

    public List<EventData> getRoomReservations() {
        List<Reservation> reservations = roomReservationRepository.findAll()
                .stream()
                .toList();
        List<EventData> eventData = new ArrayList<>();
        for (Reservation reservation : reservations) {
            EventData event = new EventData();
            event.setId(reservation.getId().toString());
            event.setTitle(reservation.getReservedBy());
            event.setStart(formatDateTime(reservation.getReservedAt()));
            event.setEnd(formatDateTime(reservation.getReservedUntil()));
            eventData.add(event);
        }
        return eventData;
    }

    private Date formatDateTime(Instant instant) {
        return Date.from(instant);
    }

    public void deleteReservation(Long id) {
        roomReservationRepository.deleteById(id);
    }


    public Reservation addRoomReservation(EventData eventData) {
        Instant start = eventData.getStart().toInstant();
        Instant end = eventData.getEnd().toInstant();
        Reservation reservation = new Reservation(eventData.getId(), eventData.getTitle(), start, end);
        return roomReservationRepository.save(reservation);
    }
}
