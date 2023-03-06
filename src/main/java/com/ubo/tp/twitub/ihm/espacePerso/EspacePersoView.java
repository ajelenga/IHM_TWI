package main.java.com.ubo.tp.twitub.ihm.espacePerso;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.profil.ProfilView;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.tweet.TweetsView;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.users.ListUserView;

import javax.swing.*;
import java.awt.*;

public class EspacePersoView {

    private EspacePersoControler espacePersoControler;
    private User user;
    private JPanel jPanel;
    private JTextField messageField;
    private TweetsView tweetsView;

    private ListUserView listUserView;
    public EspacePersoView(User user, EspacePersoControler espacePersoControler) {
        this.espacePersoControler = espacePersoControler;
        this.tweetsView = new TweetsView(this.espacePersoControler.database.getTwits());
        this.listUserView = new ListUserView(this.espacePersoControler.database.getUsers());
        this.espacePersoControler.database.addObserver(this.tweetsView);
        this.espacePersoControler.database.addObserver(this.listUserView);
        this.user = user;
        this.jPanel = createPanel();
    }

    public JPanel getJPanel() {
        return jPanel;
    }

    private JPanel createPanel() {
        // Création des composants

        JLabel welcomeLabel = new JLabel("Bienvenue dans ton espace personnel, " + user.getName());
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(41, 128, 185));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        messageField = new JTextField(20);
        messageField.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton publierButton = new JButton("Publier");
        publierButton.setBackground(Color.ORANGE);
        publierButton.setForeground(Color.BLACK);
        publierButton.setFont(new Font("Arial", Font.BOLD, 18));
        publierButton.addActionListener(e -> publierMessage());

        JButton deconnexionButton = new JButton("Déconnexion");
        deconnexionButton.setBackground(Color.ORANGE);
        deconnexionButton.setForeground(Color.BLACK);
        deconnexionButton.setFont(new Font("Arial", Font.BOLD, 18));
        deconnexionButton.addActionListener(e -> deconnecter());

        JButton profilButton = new JButton("Mon profil");
        profilButton.setBackground(Color.ORANGE);
        profilButton.setForeground(Color.BLACK);
        profilButton.setFont(new Font("Arial", Font.BOLD, 18));
        profilButton.addActionListener(e -> afficherProfil());

        JButton profilListTweet = new JButton("Mes tweets");
        profilListTweet.setBackground(Color.ORANGE);
        profilListTweet.setForeground(Color.BLACK);
        profilListTweet.setFont(new Font("Arial", Font.BOLD, 18));
        profilListTweet.addActionListener(e -> afficherTweet());

        JButton ListUser = new JButton("Les utilisateurs");
        ListUser.setBackground(Color.ORANGE);
        ListUser.setForeground(Color.BLACK);
        ListUser.setFont(new Font("Arial", Font.BOLD, 18));
        ListUser.addActionListener(e -> afficherUser());

        // Organisation des composants dans la JPanel
        jPanel = new JPanel(new GridBagLayout());
        jPanel.setBackground(new Color(255, 250, 240));
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
        JLabel messageLabel = new JLabel("Message :");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 22));
        messageLabel.setForeground(new Color(44, 62, 80));
        jPanel.add(messageLabel, c);

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

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        c.weightx = 1.0;
        c.insets = new Insets(10, 10, 10, 10);
        jPanel.add(profilListTweet, c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        c.weightx = 1.0;
        c.insets = new Insets(10, 10, 10, 10);
        jPanel.add(ListUser, c);

        return jPanel;
    }

    private void afficherUser() {
        // Créer une nouvelle instance de la vue du profil avec l'utilisateur actuel

        // Ouvrir la vue du profil dans une nouvelle fenêtre
        JFrame frame = new JFrame("Liste des utilisateurs");
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setBackground(new Color(255, 250, 240));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(listUserView.getJPanel());
        frame.pack();
        frame.setVisible(true);

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

    private JButton retourButton = new JButton("Retour");


    private void afficherProfil() {
        JPanel profilePanel = new ProfilView(user).getJPanel();
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(jPanel);
        frame.setContentPane(profilePanel);
        profilePanel.add(retourButton); // On ajoute le bouton de retour
        retourButton.addActionListener(e -> {
            frame.getContentPane().removeAll(); // On supprime tout ce qui est dans la fenêtre
            frame.getContentPane().add(this.getjPanel()); // On ajoute la vue de l'espace perso
            // On rafraîchit l'affichage
            frame.revalidate();
            frame.repaint();
        });


        frame.revalidate();
        frame.repaint();

    }


    private void afficherTweet() {
        JFrame frame = new JFrame("Liste de vos tweets");
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setBackground(new Color(255, 250, 240));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(tweetsView.getJPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public JPanel getjPanel() {
        return this.jPanel;
    }
}