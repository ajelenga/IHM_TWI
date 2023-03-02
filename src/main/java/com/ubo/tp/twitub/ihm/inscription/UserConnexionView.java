package main.java.com.ubo.tp.twitub.ihm.inscription;

import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.EspacePersoControler;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.EspacePersoView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserConnexionView {
    private final JPanel jpanel;
    private final UserConnexionController userConnexionController;

    EspacePersoControler espacePersoControler;
    public UserConnexionView(JFrame jFrameP, UserConnexionController userConnexionController) {

        this.userConnexionController = userConnexionController;
        this.jpanel = createPanel();
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 250, 240));
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Connexion");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(41, 128, 185));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(40, 0, 20, 0);
        panel.add(titleLabel, constraints);

    public UserConnexionView(UserConnexionControler userConnexionControler, EspacePersoControler espacePersoControler) {
        JLabel loginLabel = new JLabel("Nom d'utilisateur :");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 22));
        loginLabel.setForeground(new Color(44, 62, 80));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(20, 0, 10, 20);
        panel.add(loginLabel, constraints);

        this.espacePersoControler = espacePersoControler;
        this.userConnexionControler = userConnexionControler;
        JTextField loginField = new JTextField(20);
        loginField.setFont(new Font("Arial", Font.PLAIN, 20));
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(20, 0, 10, 0);
        panel.add(loginField, constraints);

        JLabel passwordLabel = new JLabel("Mot de passe :");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 22));
        passwordLabel.setForeground(new Color(44, 62, 80));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 0, 10, 20);
        panel.add(passwordLabel, constraints);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 0, 10, 0);
        panel.add(passwordField, constraints);

        JButton connexionButton = new JButton("Connexion");
        connexionButton.setBackground(Color.ORANGE);
        connexionButton.setForeground(Color.BLACK); // Modifier la couleur du texte en noir
        connexionButton.setFont(new Font("Arial", Font.BOLD, 18));
        connexionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = userConnexionController.connect(passwordField.getText(), loginField.getText());
                System.out.println("Connexion " + passwordField.getText() + loginField.getText());
            }
        });
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(20, 0, 0, 0);
        panel.add(connexionButton, constraints);


        return panel;
    }

    public UserConnexionView() {

    public JPanel getJpanel() {
        return jpanel;
    }
}
