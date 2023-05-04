package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {

    @FXML
    public void openSignUpPage() {
        try {
            // Load the sign-up page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controllers/AddUser.fxml"));
            Parent signUpPage = fxmlLoader.load();

            // Get the controller of the sign-up page
            UserController signUpController = fxmlLoader.getController();

            // Set up a new stage for the sign-up page
            Stage signUpStage = new Stage();
            signUpStage.setScene(new Scene(signUpPage));
            signUpStage.setTitle("Sign Up");

            // Call an initialization method if needed
            signUpController.initialize();

            // Show the sign-up page
            signUpStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
