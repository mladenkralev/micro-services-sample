package com.crafters.ratingsrecipeservice.resources;

import com.crafters.ratingsrecipeservice.model.Rating;
import com.crafters.ratingsrecipeservice.model.UserRatings;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsResource {

    @RequestMapping("/{recipeId}")
    public Rating getRatingForRecipe(@PathVariable("recipeId") String recipeId) {
        return new Rating(recipeId, 4);
    }

    @RequestMapping("users/{userId}")
    public UserRatings getRatingsForUser(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("Porter", 100),
                new Rating("Wit", 200),
                new Rating("Dry Stout", 300)
        );
        UserRatings userRatings = new UserRatings();
        userRatings.setUserId(userId);
        userRatings.setUserRatingList(ratings);
        return userRatings;
    }
}
