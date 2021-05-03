package persistence;

import model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Similar to ReaderTest in TellerApp(https://github.students.cs.ubc.ca/CPSC210/TellerApp)
public class TestReader {
    private Reader reader;
    @BeforeEach
    void runBefore() {
        reader = new Reader();
    }

    @Test
    void testParseReviews1() {
        try {
            List<Review> reviews = Reader.readReviews(new File("./data/reviews1.txt"));
            Review r1 = reviews.get(0);
            assertEquals("Spaghetti with meatballs", r1.getFoodName());
            assertEquals("main dish", r1.getFoodType());
            assertEquals("The Best Spaghetti", r1.getRestaurantName());
            assertEquals("123 Main St., Vancouver, BC", r1.getRestaurantLoc());
            assertEquals(4, r1.getRating());
            assertEquals("It tastes delicious, but it's too expensive", r1.getComment());

            Review r2 = reviews.get(1);
            assertEquals("Greek salad", r2.getFoodName());
            assertEquals("side dish", r2.getFoodType());
            assertEquals("Famous Steakhouse", r2.getRestaurantName());
            assertEquals("456 Main Ave., Burnaby, BC", r2.getRestaurantLoc());
            assertEquals(2, r2.getRating());
            assertEquals("It doesn't taste fresh...", r2.getComment());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readReviews(new File("./path/does/not/exist/review.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}