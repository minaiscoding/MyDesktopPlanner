package model;
import java.io.*;
import java.util.ArrayList;

public class HistoryData implements Serializable {
    private static final long serialVersionUID = 123456789L;

    private ArrayList<HistoryPlanner> historyList;

    public HistoryData() {
        historyList = new ArrayList<>();
    }

    public ArrayList<HistoryPlanner> getHistoryList() {
        return historyList;
    }

    public void addHistoryPlanner(HistoryPlanner historyPlanner) {
        historyList.add(historyPlanner);
    }

    public void saveToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("history.ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
            fileOut.close();
            System.out.println("Data saved to history.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HistoryData loadFromFile() {
        HistoryData historyData = null;
        File file = new File("history.ser");

        if (file.exists()) {
            try (FileInputStream fileIn = new FileInputStream(file);
                 ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
                historyData = (HistoryData) objectIn.readObject();
                System.out.println("Data loaded from history.ser");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // Create a new HistoryData object if the file doesn't exist
            historyData = new HistoryData();
            System.out.println("Creating a new HistoryData object.");
        }

        return historyData;
    }

}
