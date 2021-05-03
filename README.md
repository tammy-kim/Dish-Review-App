# My Dish Reviews


## What the application does

This simple yet useful application allows you to leave a review of the dish you had at a specific restaurant.
All you need to do is:
- type the *name and type of the dish*
- enter the *name of the restaurant* that served it as well as its *location* 
- *rate* the food! 

You have your own collection of the reviews you made, which means you will always keep
track of your favourite dishes at every restaurant you visited, and you can order them again the next time you
go there.


## Who can use the application

I strongly recommend this application for all age groups (they can be easily used by almost anyone), 
especially the food lovers that often go to restaurants. 
The more often you try a variety of food in different places, the more you will find this app handy!


## What inspired me to do this project

I created this project simply because remembering all the food you liked and didn't like is hard to remember. 
I love going to ethnic restaurants and learning their food culture, but I often forget the name of the food 
that I had at those places (since it sounds foreign or unfamiliar to me), which is quite frustrating.
So I thought, what if I could make a program that keeps track of all the exact names of the dishes?


## User Stories

- As a user, I want to be able to add a review of a dish to my collection
- As a user, I want to be able to view a list of the names of dishes in my collection
- As a user, I want to be able to view a list of the restaurants' information that served the dishes in my collection
- As a user, I want to be able to select a review in my collection and view it (or multiple reviews if the dish has 
  different ratings) in detail
- As a user, I want to be able to view all of my reviews in a brief summary
- As a user, when I select the save option from the application menu, I want the option to save my collection of 
  reviews to file
- As a user, I want to be able to load my collection from file when the program starts


## Instructions for Grader

- You can generate the first required event, which is adding a review to the collection, by pressing "Add" button.
- You can generate the second required event, which is searching up a specific review to view its details, by pressing
 "View" button.
- You can locate my visual component by looking at the title and the subtitle which have small food icons.
- You can save the state of my application by pressing "Save" button.
- You can reload the state of my application by simply opening it. It automatically loads the saved collection.

## Phase 4: Task 2
I decided to work on this option: "Test and design a class that is robust.  You must have at least one method that
throws a checked exception. You must have one test for the case where the exception is expected and another where 
the exception is not expected."
I made Review class (in model package) robust by throwing two exceptions (NotDishTypeException and OutOfRangeException)
in its constructor. I have a test for the case where exception is expected and another that where it is not expected in
TestReview class.

## Phase 4: Task 3
Problems:
- lack of cohesion in ReviewApp class in ui package
- lack of cohesion in ReviewAppDisplay class in ui package
- ReviewList class implements Saveable interface which increases coupling unnecessarily 
  (because Review class already implements Saveable)
  
Changes I've made to improve the design of my code: 
- I changed ReviewList class such that it no longer implements Saveable interface. This solves the coupling issue.
