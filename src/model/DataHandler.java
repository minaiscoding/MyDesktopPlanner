package model;

import java.io.*;

public class DataHandler {
    private static final String FILENAME = "app_data.ser";

    public static void save(AppData data) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            out.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AppData load() {
        AppData data = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))) {
            data = (AppData) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
