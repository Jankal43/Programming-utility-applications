package com.example.demo.rating;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

@Configuration
public class RatingConfig {

    @Bean
    CommandLineRunner commandLineRunnerRate(RatingRepository ratingRepository){
        return args -> {
            // Do something if needed
            Rating rating1 = new Rating(5, 1, "Good group");

            Rating rating2 = new Rating(4, 2, "");
            ratingRepository.saveAll(List.of(rating1,rating2));
        };
    }
}

