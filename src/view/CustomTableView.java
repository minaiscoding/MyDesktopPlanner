package view;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.PrioTask;
import model.Status;
import model.Task;

import java.util.ArrayList;
import java.util.Date;

public class CustomTableView extends TableView<Task> {

    public CustomTableView(ArrayList<Task> taskList) {
        // Create table columns and set their cell value factories
        TableColumn<Task, String> nameCol = new TableColumn<>("Task");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Task, Integer> durationCol = new TableColumn<>("Duration (Minutes)");
        durationCol.setCellValueFactory(new PropertyValueFactory<>("durationInMinutes"));

        TableColumn<Task, PrioTask> priorityCol = new TableColumn<>("Priority");
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));

        TableColumn<Task, Date> deadlineCol = new TableColumn<>("Deadline");
        deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));

        TableColumn<Task, Boolean> unscheduledCol = new TableColumn<>("Unscheduled");
        unscheduledCol.setCellValueFactory(new PropertyValueFactory<>("unscheduled"));

        TableColumn<Task, Status> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Task, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        getColumns().addAll(nameCol, durationCol, priorityCol, deadlineCol, unscheduledCol, statusCol, categoryCol);

        if (taskList == null) {
            setItems(FXCollections.observableArrayList(new ArrayList<>()));
        } else {
            setItems(FXCollections.observableArrayList(taskList));
        }
    }
}
