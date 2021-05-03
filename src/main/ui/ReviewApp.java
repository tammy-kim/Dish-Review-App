package ui;

import java.util.List;
import java.util.Scanner;

import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import persistence.Reader;
import persistence.Writer;

// Review Application
public class ReviewApp {
    private static final String REVIEWS_FILE = "./data/myCollection.txt";
    private Scanner input;
    private ReviewList collection = new ReviewList();

    // EFFECTS: runs the Review Application
    public ReviewApp() {
        runReviews();
    }

    // Source: Similar to runTeller() in ui package in TellerApp (https://github.students.cs.ubc.ca/CPSC210/TellerApp)
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runReviews() {
        boolean keepRunning = true;
        input = new Scanner(System.in);
        String command = null;

        loadReviews();

        while (keepRunning) {
            menu();
            command = input.next();
            if (command.equals("quit")) {
                keepRunning = false;
            } else {
                commands(command);
            }
        }
        System.out.println("\n Closing Review App...");

    }

    // Source: Similar to loadAccounts() in TellerApp class in TellerApp
    //         (https://github.students.cs.ubc.ca/CPSC210/TellerApp)
    // MODIFIES: this
    // EFFECTS: loads reviews from REVIEWS_FILE, if that file exists;
    // otherwise initializes reviews with default values
    private void loadReviews() {
        try {
            List<Review> reviews = Reader.readReviews(new File(REVIEWS_FILE));
            for (Review r : reviews) {
                collection.addReview(r);

            }
        } catch (IOException e) {
            init();
        }
    }

    // Source: similar to init() in TellerApp class in TellerApp(https://github.students.cs.ubc.ca/CPSC210/TellerApp)
    // MODIFIES: this
    // EFFECTS: initializes reviews
    private void init() {
        collection = new ReviewList();
    }

    // Source: similar to saveAccounts() in TellerApp class in TellerApp
    //         (https://github.students.cs.ubc.ca/CPSC210/TellerApp)
    // EFFECTS: saves reviews to REVIEWS_FILE
    private void saveReviews() {
        try {
            Writer writer = new Writer(new File(REVIEWS_FILE));
            for (Review r : collection.getReviews()) {
                writer.write(r);
            }
            writer.close();
            System.out.println("Reviews saved to file " + REVIEWS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save reviews to " + REVIEWS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // Source: Similar to processCommand(String command) in ui package in TellerApp
    //         (https://github.students.cs.ubc.ca/CPSC210/TellerApp)
    // MODIFIES: this
    // EFFECTS: processes user command
    private void commands(String option) {
        if (option.equals("add")) {
            addingReview();
        } else if (option.equals("dishes")) {
            listingDishes();
        } else if (option.equals("restaurants")) {
            listingRestaurants();
        } else if (option.equals("select")) {
            selectingAndViewing();
        } else if (option.equals("save")) {
            saveReviews();
        } else if (option.equals("view")) {
            viewingEverything();
        } else {
            System.out.println("Selection not valid.");
        }
    }

    // Source: Similar to displayMenu() in ui package in TellerApp(https://github.students.cs.ubc.ca/CPSC210/TellerApp)
    // EFFECTS: shows menu of options to user to choose from
    private void menu() {
        System.out.println("\nSelect one of the following options below: ");
        System.out.println("\tadd -> add a review to your collection");
        System.out.println("\tdishes -> see a list of the names of dishes in your collection");
        System.out.println("\trestaurants -> see a list of the restaurants and their locations in your collection");
        System.out.println("\tselect -> enter the review that you would like to view");
        System.out.println("\tview -> view all the reviews you entered so far");
        System.out.println("\tsave -> save your collection");
        System.out.println("\tquit -> end the application");
    }

    // MODIFIES: this
    // EFFECTS:  conducts an addition of review to the collection of reviews
    private void addingReview() {
        Review review;
        input.nextLine();
        System.out.println("What's the name of the dish you would like to add?");
        String dishName = input.nextLine();
        System.out.println("Is the dish an appetizer, main dish, side dish, or dessert?");
        String dishType = input.nextLine();
        System.out.println("What's the name of the restaurant?");
        String restaurantName = input.nextLine();
        System.out.println("What's the address of the restaurant?");
        String restaurantLoc = input.nextLine();
        System.out.println("How was the food?");
        String comment = input.nextLine();
        System.out.println("Rate your dish out of 5.");
        int rating = input.nextInt();
        try {
            review = new Review(dishName, dishType, restaurantName, restaurantLoc, rating, comment);
            input.nextLine();
            askForConfirmation(review);
        } catch (NotDishTypeException e) {
            e.printStackTrace();
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }

    }

    // EFFECTS: asks the user if they really want to add the review, and adds the review if they say yes
    private void askForConfirmation(Review review) {
        System.out.println("Are you sure you want to add this dish? Type yes/no.");
        String answer = input.nextLine();
        if (answer.toLowerCase().equals("yes")) {
            collection.addReview(review);
            System.out.println(review.getFoodName() + " has been added to your collection.");
        }
    }


    // EFFECTS: provides the user a list of the names of dishes in the collection
    private void listingDishes() {
        System.out.println("Here's a list of all the dishes that you reviewed: \n");
        String listOfDishes = collection.allDishNames();
        System.out.println(listOfDishes);
    }


    // EFFECTS: provides the user a list of the names of restaurants in the collection
    private void listingRestaurants() {
        System.out.println("Here's a list of all the restaurants that served the dishes you reviewed: \n");
        String listOfRestaurants = collection.allRestaurantNames();
        System.out.println(listOfRestaurants);

    }

    // EFFECTS: provides the user a summary of all reviews in the collection
    private void viewingEverything() {
        System.out.println("Here are all the reviews in your collection. \n");
        System.out.println(collection.viewEverything());
    }

    // EFFECTS: prompts the user to view in a specific review(s) in the collection
    private void selectingAndViewing() {
        String review;
        input.nextLine();
        System.out.println("What's the name of the dish you would like to view?");
        String dishName = input.nextLine();
        System.out.println("What's the name of the restaurant that served this dish?");
        String restaurantName = input.nextLine();
        System.out.println("What's the address of the restaurant?");
        String restaurantLoc = input.nextLine();
        review = collection.selectReview(dishName, restaurantName, restaurantLoc);
        if (review.equals("")) {
            System.out.println("The review you entered does not exist.");
        } else {
            System.out.println("Here's your review(s)!");
            System.out.println(review);
        }

    }


}
