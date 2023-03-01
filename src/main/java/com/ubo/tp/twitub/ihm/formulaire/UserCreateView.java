package main.java.com.ubo.tp.twitub.ihm.formulaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserCreateView {

    private JPanel jpanel;
    private UserCreateControler userCreateControler;

    public UserCreateView(UserCreateControler userCreateControler) {
        this.userCreateControler = userCreateControler;
        this.jpanel = createPanel();
    }

    public JPanel createPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 250, 240));
        GridBagConstraints constraints = new GridBagConstraints();


        JLabel titleLabel = new JLabel("Création de compte");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(41, 128, 185));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(40, 0, 20, 0);
        panel.add(titleLabel, constraints);

        JLabel labnom = new JLabel("Nom :");
        labnom.setFont(new Font("Arial", Font.BOLD, 22));
        labnom.setForeground(new Color(44, 62, 80));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(20, 0, 10, 20);
        panel.add(labnom, constraints);

        JTextField jtfnom = new JTextField(20);
        jtfnom.setFont(new Font("Arial", Font.PLAIN, 20));
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(20, 0, 10, 0);
        panel.add(jtfnom, constraints);

        JLabel labprenom = new JLabel("Prénom :");
        labprenom.setFont(new Font("Arial", Font.BOLD, 22));
        labprenom.setForeground(new Color(44, 62, 80));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 0, 10, 20);
        panel.add(labprenom, constraints);

        JTextField jtfprenom = new JTextField(20);
        jtfprenom.setFont(new Font("Arial", Font.PLAIN, 20));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 0, 10, 0);
        panel.add(jtfprenom, constraints);

        JButton btajout = new JButton("Enregistrer");
        btajout.setBackground(Color.ORANGE);
        btajout.setForeground(Color.BLACK); // Modifier la couleur du texte en noir
        btajout.setFont(new Font("Arial", Font.BOLD, 18));
        btajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println(jtfprenom.getText() + jtfnom.getText());
                UserCreateView.this.userCreateControler.inscription(jtfprenom.getText(), jtfnom.getText());
            }
        });
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(20, 0, 0, 0);
        panel.add(btajout, constraints);

        return panel;
    }

    public JPanel getJpanel() {
        return jpanel;
    }
}
