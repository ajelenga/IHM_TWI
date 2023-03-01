package main.java.com.ubo.tp.twitub.ihm.formulaire;

import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UserCreateControler {

    IDatabase database;
    public UserCreateControler(IDatabase database) {
        this.database = database;
    }


    public void inscription(String nom , String prenom){
        UUID uuid = new UUID(23,34);
        User user = new User(uuid,nom,"kkk",nom,null,prenom);


        this.database.addUser(user);
        System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeee"+user.getName());

    }
}
