package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TestReview {
    private Review r1;
    private Review r2;
    private Review r3;


    @BeforeEach
    public void runBefore() {
        try {
            r1 = new Review("Spaghetti with meatballs", "main dish", "The Best Spaghetti",
                    "123 Main St., Vancouver, BC", 4,
                    "It tastes delicious, but it's too expensive");
            r2 = new Review("Greek salad", "side dish", "Famous Steakhouse",
                    "456 Main Ave., Burnaby, BC", 2, "It doesn't taste fresh...");
            r3 = new Review("Clam chowder", "appetizer", "The Best Spaghetti",
                    "123 Main St., Vancouver, BC", 1, "Unbelievably horrible!");
        } catch (NotDishTypeException e) {
            fail("Unexpected exception");
        } catch (OutOfRangeException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void wrongTypeOfDish() {
        try {
            Review r4 = new Review("Tomato soup", "i don't know", "Best Buffet",
                    "100 Amazing St., Vancouver, BC", 3, "Mediocre");
            fail("Expected exception was not raised");
        } catch (NotDishTypeException e) {
            // expected this exception
        } catch (OutOfRangeException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void tooLowRating() {
        try {
            Review r5 = new Review("Spaghetti with meatballs", "main dish", "The Best Spaghetti",
                    "123 Main St., Vancouver, BC", 0,
                    "It tastes delicious, but it's too expensive");
            fail("Expected exception was not raised");
        } catch (NotDishTypeException e) {
            fail("Unexpected exception");
        } catch (OutOfRangeException e) {
            // expected this exception
        }
    }

    @Test
    public void tooHighRating() {
        try {
            Review r6 = new Review("Spaghetti with meatballs", "main dish", "The Best Spaghetti",
                    "123 Main St., Vancouver, BC", 6,
                    "It tastes delicious, but it's too expensive");
            fail("Expected exception was not raised");
        } catch (NotDishTypeException e) {
            fail("Unexpected exception");
        } catch (OutOfRangeException e) {
            // expected this exception
        }
    }

    @Test
    public void testGetFoodName() {
        assertEquals("Spaghetti with meatballs", r1.getFoodName());
        assertEquals("Greek salad", r2.getFoodName());
    }

    @Test
    public void testGetFoodType() {
        assertEquals("main dish", r1.getFoodType());
        assertEquals("side dish", r2.getFoodType());
        assertEquals("appetizer", r3.getFoodType());
    }

    @Test
    public void testGetRestaurantName() {
        assertEquals("Famous Steakhouse", r2.getRestaurantName());
        assertEquals("The Best Spaghetti", r3.getRestaurantName());
    }

    @Test
    public void testGetRestaurantLoc() {
        assertEquals("456 Main Ave., Burnaby, BC", r2.getRestaurantLoc());
        assertEquals("123 Main St., Vancouver, BC", r3.getRestaurantLoc());
    }

    @Test
    public void testGetRating() {
        assertEquals(4, r1.getRating());
        assertEquals(2, r2.getRating());
        assertEquals(1, r3.getRating());
    }

    @Test
    public void testGetComment() {
        assertEquals("It tastes delicious, but it's too expensive", r1.getComment());
    }

    @Test
    public void testViewReview() {
        assertEquals("Dish: Spaghetti with meatballs (main dish), Restaurant: The Best Spaghetti, " +
                "Location: 123 Main St., Vancouver, BC, Rating: 4 out of 5, Comment: It tastes delicious, but it's too " +
                "expensive\n", r1.viewReview());
    }

    @Test
    public void testViewReviewWithoutComment() {
        assertEquals("Dish: Spaghetti with meatballs (main dish), Restaurant: The Best Spaghetti, " +
                "Location: 123 Main St., Vancouver, BC, Rating: 4 out of 5\n", r1.viewReviewWithoutComment());
    }

    @Test
    public void testViewReviewWithoutCommentForGui() {
        assertEquals("Dish: Spaghetti with meatballs (main dish), Restaurant: The Best Spaghetti, " +
                "Location: 123 Main St., Vancouver, BC, Rating: 4 out of 5", r1.viewReviewWithoutCommentForGui());
    }
}