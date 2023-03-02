package main.java.com.ubo.tp.twitub.ihm.espacePerso;

import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.TwitubMainView;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.profil.ProfilView;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.tweet.TweetsView;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.users.ListUserView;
import main.java.com.ubo.tp.twitub.ihm.formulaire.UserCreateView;
import main.java.com.ubo.tp.twitub.ihm.inscription.UserConnexionView;
import main.java.com.ubo.tp.twitub.ihm.interf.IObserversControler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EspacePersoView  {

    private EspacePersoControler espacePersoControler;
    private User user;
    private JPanel jPanel;
    private JTextField messageField;

    TweetsView tweetsView;




    public EspacePersoView(User user, EspacePersoControler espacePersoControler) {
        this.espacePersoControler = espacePersoControler;
        this.tweetsView = new TweetsView(this.espacePersoControler.database.getTwits());
        this.espacePersoControler.database.addObserver( this.tweetsView);

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

        JButton profilListTweet = new JButton("Mes tweets");
        profilListTweet.addActionListener(e -> afficherTweet());

        JButton ListUser = new JButton("Les utilisateurs");
        ListUser.addActionListener(e -> afficherUser());

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
    }

    private void afficherUser() {
        // Créer une nouvelle instance de la vue du profil avec l'utilisateur actuel
        ListUserView listUserView = new ListUserView(this.espacePersoControler.database.getUsers());

        // Ouvrir la vue du profil dans une nouvelle fenêtre
        JFrame frame = new JFrame("Liste des utilisateurs");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(listUserView.getJPanel());
        frame.pack();
        frame.setVisible(true);

    }


    public JPanel getJPanel() {
        return jPanel;
    }

    private void publierMessage() {
        String message = messageField.getText();
        Twit twit = new Twit(this.user,message);
        this.espacePersoControler.database.addTwit(twit);
        JOptionPane.showMessageDialog(EspacePersoView.this.jPanel, "Tweet publié "+ message, "Info", JOptionPane.INFORMATION_MESSAGE);

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


    private void afficherTweet() {

        System.out.println("affihcer les tweet");
        // Ouvrir la vue du profil dans une nouvelle fenêtre
        JFrame frame = new JFrame("Tweets");
        frame.setPreferredSize(new Dimension(200,100));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(tweetsView.getJPanel());
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getjPanel(){
        return this.jPanel;
    }
}