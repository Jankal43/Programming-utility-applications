package com.example.demo.rating;

import com.example.demo.group.Group;
import com.example.demo.group.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private RatingService ratingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRatings() {
        // Given
        List<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating(1, 5, 1, new Date(), "Good group"));
        ratings.add(new Rating(2, 4, 2, new Date(), ""));

        when(ratingRepository.findAll()).thenReturn(ratings);

        // When
        List<Rating> result = ratingService.getRatings();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void testAddNewRating_Success() {
        // Given
        Rating newRating = new Rating(5, 1, "Good group");
        Group group = new Group(1, "Math", 10, 4.0f, 2);
        when(groupRepository.existsById(1)).thenReturn(true);
        when(groupRepository.findById(1)).thenReturn(Optional.of(group));
        when(ratingRepository.save(newRating)).thenReturn(newRating);

        // When
        ratingService.addNewRating(newRating);

        // Then
        assertEquals(3, group.getNumberOfRates()); // Sprawdź, czy liczba ocen została zwiększona o 1
        assertEquals(4.5f, group.getAverageRate()); // Sprawdź, czy średnia ocen została poprawnie obliczona
    }

    @Test
    void testAddNewRating_GroupDoesNotExist() {
        // Given
        Rating newRating = new Rating(5, 1, "Good group");
        when(groupRepository.existsById(1)).thenReturn(false);

        // When, Then
        assertThrows(IllegalStateException.class, () -> ratingService.addNewRating(newRating));
    }

    @Test
    void testAddNewRating_InvalidRatingValue() {
        // Given
        Rating newRating = new Rating(7, 1, "Good group"); // Nieprawidłowa wartość oceny

        // When, Then
        assertThrows(IllegalArgumentException.class, () -> ratingService.addNewRating(newRating));
    }

    // Dodaj więcej testów, jeśli to konieczne
}
