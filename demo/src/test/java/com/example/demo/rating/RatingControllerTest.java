package com.example.demo.rating;

import com.example.demo.rating.Rating;
import com.example.demo.rating.RatingController;
import com.example.demo.rating.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RatingControllerTest {

    @Mock
    private RatingService ratingService;

    @InjectMocks
    private RatingController ratingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetRatings() {
        // Given
        List<Rating> ratings = new ArrayList<>();
        when(ratingService.getRatings()).thenReturn(ratings);

        // When
        List<Rating> result = ratingController.getRatings();

        // Then
        assertEquals(ratings, result);
    }

    @Test
    void testRegisterNewRating_Success() {
        // Given
        Rating rating = new Rating();
        doNothing().when(ratingService).addNewRating(rating);

        // When
        ResponseEntity<Object> responseEntity = ratingController.registerNewRating(rating);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }


    @Test
    void testRegisterNewRating_IllegalArgumentException() {
        // Given
        Rating rating = new Rating();
        doThrow(new IllegalArgumentException("Invalid input")).when(ratingService).addNewRating(rating);

        // When
        ResponseEntity<Object> responseEntity = ratingController.registerNewRating(rating);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid input", responseEntity.getBody());
    }

    @Test
    void testRegisterNewRating_IllegalStateException() {
        // Given
        Rating rating = new Rating();
        doThrow(new IllegalStateException("Invalid state")).when(ratingService).addNewRating(rating);

        // When
        ResponseEntity<Object> responseEntity = ratingController.registerNewRating(rating);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid state", responseEntity.getBody());
    }
}
