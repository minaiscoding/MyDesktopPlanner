package view;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignUpView extends VBox {
private Label titleLabel;
private Label usernameLabel;
private TextField usernameField;
private Label passwordLabel;
private PasswordField passwordField;
private Button signUpButton;
public SignUpView() {
    titleLabel = new Label("Sign Up");
    titleLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

    usernameLabel = new Label("Username:");
    usernameField = new TextField();

    passwordLabel = new Label("Password:");
    passwordField = new PasswordField();

    signUpButton = new Button("Sign Up");

    VBox vBox = new VBox();
    vBox.getChildren().addAll(titleLabel, usernameLabel, usernameField, passwordLabel, passwordField, signUpButton);
    vBox.setSpacing(10);
    vBox.setPadding(new Insets(10));

    this.getChildren().add(vBox);
}

public String getUsername() {
    return usernameField.getText();
}

public String getPassword() {
    return passwordField.getText();
}

public void clearFields() {
    usernameField.clear();
    passwordField.clear();
}

public void addSignUpListener(EventHandler<ActionEvent> listener) {
    signUpButton.setOnAction(listener);
}

public void showErrorMessage() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Username already exists");
    alert.setContentText("Please choose a different username");
    alert.showAndWait();
}

public void showSuccessMessage() {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText("You have successfully signed up");
    alert.showAndWait();
}
}