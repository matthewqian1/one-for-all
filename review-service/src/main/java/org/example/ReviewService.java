package org.example;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {

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

    Map<String, Review> reviews;

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
        badComments = yaml.load(inputStream1);
    }


}
