package main.java.com.ubo.tp.twitub.ihm.espacePerso.tweet;

import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

public class ListViewT implements IDatabaseObserver {
    private JLabel tweetCountLabel;
    private Set<Twit> listFollows;
    private JPanel jpanel;
    User user;
    public JPanel getJpanel() {
        return jpanel;
    }

    public ListViewT(Set<Twit> listFollows, JPanel jPanel, User user) {
        this.user = user;
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
                        if(searchText.charAt(0)=='@'){
                            System.out.println("########  search twit ##########");
                            if(f.getUserTags().equals(ListViewT.this.user.getUserTag())){
                                System.out.println("@@@ search twit @@@");
                                JLabel tweetLabel = new JLabel(f.getText());
                                tweetsPanel.add(tweetLabel);
                            }

                        } else if (false) {

                        }else{
                            JLabel tweetLabel = new JLabel(f.getText());
                            tweetsPanel.add(tweetLabel);
                        }

                    }
                }
                jpanel.revalidate();
                jpanel.repaint();
            }
        });

        publishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tweetText = publishField.getText();
                tweetsPanel.removeAll();
                if (tweetText != null && !tweetText.isEmpty()) {
                    String message = publishField.getText();
                    Twit twit = new Twit(user, message);
                    if(tweetText.toString().length()<250){
                        listFollows.add(twit);
                        JOptionPane.showMessageDialog(jPanel, "Tweet publié "+tweetText.toString().length() +"   " + message, "Info", JOptionPane.INFORMATION_MESSAGE);
                        int count = 0;
                        for (Iterator<Twit> it = listFollows.iterator(); it.hasNext(); ) {
                            Twit f = it.next();
                            if (f.getText().contains(searchField.getText())) {
                                JLabel tweetLabel = new JLabel(f.getText());
                                tweetsPanel.add(tweetLabel);
                                count++;
                            }
                        }
                        tweetCountLabel.setText("(" + listFollows.size() + " tweets)");
                        jpanel.revalidate();
                        jpanel.repaint();
                    }else{
                        JOptionPane.showMessageDialog(jPanel, "Tweet "+ message +" non publié sa taille est sup à 250" , "Info", JOptionPane.INFORMATION_MESSAGE);
                        int count = 0;
                        for (Iterator<Twit> it = listFollows.iterator(); it.hasNext(); ) {
                            Twit f = it.next();
                            if (f.getText().contains(searchField.getText())) {
                                JLabel tweetLabel = new JLabel(f.getText());
                                tweetsPanel.add(tweetLabel);
                                count++;
                            }
                        }
                        tweetCountLabel.setText("(" + listFollows.size() + " tweets)");
                        jpanel.revalidate();
                        jpanel.repaint();
                    }
                }
            }
        });

        // Créer un JLabel pour afficher le nombre de tweets
        this.tweetCountLabel = new JLabel("(" + listFollows.size() + " tweets)");
        this.tweetCountLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        GridBagConstraints tweetCountLabelConstraints = new GridBagConstraints();
        tweetCountLabelConstraints.gridx = 1;
        tweetCountLabelConstraints.gridy = 0;
        tweetCountLabelConstraints.anchor = GridBagConstraints.LINE_END;
        this.jpanel.add(this.tweetCountLabel, tweetCountLabelConstraints);
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
                int count = 0;
                for (Iterator<Twit> it = listFollows.iterator(); it.hasNext(); ) {
                    Twit f = it.next();
                    System.out.println(f.getText().contains(searchText));
                    char c = 0;
                    if( searchText.length()>0){
                        c= searchText.charAt(0);
                    }
                    if (f.getText().contains(recherche(searchText))) {
                        if('@'==c){
                            if(f.getTwiter().getUserTag().equals(ListViewT.this.user.getUserTag()) || f.getText().contains(ListViewT.this.user.getUserTag())){
                                JLabel tweetLabel = new JLabel(f.getText());
                                tweetsPanel.add(tweetLabel);
                            }
                        } else if ('#'==c) {
                            if(f.getText().contains(searchText)){

                                JLabel tweetLabel = new JLabel(f.getText());
                                tweetsPanel.add(tweetLabel);
                            }
                        }else{
                            JLabel tweetLabel = new JLabel(f.getText());
                            tweetsPanel.add(tweetLabel);
                        }
                    }
                }
                tweetCountLabel.setText("(" + count + " tweets)");
                jpanel.revalidate();
                jpanel.repaint();
            }
        });
    }
    private String recherche(String searchText) {
        if(searchText.length()>1){
            StringBuilder stringBuilder = new StringBuilder(searchText);
            stringBuilder.deleteCharAt(0);
            return stringBuilder.toString();

        }
        return searchText;
    }


    @Override
    public void notifyTwitAdded(Twit addedTwit) {
        this.jpanel.removeAll();
        this.listFollows.add(addedTwit);
        new ListViewT(this.listFollows, this.getJPanel(), this.user);
        this.jpanel.revalidate();
        this.jpanel.repaint();
    }

    @Override
    public void notifyTwitDeleted(Twit deletedTwit) {

    }

    @Override
    public void notifyTwitModified(Twit modifiedTwit) {

    }

    @Override
    public void notifyUserAdded(User addedUser) {

    }

    @Override
    public void notifyUserDeleted(User deletedUser) {

    }

    @Override
    public void notifyUserModified(User modifiedUser) {

    }

    public JPanel getJPanel() {
        return this.jpanel;
    }
}
