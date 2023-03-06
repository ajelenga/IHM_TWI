package main.java.com.ubo.tp.twitub.ihm.espacePerso.profil;

import main.java.com.ubo.tp.twitub.datamodel.User;

import javax.swing.*;
import java.awt.*;

public class ProfilView {

    private User user;
    private JPanel jPanel;

    public ProfilView(User user) {
        this.user = user;

        // Création des composants
        JLabel userTagLabel = new JLabel("Pseudo : " + user.getUserTag());
        JLabel nameLabel = new JLabel("Nom : " + user.getName());
        String res = " ";
        for(String  u : this.user.getFollows()){
                res = res +"  ||   "+ u +"";
        }
        JLabel followsLabel = new JLabel("Abonnements : " + res);

        JLabel avatarLabel = new JLabel(new ImageIcon(user.getAvatarPath()));

        // Organisation des composants dans la JPanel
        jPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        jPanel.setBackground(new Color(255, 250, 240));
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        jPanel.add(userTagLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        jPanel.add(nameLabel, c);

        c.gridx = 0;
        c.gridy = 2;
        jPanel.add(followsLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 3;
        jPanel.add(avatarLabel, c);
    }

    public JPanel getJPanel() {
        return jPanel;
    }
}