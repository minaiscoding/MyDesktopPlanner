package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

import java.util.ArrayList;

public class TaskView {
    private User user;
    private BorderPane root;

    public TaskView(User user) {
        this.user = user;
        root = new BorderPane();
    }

    public void show(Stage stage) {
        ArrayList<Task> taskList = user.getTasks();
        System.out.print(taskList.size() );

        // Create the navigation bar
        VBox navBar = new VBox();
        navBar.setPadding(new Insets(10));
        navBar.setSpacing(10);
        Button homeButton = new Button("Home");
        Button tasksButton = new Button("Tasks");
        Button settingsButton = new Button("Settings");
        navBar.getChildren().addAll(homeButton, tasksButton, settingsButton);

        // Create the table and set the columns
        TableView<Task> table = new TableView<>();
        TableColumn<Task, String> nameCol = new TableColumn<>("Task");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TableColumn<Task, PrioTask> priorityCol = new TableColumn<>("Priority");
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
        TableColumn<Task, Status> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        table.getColumns().addAll(nameCol, priorityCol, statusCol);
        table.getItems().addAll(taskList);

        // Create the title label
        Label titleLabel = new Label(user.getUsername() + "'s Tasks");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Add the components to the root BorderPane
        root.setLeft(navBar);
        root.setCenter(table);
        root.setTop(titleLabel);

        // Create a Scene and show it on the specified Stage
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
