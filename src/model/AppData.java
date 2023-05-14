package model;

import java.io.Serializable;
import java.util.ArrayList;

public class AppData implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 4764117517080794724L;
    private ArrayList<User> users;
    private boolean isUserSignedIn;
    private User currentUser;

    public AppData(ArrayList<User> users, boolean isUserSignedIn) {
        this.users = users;
        this.isUserSignedIn = isUserSignedIn;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public boolean isUserSignedIn() {
        return isUserSignedIn;
    }

    public void setUserSignedIn(boolean userSignedIn) {
        isUserSignedIn = userSignedIn;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
