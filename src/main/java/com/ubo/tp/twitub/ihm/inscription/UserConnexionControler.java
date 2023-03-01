package main.java.com.ubo.tp.twitub.ihm.inscription;

import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UserConnexionControler {
    IDatabase database;
    public UserConnexionControler(IDatabase database) {
        this.database = database;
    }

    public User connect(String nom, String prenom) {
        System.out.println("Donnée"+ nom + prenom);
        UUID uuid = new UUID(23,34);
        User user = new User(uuid,nom,"kkk",nom,null,prenom);
        Set<User> lUsers =  this.database.getUsers();
        for(User u : lUsers){
            System.out.println(u.getName()+" value "+ u.getName().equals(nom));
            if(u.getName().equals(nom)){
                System.out.println("connexter");
                return u;
            }
        }
        System.out.println("Non connexter");
        return null;
    }
}
