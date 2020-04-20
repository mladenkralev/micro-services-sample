package com.crafters.recipecatalogservice.models;

public class Rating {
    private String recipeId;
    private int rating;

    public Rating() {
    }

    public Rating(String recipeId, int rating) {
        this.recipeId = recipeId;
        this.rating = rating;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
