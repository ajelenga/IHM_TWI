package main.java.com.ubo.tp.twitub.ihm.espacePerso;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.profil.ProfilView;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.tweet.ListViewT;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.users.ListUserView;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class EspacePersoView {

    private EspacePersoControler espacePersoControler;
    private User user;
    private JPanel jPanel;
    private JTextField messageField;
    private ListViewT listViewT;
    Set<Twit> twits;

    private ListUserView listUserView;
    public EspacePersoView(User user, EspacePersoControler espacePersoControler) {
        this.espacePersoControler = espacePersoControler;
        this.user = user;
        this.listUserView = new ListUserView(this.espacePersoControler.database.getUsers(),this.user);
   //     this.espacePersoControler.database.addObserver(this.tweetsView);
        this.espacePersoControler.database.addObserver(this.listUserView);

        this.jPanel = createPanel();
        this.twits = this.espacePersoControler.database.getTwits();

    }

    private JPanel createPanel() {
        // Création des composants

        JLabel welcomeLabel = new JLabel("Bienvenue dans ton espace personnel, " + user.getName());
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(41, 128, 185));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

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
    private JButton retourButton1 = new JButton("Retour");


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
        this.listViewT = new ListViewT(this.twits, new JPanel(), this.user);
        JPanel tweetsPanel = listViewT.getJPanel();
        this.espacePersoControler.database.addObserver(this.listViewT);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(jPanel);
        frame.setContentPane(tweetsPanel);
        tweetsPanel.add(retourButton1); // On ajoute le bouton de retour
        retourButton1.addActionListener(e -> {
            frame.getContentPane().removeAll(); // On supprime tout ce qui est dans la fenêtre
            frame.getContentPane().add(this.getjPanel()); // On ajoute la vue de l'espace perso

            // On rafraîchit l'affichage
            frame.revalidate();
            frame.repaint();
        });
        frame.revalidate();
        frame.repaint();
    }


    public JPanel getjPanel() {
        return this.jPanel;
    }
}