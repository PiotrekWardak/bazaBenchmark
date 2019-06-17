package pl.pge.di.bazaBenchamrk.model.utils;

import pl.pge.di.bazaBenchamrk.model.User;

public class CurrentUser {

    private static User currentUser;


    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        CurrentUser.currentUser = currentUser;
    }

    public static void clean(){
        CurrentUser.currentUser = null;
    }
}
