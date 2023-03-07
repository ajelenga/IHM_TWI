package main.java.com.ubo.tp.twitub.ihm.espacePerso;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.profil.ProfilView;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.tweet.ListViewT;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.users.ListUserT;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class EspacePersoView {

    private EspacePersoControler espacePersoControler;
    private User user;
    private JPanel jPanel;
    private ListUserT listUserT;
    private ListViewT listViewT;
    Set<Twit> twits;


    public EspacePersoView(User user, EspacePersoControler espacePersoControler) {
        this.espacePersoControler = espacePersoControler;
        this.user = user;
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
        c.gridy = 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);
        jPanel.add(profilButton, c);

        c.gridx = 1;
        c.gridy = 1;
        jPanel.add(profilListTweet, c);

        c.gridx = 2;
        c.gridy = 1;
        jPanel.add(ListUser, c);

        c.gridx = 3;
        c.gridy = 1;
        jPanel.add(deconnexionButton, c);

        return jPanel;
    }


    private void deconnecter() {
        this.jPanel.removeAll();
        this.espacePersoControler.deconnecter();

    }

    private JPanel previousPanel;

    private void afficherProfil() {
        previousPanel = this.getjPanel(); // On stocke la JPanel précédente
        JPanel profilePanel = new ProfilView(user, previousPanel).getJPanel();
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(jPanel);
        frame.setContentPane(profilePanel);
        frame.revalidate();
        frame.repaint();
    }

    private void afficherTweet() {
        previousPanel = this.getjPanel(); // On stocke la JPanel précédente
        this.listViewT = new ListViewT(this.twits, new JPanel(), this.user, previousPanel);
        JPanel tweetsPanel = listViewT.getJPanel();
        this.espacePersoControler.database.addObserver(this.listViewT);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(jPanel);
        frame.setContentPane(tweetsPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void afficherUser() {
        previousPanel = this.getjPanel(); // On stocke la JPanel précédente
        this.listUserT = new ListUserT(this.espacePersoControler.database.getUsers(), new JPanel(), this.user, previousPanel);
        JPanel usersPanel = listUserT.getJpanel();
        this.espacePersoControler.database.addObserver(this.listUserT);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(jPanel);
        frame.setContentPane(usersPanel);
        frame.revalidate();
        frame.repaint();
    }


    public JPanel getjPanel() {
        return this.jPanel;
    }
}