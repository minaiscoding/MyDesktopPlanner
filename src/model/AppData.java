package model;

import java.io.Serializable;
import java.util.ArrayList;

public class AppData implements Serializable {
    /**
     * Classe représentant les données de l'application.
     */
    private static final long serialVersionUID = 4764117517080794724L;
    private ArrayList<User> users;
    private boolean isUserSignedIn;
    private User currentUser;

    /**
     * Constructeur de la classe AppData.
     *
     * @param users           Liste des utilisateurs de l'application.
     * @param isUserSignedIn  Indique si un utilisateur est connecté.
     */
    public AppData(ArrayList<User> users, boolean isUserSignedIn) {
        this.users = users;
        this.isUserSignedIn = isUserSignedIn;
    }

    /**
     * Constructeur par défaut de la classe AppData.
     * Initialise la liste des utilisateurs.
     */
    public AppData() {
        this.users = new ArrayList<User>();
    }

    /**
     * Retourne la liste des utilisateurs de l'application.
     *
     * @return La liste des utilisateurs.
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Modifie la liste des utilisateurs de l'application.
     *
     * @param users La nouvelle liste des utilisateurs.
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Vérifie si un utilisateur est connecté.
     *
     * @return Vrai si un utilisateur est connecté, sinon faux.
     */
    public boolean isUserSignedIn() {
        return isUserSignedIn;
    }

    /**
     * Définit si un utilisateur est connecté.
     *
     * @param userSignedIn Vrai si un utilisateur est connecté, sinon faux.
     */
    public void setUserSignedIn(boolean userSignedIn) {
        isUserSignedIn = userSignedIn;
    }

    /**
     * Retourne l'utilisateur actuel.
     *
     * @return L'utilisateur actuel.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Définit l'utilisateur actuel.
     *
     * @param currentUser Le nouvel utilisateur actuel.
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
