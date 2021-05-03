package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestWriter {
    private static final String TEST_FILE = "./data/testReview.txt";
    private Writer testWriter;
    private Review review1;
    private Review review2;

    private Review review3;
    private Review review4;
    private ReviewList reviews;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        try {
            review1 = new Review("Spaghetti with meatballs", "main dish", "The Best Spaghetti",
                    "123 Main St., Vancouver, BC", 4,
                    "It tastes delicious, but it's too expensive");
            review2 = new Review("Clam chowder", "appetizer", "The Best Spaghetti",
                    "123 Main St., Vancouver, BC", 1, "Unbelievably horrible!");
            review3 = new Review("Tomato soup", "appetizer", "Famous Steakhouse",
                    "789 UBC St., Vancouver, BC", 3, "It was not that great but not too bad");
            review4 = new Review("Mango icecream", "dessert", "Wonderful Buffet",
                    "40 Jupiter Dr., Toronto, ON", 5, "Fantastic!!!!");
            reviews = new ReviewList();
        } catch (NotDishTypeException e) {
            fail("Unexpected fail.");
        } catch (OutOfRangeException e) {
            fail("Unexpected fail.");
        }

    }

    // Source: similar to WriterTest in TellerApp(https://github.students.cs.ubc.ca/CPSC210/TellerApp)
    @Test
    void testWriteReviews() {
        // save reviews to file
        testWriter.write(review1);
        testWriter.write(review2);

        testWriter.close();
        // now read them back in and verify that the reviews have the expected values
        try {
            List<Review> reviews = Reader.readReviews(new File(TEST_FILE));
            Review review1 = reviews.get(0);
            assertEquals("Spaghetti with meatballs", review1.getFoodName());
            assertEquals("main dish", review1.getFoodType());
            assertEquals("The Best Spaghetti", review1.getRestaurantName());
            assertEquals("123 Main St., Vancouver, BC", review1.getRestaurantLoc());
            assertEquals(4, review1.getRating());
            assertEquals("It tastes delicious, but it's too expensive", review1.getComment());
            Review review2 = reviews.get(1);
            assertEquals("Clam chowder", review2.getFoodName());
            assertEquals("appetizer", review2.getFoodType());
            assertEquals(1, review2.getRating());
            assertEquals("Unbelievably horrible!", review2.getComment());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testSaveReviewList() {
        // adds reviews the the collection of reviews
        reviews.addReview(review3);
        reviews.addReview(review4);

        // save the collection to file
        testWriter.write(review3);
        testWriter.write(review4);
        testWriter.close();
    }
}
