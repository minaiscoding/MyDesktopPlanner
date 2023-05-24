package view;


import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;

import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import model.AppData;


public class HomePageView {
    private AppData appData;
    private BorderPane root;

    public HomePageView(AppData appData) {
        this.appData = appData;
        root = new BorderPane();
    }

    public void show(Stage stage) {
    	LocalDate startDate = appData.getCurrentUser().getPlanner().getFirst_Day();
    	LocalDate endDate = appData.getCurrentUser().getPlanner().getLast_Day();

        // Create the navigation bar
        NavBar navBar = new NavBar(stage, appData);
        VBox centerBox = new VBox();
        centerBox.setPadding(new Insets(20));
        centerBox.setSpacing(30);

        // Create the title label
        Label titleLabel = new Label(appData.getCurrentUser().getUsername()+"'s Calendar");
        titleLabel.setFont(Font.font("Lobster", FontWeight.BOLD, 50));  // Set the font for the title
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-text-fill: #577133");
        // Add the statsBox and badgesBox to the center of the BorderPane
        root.setCenter(centerBox);

        // Add the navigation bar to the left of the BorderPane
        root.setLeft(navBar);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(30);
        gridPane.setVgap(30);


        int col = 0;
        int row = 0;

        if (endDate == null || startDate == null) {
            LocalDate today = LocalDate.now();
            startDate = today;
            endDate = today.plusMonths(1);
        }
        LocalDate currentDate = startDate;
            while (!currentDate.isAfter(endDate)) {
                final LocalDate selectedDate = currentDate;  // Capture currentDate in a final variable

                Label dateLabel = new Label(selectedDate.toString());
                dateLabel.setStyle("-fx-border-color: darkgreen; -fx-border-width: 1px; -fx-padding: 5px;");
                dateLabel.setPrefSize(100, 100);  // Set the preferred width and height
                dateLabel.setFont(Font.font("Lobster", FontWeight.BOLD, 14));  // Set the font


                VBox dateBox = new VBox( dateLabel);
                dateBox.setSpacing(5);  // Set spacing between title and date label
                dateBox.setAlignment(Pos.CENTER);

                dateLabel.setOnMouseClicked(event -> {
                    displaySelectedDate(selectedDate);
                });

                gridPane.add(dateBox, col, row);

                currentDate = currentDate.plusDays(1);
                col++;
                if (col == 7) {
                    col = 0;
                    row++;
                }
            }


        centerBox.getChildren().addAll(titleLabel, gridPane);
        Scene scene = new Scene(root, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }


    private void displaySelectedDate(LocalDate date) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);

        Label dateLabel = new Label(date.toString());
        dateLabel.setStyle("-fx-font-size: 20px;");

        VBox root = new VBox(dateLabel);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
