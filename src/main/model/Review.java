package model;

import persistence.*;

import java.io.PrintWriter;

// Represents a review containing the dish's name and type, restaurant's name and its location, as well as the user's
// rating and comment on the food.
public class Review implements Saveable {

    private String foodName;            // the name of the dish
    private String foodType;            // the course of the dish (appetizer, main dish, side dish, dessert)
    private String restaurantName;      // the name of the restaurant that served the dish
    private String restaurantLoc;       // the address of the restaurant's location
    private int rating;                 // the quality of the dish rated out of 5 stars
    private String comment;             // the user's comment on the dish


    // REQUIRES: foodName has non-zero length, foodType has non zero length, restaurantName has non-zero length,
    //           restaurantLoc has non-zero length, foodType is either "appetizer", "main dish", "side dish",
    //           or "dessert", rating is between 1 and 5 inclusively, comment has non-zero length.
    // EFFECTS: Review has given food's name, food's type, restaurant's name, restaurant's location, rating, and comment
    public Review(String foodName, String foodType, String restaurantName, String restaurantLoc, int rating,
                  String comment) throws NotDishTypeException, OutOfRangeException {
        if (!isDishType(foodType)) {
            throw new NotDishTypeException("This does not describe the type of the dish.");
        }
        if (rating < 1 || rating > 5) {
            throw new OutOfRangeException("The dish must be rated 1, 2, 3, 4 or 5 out of 5.");
        }
        this.foodName = foodName;
        this.foodType = foodType;
        this.restaurantName = restaurantName;
        this.restaurantLoc = restaurantLoc;
        this.rating = rating;
        this.comment = comment;
    }

    // EFFECTS: returns true if given string is either "appetizer", "main dish", "side dish", or "dessert"
    public boolean isDishType(String foodType) {
        boolean isValidDish = foodType.toLowerCase().equals("appetizer") || foodType.toLowerCase().equals("main dish")
                || foodType.toLowerCase().equals("side dish") || foodType.toLowerCase().equals("dessert");
        return isValidDish;
    }

    // EFFECTS: returns the name of the dish
    public String getFoodName() {
        return foodName;
    }

    // EFFECTS: returns the course of the dish
    public String getFoodType() {
        return foodType;
    }

    // EFFECTS: returns the name of the restaurant
    public String getRestaurantName() {
        return restaurantName;
    }

    // EFFECTS: returns the location of the restaurant
    public String getRestaurantLoc() {
        return restaurantLoc;
    }

    // EFFECTS: returns the rating of the dish
    public int getRating() {
        return rating;
    }

    // EFFECTS: returns the user's comment on the dish
    public String getComment() {
        return comment;
    }

    // EFFECTS: returns all components of the review
    public String viewReview() {
        String strRating = String.format("%d", rating);
        return "Dish: " + foodName + " (" + foodType + "), Restaurant: " + restaurantName + ", Location: "
                + restaurantLoc + ", Rating: " + rating + " out of 5, Comment: " + comment + "\n";
    }

    // EFFECTS: returns all components of the review except for the user's comment
    public String viewReviewWithoutComment() {
        String strRating = String.format("%d", rating);
        return "Dish: " + foodName + " (" + foodType + "), Restaurant: " + restaurantName + ", Location: "
                + restaurantLoc + ", Rating: " + rating + " out of 5\n";
    }

    // EFFECTS: returns all components of the review except for the user's comment, but with no newline at the end
    public String viewReviewWithoutCommentForGui() {
        String strRating = String.format("%d", rating);
        return "Dish: " + foodName + " (" + foodType + "), Restaurant: " + restaurantName + ", Location: "
                + restaurantLoc + ", Rating: " + rating + " out of 5";
    }

    // Source: similar to save method in Account in TellerApp(https://github.students.cs.ubc.ca/CPSC210/TellerApp)
    // EFFECTS: saves the components of the review in the right order
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(foodName);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(foodType);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(restaurantName);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(restaurantLoc);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(rating);
        printWriter.print(Reader.DELIMITER);
        printWriter.println(comment);
    }
}
