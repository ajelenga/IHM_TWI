package main.java.com.ubo.tp.twitub.ihm.espacePerso.profil;

import main.java.com.ubo.tp.twitub.datamodel.User;

import javax.swing.*;
import java.awt.*;

public class ProfilView {

    private User user;
    private JPanel jPanel;

    private JPanel previousPanel;

    public ProfilView(User user, JPanel previousPanel) {
        this.user = user;
        this.previousPanel = previousPanel;

        // Création des composants
        JLabel userTagLabel = new JLabel("Pseudo : " + user.getUserTag());
        userTagLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel nameLabel = new JLabel("Nom : " + user.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        StringBuilder followsBuilder = new StringBuilder();
        for (String u : this.user.getFollows()) {
            followsBuilder.append("<br>").append(u).append("</br>");
        }
        JLabel followsLabel = new JLabel("<html>Abonnements : <br>" + followsBuilder.toString() + "</html>");

        JLabel avatarLabel = new JLabel(new ImageIcon(user.getAvatarPath()));

        // Organisation des composants dans la JPanel
        JPanel jPanel = new JPanel(new GridBagLayout());
        this.jPanel = jPanel;
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
        jPanel.add(titleLabel, titleLabelConstraints);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 1;
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

        // Ajouter un bouton retour pour afficher le JPanel précédent
        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> {
            jPanel.removeAll();
            jPanel.add(previousPanel);
            jPanel.revalidate();
            jPanel.repaint();
        });
        backButton.setFont(new Font("Arial", Font.BOLD, 22));
        backButton.setForeground(new Color(44, 62, 80));
        GridBagConstraints backButtonConstraints = new GridBagConstraints();
        backButtonConstraints.gridx = 3;
        backButtonConstraints.gridy = 0;
        backButtonConstraints.fill = GridBagConstraints.NONE;
        backButtonConstraints.insets = new Insets(10, 10, 10, 10);
        this.jPanel.add(backButton, backButtonConstraints);
    }


    public JPanel getJPanel() {
        return jPanel;
    }
}