package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void handleSignUp() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validate username and password fields
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please enter a username and password.");
            return;
        }

        // Check if the user already exists
        if (userExists(username)) {
            showAlert("Error", "User already exists. Please choose a different username.");
            return;
        }

        // Write username and password to a file
        try (FileWriter writer = new FileWriter("user_data.txt", true)) {
            writer.write("Username: " + username + "\n");
            writer.write("Password: " + password + "\n");
            showAlert("Success", "User signed up successfully!");
        } catch (IOException e) {
            showAlert("Error", "An error occurred while signing up the user.");
            e.printStackTrace();
        }
    }
    @FXML
    public void initialize() {
        // Initialize the username and password fields
        usernameField.setText("");
        passwordField.setText("");
    }

    private boolean userExists(String username) {
        try {
            // Read the file to check if the username already exists
            File file = new File("user_data.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("Username:") && line.substring("Username:".length()).trim().equals(username)) {
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
        } catch (IOException e) {
            showAlert("Error", "An error occurred while reading the user data file.");
            e.printStackTrace();
        }
        return false;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
