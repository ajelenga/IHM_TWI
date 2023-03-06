package main.java.com.ubo.tp.twitub.ihm.espacePerso.tweet;

import main.java.com.ubo.tp.twitub.datamodel.Twit;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Set;

public class ListViewT {

    private Set<Twit> listFollows;

    private JPanel jpanel;


    public ListViewT(Set<Twit> listFollows, JPanel jPanel) {
        GridBagConstraints constraints = new GridBagConstraints();
        JLabel passwordLabel = new JLabel("Recherche :");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 22));
        passwordLabel.setForeground(new Color(44, 62, 80));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 0, 10, 20);
        this.jpanel.add(passwordLabel);

        this.listFollows = listFollows;
        this.jpanel = jPanel;
        this.jpanel.setBackground(Color.red);
        this.jpanel.setLayout(new GridBagLayout());

        // Ajouter un titre au JPanel
        JLabel titleLabel = new JLabel("Liste de vos tweets");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints titleLabelConstraints = new GridBagConstraints();
        titleLabelConstraints.gridx = 0;
        titleLabelConstraints.gridy = 0;
        titleLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        titleLabelConstraints.insets = new Insets(10, 10, 10, 10);
        this.jpanel.add(titleLabel, titleLabelConstraints);

        // Ajouter un JTextField de recherche
        JTextField searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.BOLD, 22));
        searchField.setForeground(new Color(44, 62, 80));
        GridBagConstraints searchFieldConstraints = new GridBagConstraints();
        searchFieldConstraints.gridx = 0;
        searchFieldConstraints.gridy = 1;
        searchFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        searchFieldConstraints.insets = new Insets(10, 10, 10, 10);
        this.jpanel.add(searchField, searchFieldConstraints);

        // Ajouter un bouton pour effectuer la recherche
        JButton searchButton = new JButton("Rechercher");
        GridBagConstraints searchButtonConstraints = new GridBagConstraints();
        searchButtonConstraints.gridx = 1;
        searchButtonConstraints.gridy = 1;
        searchButtonConstraints.fill = GridBagConstraints.NONE;
        searchButton.setBackground(Color.ORANGE);
        searchButton.setForeground(Color.BLACK); // Modifier la couleur du texte en noir
        searchButton.setFont(new Font("Arial", Font.BOLD, 18));
        searchButtonConstraints.insets = new Insets(10, 10, 10, 10);
        this.jpanel.add(searchButton, searchButtonConstraints);

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
        GridBagConstraints scrollPaneConstraints = new GridBagConstraints();
        scrollPaneConstraints.gridx = 0;
        scrollPaneConstraints.gridy = 2;
        scrollPaneConstraints.fill = GridBagConstraints.BOTH;
        scrollPaneConstraints.weightx = 1.0;
        scrollPaneConstraints.weighty = 1.0;
        scrollPaneConstraints.insets = new Insets(10, 10, 10, 10);
        this.jpanel.add(scrollPane, scrollPaneConstraints);
    }


}
