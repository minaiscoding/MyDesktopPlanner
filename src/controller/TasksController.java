package controller;

import model.AppData;

public class TasksController {
	 private AppData appData;

	 public AppData getAppData() {
	 	return appData;
	 }
	 public TasksController(AppData appdata)
	 {
		 this.appData = appdata;
	 }


}
