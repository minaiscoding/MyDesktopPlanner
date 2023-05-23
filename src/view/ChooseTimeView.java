package view;

import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.AppData;
import model.Planner;

import java.time.LocalDate;

public class ChooseTimeView extends Stage {
	private AppData appData;
	private Planner planner;

    public ChooseTimeView(AppData appData) {
    	this.appData = appData;
    	this.planner = appData.getCurrentUser().getPlanner();
        VBox root = new VBox();

        DatePicker datePicker = new DatePicker();
        // Customize DatePicker if needed

        // Set minimum and maximum dates
        LocalDate minDate = planner.getFirst_Day();
        LocalDate maxDate = planner.getLast_Day();
        datePicker.setDayCellFactory(getDatePickerDayCellFactory(minDate, maxDate));

        root.getChildren().add(datePicker);

        Scene scene = new Scene(root, 300, 200);

        setTitle("Choose Time");
        setScene(scene);
    }

    private Callback<DatePicker, DateCell> getDatePickerDayCellFactory(LocalDate minDate, LocalDate maxDate) {
        return datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(item.isBefore(minDate) || item.isAfter(maxDate));
            }
        };
    }
}
