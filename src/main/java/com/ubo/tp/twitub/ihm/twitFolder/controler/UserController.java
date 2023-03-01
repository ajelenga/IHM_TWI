package main.java.com.ubo.tp.twitub.ihm.twitFolder.controler;

import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.User;

import java.util.Set;
import java.util.UUID;

public class UserController {
    private IDatabase database;

    public UserController(IDatabase databaseP) {
        this.database = databaseP;
    }

    public User connect(String nom, String prenom) {
        System.out.println("Donnée" + nom + prenom);
        UUID uuid = new UUID(23, 34);
        User user = new User(uuid, nom, "kkk", nom, null, prenom);
        Set<User> lUsers = this.database.getUsers();
        for (User u : lUsers) {
            System.out.println(u.getName() + " value " + u.getName().equals(nom));
            if (u.getName().equals(nom)) {
                System.out.println("connexter");
                return u;
            }
        }
        System.out.println("Non connexter");
        return null;
    }
}
