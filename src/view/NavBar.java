package view;

import controller.TasksController;
import controller.WelcomeController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.AppData;

public class NavBar extends VBox {
    private Button homeButton;
    private Button tasksButton;
    private Button settingsButton;
    private Button logoutButton;

    public NavBar(Stage stage, AppData appData) {
        setPadding(new Insets(10));
        setSpacing(10);
        setBackground(new Background(new BackgroundFill(Color.web("#577133"), null, null)));
        setPrefWidth(Screen.getPrimary().getBounds().getWidth()*0.1); // Set the preferred width of the navigation bar

        homeButton = new Button("Home");
        tasksButton = new Button("Tasks");
        settingsButton = new Button("Settings");
        logoutButton = new Button("Logout");

        // Apply styles and font size to the buttons
        String buttonStyle = "-fx-background-color: transparent; -fx-text-fill: white;";
        String fontSize = "20px";
        homeButton.setStyle(buttonStyle);
        tasksButton.setStyle(buttonStyle);
        settingsButton.setStyle(buttonStyle);
        logoutButton.setStyle(buttonStyle);

        // Load and set the custom font
        Font customFont = Font.loadFont("file:Lobster-Regular.ttf", 20);
        homeButton.setFont(customFont);
        tasksButton.setFont(customFont);
        settingsButton.setFont(customFont);
        logoutButton.setFont(customFont);

        getChildren().addAll(homeButton, tasksButton, settingsButton, logoutButton);

        VBox.setMargin(logoutButton, new Insets(Screen.getPrimary().getBounds().getHeight()*0.6, 0, 0, 0));

        tasksButton.setOnAction(event -> {
            TasksController Tcontroller = new TasksController(appData);
            TaskView tasks = new TaskView(Tcontroller);
            tasks.show(stage);
        });

        logoutButton.setOnAction(event -> {
            appData.setUserSignedIn(false);
            WelcomePageView welcomePage = new WelcomePageView();
            WelcomeController welcomeController = new WelcomeController(appData);
            welcomePage.setController(welcomeController);
            welcomePage.show(stage);
        });
    }

    public Button getHomeButton() {
        return homeButton;
    }

    public Button getTasksButton() {
        return tasksButton;
    }

    public Button getSettingsButton() {
        return settingsButton;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }
}
