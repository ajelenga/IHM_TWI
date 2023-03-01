package main.java.com.ubo.tp.twitub.ihm.twitFolder.controler;

import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

public class ControllerDB {


    private ControllerDB mEntityManager;
    private Database mDatabase;

    /**
     * Ajoute un utilisateur fictif à la base de donnée.
     */
    protected void addUserInDatabase() {
        // Création d'un utilisateur fictif
        User newUser = this.generateUser();

        // Ajout de l'utilisateur à la base
        this.mDatabase.addUser(newUser);
    }

    /**
     * Génération et envoi d'un fichier utilisateur
     */
    protected void sendUser() {
        // Création d'un utilisateur fictif
        User newUser = this.generateUser();

        // Génération du fichier utilisateur
        // this.mEntityManager.sendUser(newUser);
    }

    /**
     * Génération d'un utilisateur fictif.
     */
    protected User generateUser() {
        int randomInt = new Random().nextInt(99999);
        String userName = "MockUser" + randomInt;
        User newUser = new User(UUID.randomUUID(), userName, "--", userName, new HashSet<>(), "");

        return newUser;
    }

    /**
     * Ajoute un twit fictif à la base de données.
     */
    protected void addTwitInDatabase() {
        // Création 'un twit fictif
        Twit newTwit = this.generateTwit();

        // Ajout du twit
        this.mDatabase.addTwit(newTwit);
    }

    /**
     * Génération et envoi d'un fichier twit
     */
    protected void sendTwit() {
        // Création d'un twit fictif
        Twit newTwit = this.generateTwit();

        // Génération du fichier twit
        //this.mEntityManager.sendTwit(newTwit);
    }

    /**
     * Génération d'un twit fictif.
     */
    protected Twit generateTwit() {
        // Si la base n'a pas d'utilisateur
        if (this.mDatabase.getUsers().size() == 0) {
            // Création d'un utilisateur
            this.addUserInDatabase();
        }

        // Récupération d'un utilisateur au hazard
        int userIndex = new Random().nextInt(this.mDatabase.getUsers().size());
        User randomUser = new ArrayList<User>(this.mDatabase.getUsers()).get(Math.max(0, userIndex - 1));

        // Création d'un twit fictif
        Twit newTwit = new Twit(randomUser, "Twit fictif!! #Mock #test ;)");

        return newTwit;
    }
}
