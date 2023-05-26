package model;

import java.io.*;
import java.util.ArrayList;

public class HistoryData implements Serializable {
    private static final long serialVersionUID = 123456789L;

    private ArrayList<HistoryPlanner> historyList;

    /**
     * Constructeur de la classe HistoryData.
     */
    public HistoryData() {
        historyList = new ArrayList<>();
    }

    /**
     * Retourne la liste des historiques.
     *
     * @return La liste des historiques.
     */
    public ArrayList<HistoryPlanner> getHistoryList() {
        return historyList;
    }

    /**
     * Ajoute un historique à la liste.
     *
     * @param historyPlanner L'historique à ajouter.
     */
    public void addHistoryPlanner(HistoryPlanner historyPlanner) {
        historyList.add(historyPlanner);
    }

    /**
     * Sauvegarde les données dans un fichier.
     */
    public void saveToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("history.ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
            fileOut.close();
            System.out.println("Données enregistrées dans history.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge les données à partir d'un fichier.
     *
     * @return Les données chargées depuis le fichier.
     */
    public static HistoryData loadFromFile() {
        HistoryData historyData = null;
        File file = new File("history.ser");

        if (file.exists()) {
            try (FileInputStream fileIn = new FileInputStream(file);
                 ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
                historyData = (HistoryData) objectIn.readObject();
                System.out.println("Données chargées depuis history.ser");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // Crée un nouvel objet HistoryData si le fichier n'existe pas
            historyData = new HistoryData();
            System.out.println("Création d'un nouvel objet HistoryData.");
        }

        return historyData;
    }

}
