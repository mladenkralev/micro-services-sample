package com.crafters.recipecatalogservice.services;

import com.crafters.recipecatalogservice.models.CatalogItem;
import com.crafters.recipecatalogservice.models.Rating;
import com.crafters.recipecatalogservice.models.Recipe;
import com.crafters.recipecatalogservice.models.UserRatings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RecipeInfo {

    @Autowired
    public RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackRecipeItem",
            threadPoolKey = "recipeInfoPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize" , value="20"),
                    @HystrixProperty(name = "maxQueueSize" , value="10")
            },

            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "5000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
            }
    )
    public CatalogItem getRecipeItem(Rating rating) {
        Recipe recipe =
                restTemplate.getForObject("http://RECIPE-INFO-SERVICE/recipes/" + rating.getRecipeId(),
                        Recipe.class);
        return new CatalogItem(recipe.getName(), recipe.getDescription(), rating.getRating());
    }

    public CatalogItem fallbackRecipeItem(Rating rating) {
       return new CatalogItem("Recipe not found", "",0);
    }
}
