package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestReviewList {
    private ReviewList rs1;
    private ReviewList rs2;
    private ReviewList rs3;
    private ReviewList rs4;
    private ReviewList rs5;
    private ReviewList rs6;

    private Review r1;
    private Review r2;
    private Review r3;
    private Review r4;
    private Review r5;

    @BeforeEach
    public void runBefore() {
        rs1 = new ReviewList();
        rs2 = new ReviewList();
        rs3 = new ReviewList();
        rs4 = new ReviewList();
        rs5 = new ReviewList();
        rs6 = new ReviewList();
        try {
            r1 = new Review("Spaghetti with meatballs", "main dish", "The Best Spaghetti",
                    "123 Main St., Vancouver, BC", 4,
                    "It tastes delicious, but it's too expensive");
            r2 = new Review("Greek salad", "side dish", "Famous Steakhouse",
                    "456 Main Ave., Burnaby, BC", 2, "It doesn't taste fresh...");
            r3 = new Review("Clam chowder", "appetizer", "The Best Spaghetti",
                    "123 Main St. Vancouver, BC", 1, "Unbelievably horrible!");
            r4 = new Review("Rainbow sherbet", "dessert", "Lovely Buffet",
                    "789 Food St., New York, NY", 5, "The best sherbet I've ever had");
            r5 = new Review("Dragon Roll", "main dish", "Sushi Garden",
                    "100 Best St., Vancouver, BC", 3,
                    "It was okay, but I didn't like the sauce");
        } catch (NotDishTypeException e) {
            fail("Unexpected fail");
        } catch (OutOfRangeException e) {
            fail("Unexpected fail");
        }

    }

    @Test
    public void testAddReview() {
        assertEquals(0, rs1.howManyReviews());
        rs1.addReview(r1);
        rs1.addReview(r5);
        assertEquals(2, rs1.howManyReviews());
    }


    @Test
    public void testAllDishNames() {
        rs3.addReview(r1);
        assertEquals("Appetizer(s): \n\nMain dish(es): \nSpaghetti with meatballs\n\nSide dish(es): \n\n" +
                "Dessert(s): \n", rs3.allDishNames());
        assertTrue(rs3.allDishNames().contains("Main dish(es): \nSpaghetti with meatballs"));
        assertFalse(rs3.allDishNames().contains("Side dish(es): \nGreek salad"));
        rs3.addReview(r2);
        assertTrue(rs3.allDishNames().contains("Side dish(es): \nGreek salad"));
        assertFalse(rs3.allDishNames().contains("Dessert(s): \nRainbow sherbet"));
        rs3.addReview(r4);
        assertTrue(rs3.allDishNames().contains("Dessert(s): \nRainbow sherbet"));
        assertFalse(rs3.allDishNames().contains("Appetizer(s): \nClam chowder"));
        rs3.addReview(r3);
        assertTrue(rs3.allDishNames().contains("Appetizer(s): \nClam chowder"));
        assertFalse(rs3.allDishNames().contains("Main dish(es): \nDragon Roll"));

    }

    @Test
    public void testAllRestaurantNames() {
        rs4.addReview(r1);
        assertEquals("The Best Spaghetti in 123 Main St., Vancouver, BC\n", rs4.allRestaurantNames());
        rs4.addReview(r2);
        rs4.addReview(r3);
        assertTrue(rs4.allRestaurantNames().contains("The Best Spaghetti in 123 Main St., Vancouver, BC"));
        assertTrue(rs4.allRestaurantNames().contains("Famous Steakhouse in 456 Main Ave., Burnaby, BC"));
        assertFalse(rs4.allRestaurantNames().contains("Lovely Buffet in 789 Food St., New York, NY"));
    }

    @Test
    public void testSelectReview() {
        rs5.addReview(r1);
        rs5.addReview(r2);
        rs5.addReview(r3);
        rs5.addReview(r4);
        rs5.addReview(r5);
        assertEquals("Dish: Spaghetti with meatballs (main dish), Restaurant: The Best Spaghetti, " +
                "Location: 123 Main St., Vancouver, BC, Rating: 4 out of 5, " +
                        "Comment: It tastes delicious, but it's too expensive\n",
                rs5.selectReview("Spaghetti with meatballs", "The Best Spaghetti",
                        "123 Main St., Vancouver, BC"));
        assertEquals("", rs5.selectReview("Pepperoni pizza", "The Best Spaghetti",
                "123 Main St., Vancouver, BC"));
        assertEquals("", rs5.selectReview("Dragon Roll", "Tokyo sushi",
                "100 Best St., Vancouver, BC"));
        assertEquals("", rs5.selectReview("Rainbow sherbet", "Lovely Buffet",
                "123 Food St., New York, NY"));
        assertEquals("", rs5.selectReview("Spaghetti with meatballs", "Pasta place",
                "Wonderland"));
        assertEquals("", rs5.selectReview("Mushroom pizza", "The Best Spaghetti",
                "101 Awesome Blvd., Vancouver, BC"));
        assertEquals("", rs5.selectReview("Chocolate ice-cream", "Vancouver Buffet",
                "UBC"));
        assertEquals("", rs5.selectReview("a", "b", "c"));
    }

    @Test
    public void testSelectReviewForGui() {
        rs5.addReview(r1);
        rs5.addReview(r2);
        rs5.addReview(r3);
        rs5.addReview(r4);
        rs5.addReview(r5);
        assertEquals(r1, rs5.selectReviewForGui("Spaghetti with meatballs", "The Best Spaghetti",
                "123 Main St., Vancouver, BC"));
        assertNull(rs5.selectReviewForGui("Pepperoni pizza", "The Best Spaghetti",
                "123 Main St., Vancouver, BC"));
        assertNull(rs5.selectReviewForGui("Dragon Roll", "Tokyo sushi",
                "100 Best St., Vancouver, BC"));
        assertNull(rs5.selectReviewForGui("Rainbow sherbet", "Lovely Buffet",
                "123 Food St., New York, NY"));
        assertNull(rs5.selectReviewForGui("Spaghetti with meatballs", "Pasta place",
                "Wonderland"));
        assertNull(rs5.selectReviewForGui("Mushroom pizza", "The Best Spaghetti",
                "101 Awesome Blvd., Vancouver, BC"));
        assertNull(rs5.selectReviewForGui("Chocolate ice-cream", "Vancouver Buffet",
                "UBC"));
        assertNull(rs5.selectReviewForGui("a", "b", "c"));
    }

    @Test
    public void testViewEverything() {
        rs6.addReview(r1);
        rs6.addReview(r4);
        assertTrue(rs6.viewEverything().contains("Dish: Spaghetti with meatballs (main dish), " +
                "Restaurant: The Best Spaghetti, Location: 123 Main St., Vancouver, BC, Rating: 4 out of 5\n"));
        assertTrue(rs6.viewEverything().contains("Dish: Rainbow sherbet (dessert), Restaurant: Lovely Buffet, " +
                "Location: 789 Food St., New York, NY, Rating: 5 out of 5\n"));
        assertFalse(rs6.viewEverything().contains("Dish: Greek salad (side dish), Restaurant: Famous Steakhouse, " +
                "Location: 456 Main Ave., Burnaby, BC, Rating: 2 out of 5\n"));
    }

    @Test
    public void testGetReviews() {
        rs6.addReview(r1);
        rs6.addReview(r4);
        assertTrue(rs6.getReviews().contains(r1));
        assertTrue(rs6.getReviews().contains(r4));
        assertFalse(rs6.getReviews().contains(r3));

    }
}
