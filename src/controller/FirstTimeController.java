package controller;

import model.AppData;

public class FirstTimeController {
	 private AppData appData;

	 public AppData getAppData() {
	 	return appData;
	 }
	 public FirstTimeController(AppData appdata)
	 {
		 this.appData = appdata;
	 }

}
