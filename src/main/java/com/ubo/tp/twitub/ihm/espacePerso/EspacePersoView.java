package main.java.com.ubo.tp.twitub.ihm.espacePerso;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.profil.ProfilView;

import javax.swing.*;
import java.awt.*;

public class EspacePersoView {

    private EspacePersoControler espacePersoControler;
    private User user;
    private JPanel jPanel;
    private JTextField messageField;


    public EspacePersoView(User user, EspacePersoControler espacePersoControler) {
        this.espacePersoControler = espacePersoControler;
        this.user = user;

        // Création des composants
        JLabel welcomeLabel = new JLabel("Bienvenue dans ton espace personnel, " + user.getName());
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 40));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        messageField = new JTextField(20);
        JButton publierButton = new JButton("Publier");
        publierButton.addActionListener(e -> publierMessage());

        JButton deconnexionButton = new JButton("Déconnexion");
        deconnexionButton.addActionListener(e -> deconnecter());

        JButton profilButton = new JButton("Mon profil");
        profilButton.addActionListener(e -> afficherProfil());

        // Organisation des composants dans la JPanel
        jPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(10, 10, 20, 10);
        jPanel.add(welcomeLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.weightx = 0.0;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(0, 10, 10, 10);
        jPanel.add(new JLabel("Message :"), c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 0, 10, 10);
        jPanel.add(messageField, c);

        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 0.0;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, 0, 10, 10);
        jPanel.add(publierButton, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(20, 10, 10, 10);
        jPanel.add(deconnexionButton, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        c.weightx = 1.0;
        c.insets = new Insets(10, 10, 10, 10);
        jPanel.add(profilButton, c);
    }

    public JPanel getJPanel() {
        return jPanel;
    }

    private void publierMessage() {
        String message = messageField.getText();
        Twit twit = new Twit(this.user, message);
        this.espacePersoControler.database.addTwit(twit);
        JOptionPane.showMessageDialog(EspacePersoView.this.jPanel, "Tweet publié " + message, "Info", JOptionPane.INFORMATION_MESSAGE);

    }

    private void deconnecter() {
        this.jPanel.removeAll();
        this.espacePersoControler.deconnecter();

    }

    private void afficherProfil() {
        // Créer une nouvelle instance de la vue du profil avec l'utilisateur actuel
        ProfilView profilView = new ProfilView(user);

        // Ouvrir la vue du profil dans une nouvelle fenêtre
        JFrame frame = new JFrame("Profil");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(profilView.getJPanel());
        frame.pack();
        frame.setVisible(true);

    }

    public JPanel getjPanel() {
        return this.jPanel;
    }
}