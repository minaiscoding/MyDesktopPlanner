package model;

import java.io.*;

public class DataHandler {
    private static final String FILENAME = "app_data.ser";

    /**
     * Sauvegarde les données de l'application.
     *
     * @param data Les données à sauvegarder.
     */
    public static void save(AppData data) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            out.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge les données de l'application.
     *
     * @return Les données chargées de l'application.
     */
    public static AppData load() {
        AppData data = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))) {
            data = (AppData) in.readObject();
        } catch (FileNotFoundException e) {
            // Le fichier n'existe pas, on crée un nouveau
            data = new AppData();
            save(data);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
