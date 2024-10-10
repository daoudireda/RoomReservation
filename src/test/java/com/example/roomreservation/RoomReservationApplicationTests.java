package com.example.roomreservation;

import com.example.roomreservation.entities.Reservation;
import com.example.roomreservation.repositories.RoomReservationRepository;
import com.example.roomreservation.services.RoomReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RoomReservationApplicationTests {


    @Mock
    private RoomReservationRepository reservationRepository;

    @InjectMocks
    private RoomReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllReservations() {
        // Préparer des données fictives
        Reservation reservation1 = new Reservation("Salle A", "John Doe", Instant.now(), Instant.now().plus(2, ChronoUnit.HOURS));
        Reservation reservation2 = new Reservation("Salle B", "Jane Doe", Instant.now().plus(1, ChronoUnit.HOURS), Instant.now().plus(2, ChronoUnit.HOURS));
        List<Reservation> reservations = Arrays.asList(reservation1, reservation2);

        // Simuler le comportement du repository
        when(reservationRepository.findAll()).thenReturn(reservations);

        // Appeler le service
        List<Reservation> result = reservationService.getAllReservations();

        // Vérifier le résultat
        assertEquals(2, result.size());
        assertEquals("Salle A", result.get(0).getRoomName());
        assertEquals("Salle B", result.get(1).getRoomName());
    }

    @Test
    void testGetReservationById() {
        // Préparer des données fictives
        Reservation reservation = new Reservation("Salle A", "John Doe", Instant.now(), Instant.now().plus(2, ChronoUnit.HOURS));

        // Simuler le comportement du repository
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        // Appeler le service
        Reservation result = reservationService.getReservationById(1L);

        // Vérifier le résultat
        assertNotNull(result);
        assertEquals("Salle A", result.getRoomName());
        assertEquals("John Doe", result.getReservedBy());
    }

    @Test
    void testCreateReservation() {
        // Préparer des données fictives
        Reservation reservation = new Reservation("Salle A", "John Doe", Instant.now(), Instant.now().plus(2, ChronoUnit.HOURS));

        // Simuler le comportement du repository
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Appeler le service
        Reservation result = reservationService.saveReservation(reservation);

        // Vérifier le résultat
        assertNotNull(result);
        assertEquals("Salle A", result.getRoomName());
        assertEquals("John Doe", result.getReservedBy());
    }

    @Test
    void testUpdateReservation() {
        // Préparer des données fictives
        Reservation reservation = new Reservation("Salle A", "John Doe", Instant.now(), Instant.now().plus(2, ChronoUnit.HOURS));
        Reservation updatedReservation = new Reservation("Salle B", "Jane Doe", Instant.now().plus(1, ChronoUnit.HOURS), Instant.now().plus(2, ChronoUnit.HOURS));

        // Simuler le comportement du repository
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(reservation)).thenReturn(updatedReservation);

        // Appeler le service
        Reservation result = reservationService.updateReservation(1L, updatedReservation);

        // Vérifier le résultat
        assertNotNull(result);
        assertEquals("Salle B", result.getRoomName());
        assertEquals("Jane Doe", result.getReservedBy());
    }

    @Test
    void testDeleteReservation() {
        // Simuler le comportement du repository
        doNothing().when(reservationRepository).deleteById(1L);

        // Appeler le service
        reservationService.deleteReservation(1L);

        // Vérifier que le repository a bien été appelé
        verify(reservationRepository, times(1)).deleteById(1L);
    }
}
