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
     * Ajoute un historique � la liste.
     *
     * @param historyPlanner L'historique � ajouter.
     */
    public void addHistoryPlanner(HistoryPlanner historyPlanner) {
        historyList.add(historyPlanner);
    }

    /**
     * Sauvegarde les donn�es dans un fichier.
     */
    public void saveToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("history.ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
            fileOut.close();
            System.out.println("Donn�es enregistr�es dans history.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge les donn�es � partir d'un fichier.
     *
     * @return Les donn�es charg�es depuis le fichier.
     */
    public static HistoryData loadFromFile() {
        HistoryData historyData = null;
        File file = new File("history.ser");

        if (file.exists()) {
            try (FileInputStream fileIn = new FileInputStream(file);
                 ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
                historyData = (HistoryData) objectIn.readObject();
                System.out.println("Donn�es charg�es depuis history.ser");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // Cr�e un nouvel objet HistoryData si le fichier n'existe pas
            historyData = new HistoryData();
            System.out.println("Cr�ation d'un nouvel objet HistoryData.");
        }

        return historyData;
    }

}
