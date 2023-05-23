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
    private Button projectsButton;
    private Button settingsButton;
    private Button historyButton;
    private Button statisticsButton;
    private Button logoutButton;

    public NavBar(Stage stage, AppData appData) {
        setPadding(new Insets(10));
        setSpacing(10);
        setBackground(new Background(new BackgroundFill(Color.web("#577133"), null, null)));
        setPrefWidth(Screen.getPrimary().getBounds().getWidth()*0.1); // Set the preferred width of the navigation bar

        homeButton = new Button("Home");
        tasksButton = new Button("Tasks");
        projectsButton = new Button("Projects");
        settingsButton = new Button("Settings");
        historyButton = new Button("History");
        statisticsButton = new Button("Statistics");
        logoutButton = new Button("Logout");

        // Apply styles and font size to the buttons
        String buttonStyle = "-fx-background-color: transparent; -fx-text-fill: white;";
        String fontSize = "20px";
        homeButton.setStyle(buttonStyle);
        tasksButton.setStyle(buttonStyle);
        projectsButton.setStyle(buttonStyle);
        settingsButton.setStyle(buttonStyle);
        historyButton.setStyle(buttonStyle);
        statisticsButton.setStyle(buttonStyle);
        logoutButton.setStyle(buttonStyle);

        // Load and set the custom font
        Font customFont = Font.loadFont("file:Lobster-Regular.ttf", 20);
        homeButton.setFont(customFont);
        projectsButton.setFont(customFont);
        tasksButton.setFont(customFont);
        settingsButton.setFont(customFont);
        logoutButton.setFont(customFont);
        historyButton.setFont(customFont);
        statisticsButton.setFont(customFont);

        getChildren().addAll(homeButton, tasksButton, settingsButton,historyButton,statisticsButton,projectsButton, logoutButton);

        VBox.setMargin(logoutButton, new Insets(Screen.getPrimary().getBounds().getHeight()*0.4, 0, 0, 0));
        settingsButton.setOnAction(event ->{
        	SettingsStage settingsStage = new SettingsStage();
            settingsStage.show();

        });

        tasksButton.setOnAction(event -> {
            TasksController Tcontroller = new TasksController(appData);
            TaskView tasks = new TaskView(Tcontroller);
            tasks.show(stage);
        });
        projectsButton.setOnAction(event -> {

            ProjectsView projets = new ProjectsView(appData);
            projets.show(stage);
        });
       statisticsButton.setOnAction(event -> {

           StatisticView stats = new StatisticView(appData);
            stats.show(stage);
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
