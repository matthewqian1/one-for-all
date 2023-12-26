package org.example;

import jakarta.annotation.PostConstruct;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final int MAX_REVIEWS = 5;

    private final Random random = new Random();
    List<String> users = List.of("Shopaholic123",
            "DigitalShopper22",
            "TrendyExplorer",
            "SavvyBuyer89",
            "Fashionista2023",
            "GadgetGeek47",
            "BargainHunterX",
            "EcoFriendlyBuyer",
            "LuxuryLover77",
            "SportsFanatic99");

    List<String> goodComments;
    List<String> badComments;

    Map<String, List<Review>> reviews;

    @PostConstruct
    public void load() throws IOException {
        reviews = new HashMap<>();
        Yaml yaml = new Yaml();
        InputStream inputStream1 = this.getClass()
                .getClassLoader()
                .getResourceAsStream("sampleReviews/good.yml");
        goodComments = yaml.load(inputStream1);
        InputStream inputStream2 = this.getClass()
                .getClassLoader()
                .getResourceAsStream("sampleReviews/bad.yml");
        badComments = yaml.load(inputStream2);
    }

    public List<Review> getProductReviews(String id) {
        if (reviews.containsKey(id)) {
            return reviews.get(id);
        }

        List<Review> reviewList = new ArrayList<>();

        for (int i = 0; i < MAX_REVIEWS; i++) {
            boolean isGoodReview = getRandomNumber(0,2) == 0;
            String comment = isGoodReview ? goodComments.get(getRandomNumber(0, goodComments.size() - 1)) : badComments.get(getRandomNumber(0, badComments.size() - 1));
            int rating = isGoodReview ? getRandomNumber(4,6) : getRandomNumber(0, 3);
            reviewList.add(Review.builder()
                            .comment(comment)
                            .rating(rating)
                            .username(users.get(getRandomNumber(0, users.size() - 1)))
                            .date(LocalDate.now().minusDays(i))
                    .build());
        }

        reviews.put(id, reviewList);

        return reviewList;
    }

    public double getProductRating(String id) {
        List<Review> reviews = getProductReviews(id);
        return (double) reviews.stream().map(Review::getRating).reduce(0, Integer::sum) / reviews.size();

    }

    private int getRandomNumber(int min, int max) {

        return random.nextInt(max - min) + min;
    }


}
