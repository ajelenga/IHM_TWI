package main.java.com.ubo.tp.twitub.ihm.espacePerso;

import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.TwitubMainView;
import main.java.com.ubo.tp.twitub.ihm.inscription.UserConnexionView;

import javax.swing.*;
import java.awt.*;

public class EspacePersoView {

    private User user;

    public JFrame jrame;


    public EspacePersoView(JFrame jrame,User user) {

        this.user = user;

        System.out.println("Bienvenue dans ton espace personnel");
        this.jrame = jrame;
        Container contentPane = this.jrame.getContentPane();
        contentPane.removeAll();
// Rafraîchir la frame

        System.out.println("eee");


        JPanel panel = new JPanel();
        JLabel label = new JLabel("Bienvenue dans ton espace !" +user.getName());
        panel.add(label);

        this.jrame.add(panel, new GridBagConstraints(0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 200, 70), 100, 0));

        this.jrame.revalidate();
        this.jrame.repaint();

    }

    public JFrame getFrame() {
        return jrame;
    }
}
