package main.java.com.ubo.tp.twitub.ihm.inscription;

import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.EspacePersoControler;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.EspacePersoView;
import main.java.com.ubo.tp.twitub.ihm.interf.IObserversControler;

import javax.swing.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class UserConnexionControler {

    EspacePersoView espacePersoView;

    EspacePersoControler espacePersoControler;
    protected final Set<IObserversControler> mObservers;

    JPanel jpanel;
    IDatabase database;

    public UserConnexionControler(IDatabase database, EspacePersoControler espacePersoControler) {
        this.database = database;
        mObservers = new HashSet<>();
        this.espacePersoControler = espacePersoControler;

    }

    public User connect(String password, String nom) {
        for (Iterator<User> it = this.database.getUsers().iterator(); it.hasNext(); ) {
            User u = it.next();
            if (u.getName().equals(nom) && u.getUserPassword().equals(password)) {
                System.out.println("connexter");
                EspacePersoView espacePersoView = new EspacePersoView(u, espacePersoControler);
                jpanel = espacePersoView.getjPanel();
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

    public JPanel getJpanel() {
        return jpanel;
    }
}
