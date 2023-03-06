package main.java.com.ubo.tp.twitub.ihm.espacePerso.tweet;

import main.java.com.ubo.tp.twitub.datamodel.Twit;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Set;

import static java.awt.Color.black;
import static java.awt.Color.red;

public class ListViewT extends JFrame {

    private Set<Twit> listFollows;

    private JPanel jpanel;


    public ListViewT(Set<Twit> listFollows, JPanel jPanel) {
        this.listFollows = listFollows;
        this.jpanel = jPanel;
        this.jpanel.setBackground(Color.red);

        // Ajouter un titre au JPanel
        JLabel titleLabel = new JLabel("Liste de vos tweets");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.jpanel.add(titleLabel);

        // Créer un JPanel pour afficher les tweets dans le JScrollPane
        JPanel tweetsPanel = new JPanel();
        tweetsPanel.setLayout(new BoxLayout(tweetsPanel, BoxLayout.PAGE_AXIS));

        for (Iterator<Twit> it = this.listFollows.iterator(); it.hasNext(); ) {
            Twit f = it.next();
            JLabel tweetLabel = new JLabel("Tweet: " + f.getText());
            tweetsPanel.add(tweetLabel);
        }

        // Ajouter le JPanel avec les tweets au JScrollPane
        JScrollPane scrollPane = new JScrollPane(tweetsPanel);
        scrollPane.setBackground(Color.black);
        scrollPane.setSize(100, 200);
        this.jpanel.add(scrollPane);
    }



}
