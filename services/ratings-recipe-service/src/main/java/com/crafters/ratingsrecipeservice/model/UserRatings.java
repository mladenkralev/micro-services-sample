package com.crafters.ratingsrecipeservice.model;

import java.util.List;

public class UserRatings {
    public String userId;
    public List<Rating> userRatingList;

    public UserRatings() {
    }

    public UserRatings(List<Rating> userRatingList) {
        this.userRatingList = userRatingList;
    }

    public List<Rating> getUserRatingList() {
        return userRatingList;
    }

    public void setUserRatingList(List<Rating> userRatingList) {
        this.userRatingList = userRatingList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
