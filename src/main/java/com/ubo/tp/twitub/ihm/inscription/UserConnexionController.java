package main.java.com.ubo.tp.twitub.ihm.inscription;

import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.EspacePersoView;
import main.java.com.ubo.tp.twitub.ihm.interf.IObserversControler;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserConnexionController {

    protected final Set<IObserversControler> mObservers;

    JPanel jpanel;
    IDatabase database;

    public UserConnexionController(IDatabase database) {
        this.database = database;
        mObservers = new HashSet<>();
    }

    public User connect(String nom, String prenom) {
        System.out.println("Donnée" + nom + prenom);
        UUID uuid = new UUID(23, 34);
        User user = new User(uuid, nom, "kkk", nom, null, prenom);
        Set<User> lUsers = this.database.getUsers();
        for (User u : lUsers) {
            System.out.println(u.getName() + " value " + u.getName().equals(nom));
            if (u.getName().equals(nom)) {
                System.out.println("Connecter");
                EspacePersoView espacePersoView = new EspacePersoView(u);
                jpanel = espacePersoView.getJpanel();
                this.notifyObservers();
                return u;
            }
        }
        System.out.println("Non Connecter");
        return null;
    }


    public void addObserver(IObserversControler observer) {
        this.mObservers.add(observer);
    }

    public void removeObserver(IObserversControler observer) {
        this.mObservers.add(observer);
    }

    public void notifyObservers() {
        for (IObserversControler observer : mObservers) {
            observer.update(jpanel);
        }
    }
}
