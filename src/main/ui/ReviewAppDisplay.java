package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import persistence.Reader;
import persistence.Writer;

// Review App Application for GUI
public class ReviewAppDisplay extends JFrame implements ActionListener {

    private JLabel newReview;
    private JLabel viewReview = new JLabel("");
    private JLabel comment = new JLabel("");
    private JLabel rating = new JLabel("");
    private JTextField textFoodName1;
    private JTextField textFoodType;
    private JTextField textRestName1;
    private JTextField textRestLoc1;
    private JTextField textComment;
    private JTextField textRating;

    private JTextField textFoodName2;
    private JTextField textRestName2;
    private JTextField textRestLoc2;

    private int row = 650;          // the row where an added review will be located
    private int column = 120;       // the column where an added review will be located
    private ReviewList collection = new ReviewList();
    private static final String REVIEWS_FILE = "./data/myCollection.txt";

    private JButton addButton = new JButton("Add");
    private JButton viewButton = new JButton("View");
    private JButton saveButton = new JButton("Save");

    // EFFECTS: runs all called methods to display Review App on a screen
    public ReviewAppDisplay() {
        super("Review App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 1000));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
        JLabel title = new JLabel("REVIEW APP");
        title.setLocation(600, 10);
        title.setSize(300, 100);
        title.setFont(new Font("Serif", Font.PLAIN, 30));
        add(title);
        loadReviews();
        addCakeImage();
        labelFields();
        textFields();
        addHeading();
        addOption();
        viewOption();
        saveOption();
        pack();
        setVisible(true);
        setResizable(true);
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
            for (Review review : collection.getReviews()) {
                newReview = new JLabel(review.viewReviewWithoutCommentForGui());
                newReview.setLocation(row, column);
                column = column + 20;
                newReview.setSize(1000, 20);
                add(newReview);
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
            JLabel savedString = new JLabel();
            savedString.setText("Reviews saved!");
            savedString.setLocation(20, 600);
            savedString.setSize(300, 20);
            add(savedString);
        } catch (FileNotFoundException e) {
            JLabel notSavedString = new JLabel();
            notSavedString.setText("Unable to save.");
            notSavedString.setLocation(20, 600);
            notSavedString.setSize(300, 20);
            add(notSavedString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // EFFECTS: adds the written subheading and the watermelon image for display
    public void addHeading() {
        addedReviewTitle();
        addWatermelonImage();
    }

    // EFFECTS: adds a subheading "Your Review Collection"
    public void addedReviewTitle() {
        JLabel reviewTitle = new JLabel();
        reviewTitle.setText("Your Review Collection");
        reviewTitle.setLocation(850, 50);
        reviewTitle.setSize(200, 100);
        reviewTitle.setFont(new Font("Serif", Font.PLAIN, 20));
        add(reviewTitle);
    }

    // EFFECTS: adds an image of a watermelon
    public void addWatermelonImage() {
        ImageIcon melon1 = new ImageIcon("./data/watermelon.png");
        ImageIcon melon2 = new ImageIcon("./data/watermelon.png");
        JLabel m1 = new JLabel(melon1);
        JLabel m2 = new JLabel(melon2);
        m1.setLocation(730, 50);
        m1.setSize(200, 80);
        m2.setLocation(970, 50);
        m2.setSize(200, 80);
        add(m1);
        add(m2);
    }

    // EFFECTS: adds an image of a cake
    public void addCakeImage() {
        ImageIcon cake = new ImageIcon("./data/cake.png");
        JLabel titleImg = new JLabel(cake);
        titleImg.setLocation(700, 10);
        titleImg.setSize(230, 80);
        add(titleImg);
    }

    // EFFECTS: adds "Add" button for display
    public void addOption() {
        addButton.setActionCommand("add");
        addButton.setBounds(10, 100, 80, 60);
        addButton.addActionListener(this);
        add(addButton);
    }

    // EFFECTS: shows the label for food's name
    public void addFoodName() {
        JLabel foodName = new JLabel("name of the dish: ");
        foodName.setLocation(100, 100);
        foodName.setSize(300, 20);
        add(foodName);

    }

    // EFFECTS: shows the label for food's type
    public void addFoodType() {
        JLabel foodType = new JLabel("appetizer, main dish, side dish, or dessert?: ");
        foodType.setLocation(100, 130);
        foodType.setSize(300, 20);
        add(foodType);
    }

    // EFFECTS: shows the label for restaurant's name
    public void addRestName() {
        JLabel restName = new JLabel("restaurant's name: ");
        restName.setLocation(100, 160);
        restName.setSize(300, 20);
        add(restName);
    }

    // EFFECTS: shows the label for restaurant's location
    public void addRestLoc() {
        JLabel restLoc = new JLabel("restaurant's address: ");
        restLoc.setLocation(100, 190);
        restLoc.setSize(300, 20);
        add(restLoc);
    }

    // EFFECTS: shows the label for the comment on the dish
    public void addComment() {
        JLabel comment = new JLabel("short comment on the food: ");
        comment.setLocation(100, 220);
        comment.setSize(300, 20);
        add(comment);
    }

    // EFFECTS: shows the label for the rating on the dish
    public void addRating() {
        JLabel rating = new JLabel("rate out of 5: ");
        rating.setLocation(100, 250);
        rating.setSize(300, 20);
        add(rating);
    }

    // EFFECTS: adds all the labels for "Add" and "View" buttons
    public void labelFields() {
        addFoodName();
        addFoodType();
        addRestName();
        addRestLoc();
        addComment();
        addRating();

        viewFoodName();
        viewRestName();
        viewRestLoc();
    }

    // EFFECTS: adds the textfield for food's name for the user to input for "Add"
    public void addFoodNameInput() {
        textFoodName1 = new JTextField(10);
        textFoodName1.setLocation(400, 100);
        textFoodName1.setSize(200, 20);
        add(textFoodName1);
    }

    // EFFECTS: adds the textfield for food's type for the user to input for "Add"
    public void addFoodTypeInput() {
        textFoodType = new JTextField(10);
        textFoodType.setLocation(400, 130);
        textFoodType.setSize(200, 20);
        add(textFoodType);
    }

    // EFFECTS: adds the textfield for restaurant's name for the user to input for "Add"
    public void addRestNameInput() {
        textRestName1 = new JTextField(10);
        textRestName1.setLocation(400, 160);
        textRestName1.setSize(200, 20);
        add(textRestName1);
    }

    // EFFECTS: adds the textfield for restaurant's location for the user to input for "Add"
    public void addRestLocInput() {
        textRestLoc1 = new JTextField(10);
        textRestLoc1.setLocation(400, 190);
        textRestLoc1.setSize(200, 20);
        add(textRestLoc1);
    }

    // EFFECTS: adds the textfield for comment for the user to input for "Add"
    public void addCommentInput() {
        textComment = new JTextField(10);
        textComment.setLocation(400, 220);
        textComment.setSize(200, 20);
        add(textComment);
    }

    // EFFECTS: adds the textfield for rating for the user to input for "Add"
    public void addRatingInput() {
        textRating = new JTextField(10);
        textRating.setLocation(400, 250);
        textRating.setSize(200, 20);
        add(textRating);
    }

    // EFFECTS: adds all inputs for "Add" and "View" buttons
    public void textFields() {
        // inputs for adding review
        addFoodNameInput();
        addFoodTypeInput();
        addRestNameInput();
        addRestLocInput();
        addCommentInput();
        addRatingInput();

        // inputs for selecting and viewing a review
        viewFoodNameInput();
        viewRestNameInput();
        viewRestLocInput();
    }

    // EFFECTS: adds "View" button for display
    public void viewOption() {
        viewButton.setActionCommand("view");
        viewButton.setBounds(10, 300, 80, 60);
        viewButton.addActionListener(this);
        add(viewButton);
    }

    // EFFECTS: shows the label for name of the dish
    public void viewFoodName() {
        JLabel foodName = new JLabel("name of the dish: ");
        foodName.setLocation(100, 300);
        foodName.setSize(300, 20);
        add(foodName);
    }

    // EFFECTS: shows the label for restaurant's name
    public void viewRestName() {
        JLabel restName = new JLabel("restaurant's name: ");
        restName.setLocation(100, 330);
        restName.setSize(300, 20);
        add(restName);
    }

    // EFFECTS: shows the label for restaurant's location
    public void viewRestLoc() {
        JLabel restLoc = new JLabel("restaurant's address: ");
        restLoc.setLocation(100, 360);
        restLoc.setSize(300, 20);
        add(restLoc);
    }

    // EFFECTS: adds the textfield for food's name for the user to input for "View"
    public void viewFoodNameInput() {
        textFoodName2 = new JTextField(10);
        textFoodName2.setLocation(400, 300);
        textFoodName2.setSize(200, 20);
        add(textFoodName2);
    }

    // EFFECTS: adds the textfield for restaurant's name for the user to input for "View"
    public void viewRestNameInput() {
        textRestName2 = new JTextField(10);
        textRestName2.setLocation(400, 330);
        textRestName2.setSize(200, 20);
        add(textRestName2);
    }

    // EFFECTS: adds the textfield for restaurant's location for the user to input for "View"
    public void viewRestLocInput() {
        textRestLoc2 = new JTextField(10);
        textRestLoc2.setLocation(400, 360);
        textRestLoc2.setSize(200, 20);
        add(textRestLoc2);
    }


    // EFFECTS: adds "Save" button for display
    public void saveOption() {
        saveButton.setActionCommand("save");
        saveButton.setBounds(10, 500, 80, 60);
        saveButton.addActionListener(this);
        add(saveButton);
    }

    // MODIFIES: this
    // EFFECTS: adds a review to the collection and displays it
    public void addingReview() {
        String foodName = textFoodName1.getText();
        String foodType = textFoodType.getText();
        String restName = textRestName1.getText();
        String restLoc = textRestLoc1.getText();
        String comment = textComment.getText();
        int rating = Integer.parseInt(textRating.getText());
        try {
            Review review = new Review(foodName, foodType, restName, restLoc, rating, comment);
            newReview = new JLabel(review.viewReviewWithoutCommentForGui());
            newReview.setLocation(row, column);
            column = column + 20;
            newReview.setSize(1200, 20);
            add(newReview);
            collection.addReview(review);
        } catch (NotDishTypeException e) {
            e.printStackTrace();
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }

    }


    // EFFECTS: shows the specific review to the user in detail with given inputs
    public void viewingReview() {
        String foodName = textFoodName2.getText();
        String restName = textRestName2.getText();
        String restLoc = textRestLoc2.getText();
        Review review = collection.selectReviewForGui(foodName, restName, restLoc);

        if (review == null) {
            viewReview.setText("No review found.");
            comment.setText("");
            rating.setText("");
        } else {
            viewReview.setText("Here are more details for the review you searched.");
            comment.setText("Your comment: " + review.getComment());
            rating.setText("Your rating: " + review.getRating() + " out of 5");
        }
        viewReview.setLocation(20, 400);
        viewReview.setSize(1000, 20);
        comment.setLocation(50, 430);
        comment.setSize(1000, 20);
        rating.setLocation(50, 460);
        rating.setSize(1000, 20);
        add(viewReview);
        add(comment);
        add(rating);
    }

    // EFFECTS: this is called then one of the buttons is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addingReview();
            repaint();
        } else if (e.getSource() == viewButton) {
            viewingReview();
            repaint();
        } else {
            saveReviews();
            repaint();
        }
    }
}
