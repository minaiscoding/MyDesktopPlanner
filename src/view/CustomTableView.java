package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableRow;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.Task;
import model.Priority;
import model.Status;
import model.Category;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeSet;

public class CustomTableView extends TableView<Task> {

    public CustomTableView(ArrayList<Task> taskList) {
        // Create table columns and set their cell value factories
        TableColumn<Task, String> nameCol = new TableColumn<>("Task");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Task, Duration> durationCol = new TableColumn<>("Duration");
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));

        TableColumn<Task, Priority> priorityCol = new TableColumn<>("Priority");
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));

        TableColumn<Task, LocalDate> deadlineCol = new TableColumn<>("Deadline");
        deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));

        // TableColumn<Task, Boolean> isScheduledCol = new TableColumn<>("Is Scheduled");
        // isScheduledCol.setCellValueFactory(new PropertyValueFactory<>("is_scheduled"));

        TableColumn<Task, Status> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Task, Category> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        getColumns().addAll(nameCol, durationCol, priorityCol, deadlineCol, statusCol, categoryCol);
        getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        if (taskList == null) {
            setItems(FXCollections.observableArrayList(new ArrayList<>()));
        } else {
            setItems(FXCollections.observableArrayList(taskList));
        }

        // Set row factory to customize row styling
        setRowFactory(new Callback<TableView<Task>, TableRow<Task>>() {
            @Override
            public TableRow<Task> call(TableView<Task> tableView) {
                return new TableRow<Task>() {
                    @Override
                    protected void updateItem(Task task, boolean empty) {
                        super.updateItem(task, empty);

                        if (task != null && task.isscheduled()) {
                            setStyle("-fx-background-color: lightgray;");
                        } else {
                            setStyle("");
                        }
                    }
                };
            }
        });
    }
}