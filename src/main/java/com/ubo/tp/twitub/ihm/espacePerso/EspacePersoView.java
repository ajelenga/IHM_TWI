package main.java.com.ubo.tp.twitub.ihm.espacePerso;

import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.TwitubMainView;
import main.java.com.ubo.tp.twitub.ihm.inscription.UserConnexionView;
import main.java.com.ubo.tp.twitub.ihm.interf.IObserversControler;

import javax.swing.*;
import java.awt.*;

public class EspacePersoView{

    private User user;

    public JPanel jPanel;


    public EspacePersoView(User user) {

        this.user = user;

        System.out.println("Bienvenue dans ton espace personnel");
        this.jPanel = new JPanel(new GridBagLayout());

// Rafraîchir la frame

        System.out.println("eee");


        JPanel panel = new JPanel();
        JLabel label = new JLabel("Bienvenue dans ton espace !" +user.getName());
        this.jPanel.add(label);

        this.jPanel.add(panel, new GridBagConstraints(1, 0, 0, 0, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 100, 0));

        this.jPanel.revalidate();
        this.jPanel.repaint();



    }

    public JPanel getJpanel() {
        return jPanel;
    }


}
