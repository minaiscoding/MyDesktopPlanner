package view;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.AppData;
import model.Badge;
import model.DataHandler;
import model.Projet;
import model.Statistiques;
import model.Task;

public class StatisticView {
    private AppData appData;
    private BorderPane root;

    public StatisticView(AppData appData) {
        this.appData = appData;
        root = new BorderPane();
    }

    public void show(Stage stage) {
        ArrayList<Projet> projectsList = appData.getCurrentUser().getProjects();

        // Create the navigation bar
        NavBar navBar = new NavBar(stage, appData);

        VBox statsBox = new VBox();
        statsBox.getChildren().add(Statistiques.todayTasksStatus(appData.getCurrentUser().getPlanner().getCalendar()));

        for (Projet project : projectsList) {
            statsBox.getChildren().add(Statistiques.projectTasksStatus(project));
        }

        VBox badgesBox = new VBox();
        badgesBox.setPadding(new Insets(10));
        Label badgesTitleLabel = new Label("Badges");
        badgesTitleLabel.setFont(Font.font("Lobster", 20));
        badgesTitleLabel.setTextFill(Color.DARKGREEN);

        int totalBadges = appData.getCurrentUser().getPlanner().getTotalBadges();
        int goodBadges = appData.getCurrentUser().getPlanner().getNumBadge(Badge.GOOD);
        int veryGoodBadges = appData.getCurrentUser().getPlanner().getNumBadge(Badge.VERYGOOD);
        int excellentBadges = appData.getCurrentUser().getPlanner().getNumBadge(Badge.EXCELLENT);

        Label totalBadgesLabel = new Label("Total number of badges: " + totalBadges);
        Label goodBadgesLabel = new Label("Number of GOOD badges: " + goodBadges);
        Label veryGoodBadgesLabel = new Label("Number of VERYGOOD badges: " + veryGoodBadges);
        Label excellentBadgesLabel = new Label("Number of EXCELLENT badges: " + excellentBadges);

        badgesBox.getChildren().addAll(badgesTitleLabel, totalBadgesLabel, goodBadgesLabel, veryGoodBadgesLabel, excellentBadgesLabel);

        root.setBackground(new Background(new BackgroundFill(Color.web("#CADFAD"), null, null)));

        VBox centerBox = new VBox(statsBox, badgesBox);
        centerBox.setPadding(new Insets(10));

        // Add the statsBox and badgesBox to the center of the BorderPane
        root.setCenter(centerBox);

        // Add the navigation bar to the left of the BorderPane
        root.setLeft(navBar);

        Scene scene = new Scene(root, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

}
