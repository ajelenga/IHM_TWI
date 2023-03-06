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
        userTagLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel nameLabel = new JLabel("Nom : " + user.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        String res = "";
        for (String u : this.user.getFollows()) {
            res = res + "<html><br>" + u + "</br></html>";
        }
        JLabel followsLabel = new JLabel("<html>Abonnements : <br>" + res + "</html>");

        JLabel avatarLabel = new JLabel(new ImageIcon(user.getAvatarPath()));

        // Organisation des composants dans la JPanel
        JPanel jPanel = new JPanel(new GridBagLayout());
        this.jPanel = jPanel;
        GridBagConstraints c = new GridBagConstraints();
        jPanel.setBackground(new Color(255, 250, 240));

        // Ajouter un titre au JPanel
        JLabel titleLabel = new JLabel("Mon profil");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints titleLabelConstraints = new GridBagConstraints();
        titleLabelConstraints.gridx = 0;
        titleLabelConstraints.gridy = 0;
        titleLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        titleLabelConstraints.insets = new Insets(10, 10, 10, 10);
        this.jPanel.add(titleLabel, titleLabelConstraints);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 10, 10);
        jPanel.add(userTagLabel, c);

        c.gridx = 0;
        c.gridy = 2;
        jPanel.add(nameLabel, c);

        c.gridx = 0;
        c.gridy = 3;
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