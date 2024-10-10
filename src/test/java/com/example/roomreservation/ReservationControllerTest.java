package com.example.roomreservation;

import com.example.roomreservation.controllers.RoomReservationController;
import com.example.roomreservation.entities.Reservation;
import com.example.roomreservation.services.RoomReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomReservationController.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoomReservationService roomReservationService;

    @Test
    void testCreateReservation() throws Exception {
        // Create test data
        Instant now = Instant.now();
        Reservation reservation = new Reservation("Salle A", "John Doe", now, now.plus(2, ChronoUnit.HOURS));

        // Print the request payload for debugging
        String jsonRequest = objectMapper.writeValueAsString(reservation);

        // Setup mock with argument matcher
        when(roomReservationService.saveReservation(argThat(res ->
                "Salle A".equals(res.getRoomName()) &&
                        "John Doe".equals(res.getReservedBy())
        ))).thenReturn(reservation);

        // Perform test
        mockMvc.perform(post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roomName", is("Salle A")))
                .andReturn();

        // Verify the mock was called
        verify(roomReservationService).saveReservation(any(Reservation.class));
    }

    @Test
    void testGetReservations() throws Exception {
        Instant instant = Instant.now();
        Reservation res1 = new Reservation("Salle A", "John Doe", instant, instant.plus(2, ChronoUnit.HOURS));
        Reservation res2 = new Reservation("Salle B", "Jane Doe", instant, instant.plus(2, ChronoUnit.HOURS));
        when(roomReservationService.getAllReservations()).thenReturn(List.of(res1, res2));

        mockMvc.perform(get("/api/reservations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].roomName", is("Salle A")))
                .andExpect(jsonPath("$[1].roomName", is("Salle B")))
                .andReturn();

        verify(roomReservationService).getAllReservations();
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ObjectMapper objectMapper() {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper;
        }
    }
}
