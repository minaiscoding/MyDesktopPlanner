package controller;
import model.*;
import view.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UserController {
    private User userModel;
    private SignInView signInView;
    private SignUpView signUpView;

    public UserController(User userModel, SignInView signInView, SignUpView signUpView) {
        this.userModel = userModel;
        this.signInView = signInView;
        this.signUpView = signUpView;

        signInView.addSignInListener(new SignInListener());
        signUpView.addSignUpListener(new SignUpListener());
    }

    class SignInListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String username = signInView.getUsername();
            String password = signInView.getPassword();

            if (userModel.isValidUser(username, password)) {
                userModel.setLoggedIn(true);
                signInView.showSuccessMessage();
                signInView.clearFields();
            } else {
                signInView.showErrorMessage();
            }
        }
    }

    class SignUpListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String username = signUpView.getUsername();
            String password = signUpView.getPassword();

            if (userModel.registerUser(username, password)) {
                signUpView.showSuccessMessage();
                signUpView.clearFields();
            } else {
                signUpView.showErrorMessage();
            }
        }
    }
}
