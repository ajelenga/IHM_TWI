package main.java.com.ubo.tp.twitub.ihm.espacePerso.tweet;

import main.java.com.ubo.tp.twitub.datamodel.Twit;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

public class ListViewT {

    private Set<Twit> listFollows;

    private JPanel jpanel;


    public ListViewT(Set<Twit> listFollows, JPanel jPanel) {
        this.listFollows = listFollows;
        this.jpanel = jPanel;
        this.jpanel.setBackground(new Color(255, 250, 240));
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
        GridBagConstraints searchFieldConstraints = new GridBagConstraints();
        searchFieldConstraints.gridx = 0;
        searchFieldConstraints.gridy = 1;
        searchFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        searchFieldConstraints.insets = new Insets(10, 10, 10, 10);
        this.jpanel.add(searchField, searchFieldConstraints);

        // Ajouter un bouton pour effectuer la recherche
        JButton searchButton = new JButton("Rechercher");
        searchButton.setFont(new Font("Arial", Font.BOLD, 22));
        searchButton.setForeground(new Color(44, 62, 80));
        GridBagConstraints searchButtonConstraints = new GridBagConstraints();
        searchButtonConstraints.gridx = 1;
        searchButtonConstraints.gridy = 1;
        searchButtonConstraints.fill = GridBagConstraints.NONE;
        searchButtonConstraints.insets = new Insets(10, 10, 10, 10);
        this.jpanel.add(searchButton, searchButtonConstraints);

        // Ajouter un JLabel pour l'étiquette "Publier un tweet"
        JLabel publishLabel = new JLabel("Publier un tweet ");
        publishLabel.setFont(new Font("Arial", Font.BOLD, 16));
        publishLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints publishLabelConstraints = new GridBagConstraints();
        publishLabelConstraints.gridx = 0;
        publishLabelConstraints.gridy = 2;
        publishLabelConstraints.anchor = GridBagConstraints.LINE_END;
        publishLabelConstraints.insets = new Insets(10, 10, 10, 10);
        this.jpanel.add(publishLabel, publishLabelConstraints);

// Ajouter un JTextField pour publier un tweet
        JTextField publishField = new JTextField(20);
        GridBagConstraints publishFieldConstraints = new GridBagConstraints();
        publishFieldConstraints.gridx = 0;
        publishFieldConstraints.gridy = 3;
        publishFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        publishFieldConstraints.insets = new Insets(10, 10, 10, 10);
        this.jpanel.add(publishField, publishFieldConstraints);

        // Ajouter un bouton pour publier un tweet
        JButton publishButton = new JButton("Publier");
        publishButton.setFont(new Font("Arial", Font.BOLD, 22));
        publishButton.setForeground(new Color(44, 62, 80));
        GridBagConstraints publishButtonConstraints = new GridBagConstraints();
        publishButtonConstraints.gridx = 1;
        publishButtonConstraints.gridy = 3;
        publishButtonConstraints.insets = new Insets(10, 10, 10, 10);
        this.jpanel.add(publishButton, publishButtonConstraints);


        // Créer un JPanel pour afficher les tweets dans le JScrollPane
        JPanel tweetsPanel = new JPanel();
        tweetsPanel.setLayout(new BoxLayout(tweetsPanel, BoxLayout.PAGE_AXIS));

        for (Iterator<Twit> it = this.listFollows.iterator(); it.hasNext(); ) {
            Twit f = it.next();
            JLabel tweetLabel = new JLabel(f.getText());
            tweetsPanel.add(tweetLabel);
        }

        // Ajouter le JPanel avec les tweets au JScrollPane
        JScrollPane scrollPane = new JScrollPane(tweetsPanel);
        scrollPane.setBackground(Color.black);
        GridBagConstraints scrollPaneConstraints = new GridBagConstraints();
        scrollPaneConstraints.gridx = 0;
        scrollPaneConstraints.gridy = 4;
        scrollPaneConstraints.fill = GridBagConstraints.BOTH;
        scrollPaneConstraints.weightx = 1.0;
        scrollPaneConstraints.weighty = 1.0;
        scrollPaneConstraints.insets = new Insets(10, 10, 10, 10);
        this.jpanel.add(scrollPane, scrollPaneConstraints);

        // Ajouter une action pour le bouton de recherche
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                tweetsPanel.removeAll();
                for (Iterator<Twit> it = listFollows.iterator(); it.hasNext(); ) {
                    Twit f = it.next();
                    if (f.getText().contains(searchText)) {
                        JLabel tweetLabel = new JLabel(f.getText());
                        tweetsPanel.add(tweetLabel);
                    }
                }
                jpanel.revalidate();
                jpanel.repaint();
            }
        });

        // Créer un JLabel pour afficher le nombre de tweets
        JLabel tweetCountLabel = new JLabel("(" + listFollows.size() + " tweets)");
        tweetCountLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        GridBagConstraints tweetCountLabelConstraints = new GridBagConstraints();
        tweetCountLabelConstraints.gridx = 1;
        tweetCountLabelConstraints.gridy = 0;
        tweetCountLabelConstraints.anchor = GridBagConstraints.LINE_END;
        this.jpanel.add(tweetCountLabel, tweetCountLabelConstraints);

        // Ajouter un DocumentListener pour la recherche en temps réel
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateTweetsPanel();
            }

            public void insertUpdate(DocumentEvent e) {
                updateTweetsPanel();
            }

            public void removeUpdate(DocumentEvent e) {
                updateTweetsPanel();
            }

            // Mettre à jour le JPanel des tweets en fonction du texte de recherche
            public void updateTweetsPanel() {
                String searchText = searchField.getText();
                tweetsPanel.removeAll();
                for (Iterator<Twit> it = listFollows.iterator(); it.hasNext(); ) {
                    Twit f = it.next();
                    if (f.getText().contains(searchText)) {
                        JLabel tweetLabel = new JLabel(f.getText());
                        tweetsPanel.add(tweetLabel);
                    }
                }
                jpanel.revalidate();
                jpanel.repaint();
            }
        });

    }

}
