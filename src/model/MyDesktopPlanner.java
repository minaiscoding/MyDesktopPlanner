package model;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.*;
import controller.*;

public class MyDesktopPlanner extends Application {
private User userModel;
private SignInView signInView;
private SignUpView signUpView;
private UserController userController;
@Override
public void start(Stage primaryStage) {
    userModel = new User();
    signInView = new SignInView();
    signUpView = new SignUpView();
    userController = new UserController(userModel, signInView, signUpView);

    VBox vBox = new VBox();
    vBox.getChildren().addAll(signInView, signUpView);
    vBox.setSpacing(10);
    vBox.setPadding(new Insets(10));

    Scene scene = new Scene(vBox, 300, 250);

    primaryStage.setTitle("My Desktop Planner");
    primaryStage.setScene(scene);
    primaryStage.show();
}

public static void main(String[] args) {
    launch(args);
}
}