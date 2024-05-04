package com.example.demo.rating;

import com.example.demo.group.Group;
import com.example.demo.group.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, GroupRepository groupRepository) {
        this.ratingRepository = ratingRepository;
        this.groupRepository = groupRepository;
    }

    public List<Rating> getRatings(){
        return ratingRepository.findAll();
    }

    public void addNewRating(Rating rating) {
        Integer groupId = rating.getGroupId();
        if (groupId != null && !groupRepository.existsById(groupId)) {
            throw new IllegalStateException("Group with id " + groupId + " does not exist");
        }

        // Sprawdź, czy ocena mieści się w zakresie od 0 do 6
        int newRatingValue = rating.getRate();
        if (newRatingValue < 0 || newRatingValue > 6) {
            throw new IllegalArgumentException("Rating value must be between 0 and 6");
        }

        // Pobierz aktualną grupę
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalStateException("Group with id " + groupId + " does not exist"));

        // Zwiększ liczbę ocen
        group.setNumberOfRates(group.getNumberOfRates() + 1);

        // Oblicz nową sumę ocen
        float totalRate = group.getAverageRate() * (group.getNumberOfRates() - 1); // Aktualna suma ocen
        totalRate += newRatingValue; // Dodaj nową ocenę
        group.setAverageRate(totalRate / group.getNumberOfRates()); // Oblicz nową średnią ocen

        // Zapisz zaktualizowaną grupę
        groupRepository.save(group);

        // Zapisz ocenę
        rating.setRate(newRatingValue); // Aktualizuj wartość oceny
        ratingRepository.save(rating);
    }



}

