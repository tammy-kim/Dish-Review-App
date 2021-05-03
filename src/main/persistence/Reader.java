package persistence;

import model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Source: Similar to Reader class in TellerApp (https://github.students.cs.ubc.ca/CPSC210/TellerApp)
// A reader that can read review's data from a file
public class Reader {

    public static final String DELIMITER = "|";

    // EFFECTS: returns a list of reviews parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Review> readReviews(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of reviews parsed from list of strings
    // where each string contains data for one review
    private static List<Review> parseContent(List<String> fileContent) {
        List<Review> reviews = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            reviews.add(parseReview(lineComponents));
        }

        return reviews;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split("\\" + DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 6 where element 0 represents the food's name, element 1 represents the food's type,
    // element 2 represents the restaurant's name, element 3 represents the location, element 4 represents the
    // food's rating, and element 5 represents the user's comment
    // EFFECTS: returns a review constructed from components
    private static Review parseReview(List<String> components) {
        Review review = null;
        String foodName = components.get(0);
        String foodType = components.get(1);
        String restaurantName = components.get(2);
        String restaurantLoc = components.get(3);
        int rating = Integer.parseInt(components.get(4));
        String comment = components.get(5);
        try {
            review = new Review(foodName, foodType, restaurantName, restaurantLoc, rating, comment);
        } catch (NotDishTypeException e) {
            e.printStackTrace();
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }
        return review;
    }

}
