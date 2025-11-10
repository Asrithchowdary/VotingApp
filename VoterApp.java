

import java.util.InputMismatchException;
import java.util.Scanner;

// Custom Exceptions
class InvalidGenderException extends Exception {
    public InvalidGenderException(String message) {
        super(message);
    }
}

class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// --- Model Class ---
class Voter {
    private String name;
    private String gender;
    private int age;

    public Voter(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public void displayDetails() {
        System.out.println("\n Voter Details Captured Successfully!");
        System.out.println("----------------------------------");
        System.out.println("Name   : " + name);
        System.out.println("Gender : " + gender);
        System.out.println("Age    : " + age);
        System.out.println("----------------------------------\n");
    }
}

// --- Utility Class for Validation ---
class VoterValidator {
    public static void validateGender(String gender) throws InvalidGenderException {
        if (!(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))) {
            throw new InvalidGenderException(" Invalid Gender! Please enter 'M' for Male or 'F' for Female.");
        }
    }

    public static void validateAge(int age) throws InvalidAgeException, InvalidInputException {
        if (age < 0 || age > 120) {
            throw new InvalidInputException(" Invalid Age! Please enter age between 0 and 120.");
        } else if (age < 18) {
            throw new InvalidAgeException(" You must be at least 18 years old to vote.");
        }
    }
}

// --- Main Application ---
public class VoterApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("  Welcome to the Voter ID Application ");

        boolean continueApp = true;

        while (continueApp) {
            try {
                System.out.print("\nEnter Voter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Gender (M/F): ");
                String gender = sc.nextLine();

                VoterValidator.validateGender(gender);

                System.out.print("Enter Age: ");
                int age = sc.nextInt();
                sc.nextLine(); // consume newline

                VoterValidator.validateAge(age);

                // Create Voter Object
                Voter voter = new Voter(name, gender.toUpperCase(), age);
                voter.displayDetails();

            } catch (InvalidGenderException | InvalidInputException | InvalidAgeException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println(" Invalid Input! Please enter a valid number for age.");
                sc.nextLine(); // clear invalid input
            }

            System.out.print("Do you want to register another voter? (yes/no): ");
            String choice = sc.nextLine();
            continueApp = choice.equalsIgnoreCase("yes");
        }

        System.out.println("\nThank you for using the Voter ID Application! ");
        sc.close();
    }
}