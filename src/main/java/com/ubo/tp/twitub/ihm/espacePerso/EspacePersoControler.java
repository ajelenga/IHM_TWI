package main.java.com.ubo.tp.twitub.ihm.espacePerso;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.ihm.inscription.UserConnexionControler;
import main.java.com.ubo.tp.twitub.ihm.interf.IObserversControler;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class EspacePersoControler {

    protected final Set<IObserversControler> mObservers;
    private EntityManager mEntityManager;

    public EntityManager getmEntityManager() {
        return mEntityManager;
    }

    public void setmEntityManager(EntityManager mEntityManager) {
        this.mEntityManager = mEntityManager;
    }

    UserConnexionControler userConnexionControler;
    IDatabase database;

    JPanel jpanel;

    public EspacePersoControler(IDatabase database, EntityManager mEntityManager) {
        this.database = database;
        this.mObservers = new HashSet<>();
        this.mEntityManager = mEntityManager;

    }

    public void deconnecter() {
        System.out.println("deconnecter");
        System.out.println(this.jpanel);

        this.notifyObservers();

    }

    public void addObserver(IObserversControler observer) {
        System.out.println("ajout     observer");
        this.mObservers.add(observer);
    }

    public void removeObserver(IObserversControler observer) {
        this.mObservers.add(observer);
    }

    public void notifyObservers() {
        for (IObserversControler observer : mObservers) {
           /* JLabel labelDeconnexion = new JLabel("Vous êtes déconnecté");
            this.jpanel.add(labelDeconnexion);*/
            observer.update(this.jpanel);
        }
    }

    public void setJpanel(JPanel jpanel) {

        this.jpanel = jpanel;
    }


}
