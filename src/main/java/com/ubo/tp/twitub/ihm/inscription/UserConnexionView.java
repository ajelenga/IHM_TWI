package main.java.com.ubo.tp.twitub.ihm.inscription;

import main.java.com.ubo.tp.twitub.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserConnexionView {
    private final JPanel jpanel;
    private final UserConnexionController userConnexionController;

    public UserConnexionView(JFrame jFrameP, UserConnexionController userConnexionController) {

        this.userConnexionController = userConnexionController;
        this.jpanel = createPanel();
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Formulaire de connexion");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(20, 0, 10, 0);
        panel.add(titleLabel, constraints);

        JLabel loginLabel = new JLabel("login :");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 18));
        loginLabel.setForeground(Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 0, 10, 10);
        panel.add(loginLabel, constraints);

        JTextField loginField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 0, 10, 0);
        panel.add(loginField, constraints);

        JLabel passwordLabel = new JLabel("password :");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setForeground(Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 0, 10, 10);
        panel.add(passwordLabel, constraints);

        JTextField passwordField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 0, 10, 0);
        panel.add(passwordField, constraints);

        JButton connexionButton = new JButton("Connexion");
        connexionButton.setBackground(Color.ORANGE);
        connexionButton.setForeground(Color.BLUE);
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

    public JPanel getJpanel() {
        return jpanel;
    }
}
