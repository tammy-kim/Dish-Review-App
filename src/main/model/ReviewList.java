package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

// Represents a collection of reviews
public class ReviewList {

    private List<Review> reviews;

    // EFFECTS: an empty set of reviews
    public ReviewList() {
        reviews = new ArrayList<>();
    }

    public List<Review> getReviews() {
        return reviews;
    }

    // REQUIRES: the review being added is not completely identical to any of reviews
    // MODIFIES: this
    // EFFECTS: adds review to the collection of reviews
    public void addReview(Review review) {
        reviews.add(review);
    }


    // EFFECTS: returns the number of elements in the collection of reviews
    public int howManyReviews() {
        return reviews.size();
    }

    // EFFECTS: returns the names of dishes and their types in the collection of reviews
    public String allDishNames() {
        String appetizer = "Appetizer(s): \n";
        String mainDish = "Main dish(es): \n";
        String sideDish = "Side dish(es): \n";
        String dessert = "Dessert(s): \n";
        for (Review r : reviews) {
            if (r.getFoodType().equals("appetizer")) {
                appetizer = appetizer + r.getFoodName() + "\n";
            } else if (r.getFoodType().equals("dessert")) {
                dessert = dessert + r.getFoodName() + "\n";
            } else if (r.getFoodType().equals("side dish")) {
                sideDish = sideDish + r.getFoodName() + "\n";
            } else {
                mainDish = mainDish + r.getFoodName() + "\n";
            }
        }
        return appetizer + "\n" + mainDish + "\n" + sideDish + "\n" + dessert;
    }

    // EFFECTS: returns only the names and addresses of restaurants in the collection of reviews
    public String allRestaurantNames() {
        String listRestaurants = "";
        for (Review r : reviews) {
            listRestaurants = listRestaurants + r.getRestaurantName() + " in " + r.getRestaurantLoc() + "\n";
        }
        return listRestaurants;
    }

    // EFFECTS: returns the details of a review that matches with given dish's name, restaurant's name and location,
    //          returns an empty string otherwise
    public String selectReview(String dish, String restaurant, String location) {
        String review = "";
        for (Review r : reviews) {
            if (r.getFoodName().equals(dish) && r.getRestaurantName().equals(restaurant)
                    && r.getRestaurantLoc().equals(location)) {
                review = review + r.viewReview();
            }
        }
        return review;
    }

    // EFFECTS: returns the review that matches with given dish's name, restaurant's name and location,
    //          returns null otherwise
    public Review selectReviewForGui(String dish, String restaurant, String location) {
        Review review = null;
        for (Review r : reviews) {
            if (r.getFoodName().equals(dish) && r.getRestaurantName().equals(restaurant)
                    && r.getRestaurantLoc().equals(location)) {
                review = r;
            }
        }
        return review;
    }

    // EFFECTS: returns all of the reviews
    public String viewEverything() {
        String viewAll = "";
        for (Review r : reviews) {
            viewAll = viewAll + r.viewReviewWithoutComment();
        }
        return viewAll;
    }



}
