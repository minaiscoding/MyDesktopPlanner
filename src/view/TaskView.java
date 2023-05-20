package view;

import controller.TasksController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Task;

import java.util.ArrayList;

public class TaskView {
    private TasksController controller;
    private BorderPane root;

    public TaskView(TasksController controller) {
        this.controller = controller;
        root = new BorderPane();
    }

    public void show(Stage stage) {
        ArrayList<Task> taskList = controller.getAppData().getCurrentUser().getTasks();

        // Create the navigation bar
        NavBar navBar = new NavBar(stage, controller.getAppData());

        // Create the custom table view
        CustomTableView tableView = new CustomTableView(taskList);

        // Create the title label
        Label titleLabel = new Label(controller.getAppData().getCurrentUser().getUsername() + "'s Tasks");

        titleLabel.setFont(Font.loadFont("file:Lobster-Regular.ttf", 50));
        titleLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: #577133;");

        titleLabel.setPadding(new Insets(20));

        // Set the background color of the root pane
        root.setBackground(new Background(new BackgroundFill(Color.web("#CADFAD"), null, null)));

        // Create a VBox to hold the title label and table view
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(40));
        vbox.getChildren().addAll(titleLabel, tableView);
      

        // Set the VBox as the center of the BorderPane
        root.setCenter(vbox);

        // Add the navigation bar to the left of the BorderPane
        root.setLeft(navBar);

        // Create a Scene and show it on the specified Stage
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
