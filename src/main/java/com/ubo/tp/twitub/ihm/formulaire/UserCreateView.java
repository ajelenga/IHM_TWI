package main.java.com.ubo.tp.twitub.ihm.formulaire;

import main.java.com.ubo.tp.twitub.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserCreateView {

    UserCreateControler userCreateControler;

    public JPanel jpanel;


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

        JLabel labTag = new JLabel("Tag :");
        labTag.setFont(new Font("Arial", Font.BOLD, 22));
        labTag.setForeground(new Color(44, 62, 80));
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 0, 10, 20);
        panel.add(labTag, constraints);

        JTextField jtfTag = new JTextField(20);
        jtfTag.setFont(new Font("Arial", Font.PLAIN, 20));
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 0, 10, 0);
        panel.add(jtfTag, constraints);

        JLabel labMdp = new JLabel("Mot de passe :");
        labMdp.setFont(new Font("Arial", Font.BOLD, 22));
        labMdp.setForeground(new Color(44, 62, 80));
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 0, 10, 20);
        panel.add(labMdp, constraints);

        JPasswordField jtfMdp = new JPasswordField(20);
        jtfMdp.setFont(new Font("Arial", Font.PLAIN, 20));
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 0, 10, 0);
        panel.add(jtfMdp, constraints);

        JButton btajout = new JButton("Enregistrer");
        btajout.setBackground(Color.ORANGE);
        btajout.setForeground(Color.BLACK);
        btajout.setFont(new Font("Arial", Font.BOLD, 18));
        btajout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println(jtfprenom.getText() + jtfnom.getText());
                if (jtfprenom.getText().isEmpty() || jtfTag.getText().isEmpty()) {
                    System.out.println("valeur null");
                    JOptionPane.showMessageDialog(UserCreateView.this.jpanel, "Information manquantes pour créer un compte, merci de bien vouloir renseigner les champs (Tag et nom)", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    User user = UserCreateView.this.userCreateControler.inscription(jtfTag.getText(), jtfMdp.getText(), jtfnom.getText(), jtfprenom.getText());
                    if (user != null) {
                        JOptionPane.showMessageDialog(UserCreateView.this.jpanel, "Bravo, inscription OK !", "Info", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(UserCreateView.this.jpanel, "Inscription no efféctuée, le tag saisi existe dèja  !", "warnig", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(20, 0, 0, 0);
        panel.add(btajout, constraints);


        return panel;
    }

    public JPanel getJpanel() {
        return jpanel;
    }

    public JPanel getJrame() {
        return jpanel;
    }
}
