package com.crafters.recipecatalogservice.resources;

import com.crafters.recipecatalogservice.models.CatalogItem;
import com.crafters.recipecatalogservice.models.UserRatings;
import com.crafters.recipecatalogservice.services.RecipeInfo;
import com.crafters.recipecatalogservice.services.UserRatingsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class RecipeCatalogController {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    UserRatingsInfo userRatingsInfo;

    @Autowired
    RecipeInfo recipeInfo;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRatings userRatings = userRatingsInfo.getUserRatings(userId);

        return Objects.requireNonNull(userRatings).getUserRatingList().stream()
                .map(rating -> recipeInfo.getRecipeItem(rating))
                .collect(Collectors.toList());
    }
}
