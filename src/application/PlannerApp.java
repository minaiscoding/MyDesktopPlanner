package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlannerApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/controllers/AddUser.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Desktop Planner App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
