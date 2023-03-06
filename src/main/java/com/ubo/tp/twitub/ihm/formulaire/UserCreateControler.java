package main.java.com.ubo.tp.twitub.ihm.formulaire;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.User;

import java.util.*;

public class UserCreateControler {



    IDatabase database;

    EntityManager mEntityManager;
    public UserCreateControler(IDatabase database, EntityManager mEntityManager) {
        this.mEntityManager = mEntityManager;
        this.database = database;
    }


    public User inscription(String tag, String mdp,String nom,String prenom){
        UUID uuid = UUID.randomUUID();
        User user = new User(uuid,tag,mdp,nom,new HashSet<>(),prenom);
        Set<User> lu = this.database.getUsers();

        for(User u : lu){
            if(u.getName().equals(user.getName())){
                return null;
            }
        }

        this.database.addUser(user);
        this.mEntityManager.sendUser(user);
        System.out.println("L'utilisateur a bien été ajouté "+user.getName());
        return user;

    }
}
