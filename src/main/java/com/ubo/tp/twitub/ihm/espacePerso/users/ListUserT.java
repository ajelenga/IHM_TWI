package main.java.com.ubo.tp.twitub.ihm.espacePerso.users;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.EspacePersoView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

public class ListUserT {

    private User user;
    private Set<User> listUsers;

    private JPanel jpanel;

    public ListUserT(Set<User> listUsers, JPanel jpanel,User user1) {
        this.user = user1;
        this.listUsers = listUsers;
        this.jpanel = jpanel;
        this.jpanel.setBackground(new Color(255, 250, 240));
        this.jpanel.setLayout(new GridBagLayout());

        // Ajouter un titre au JPanel
        JLabel titleLabel = new JLabel("Liste des utilisateurs");
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

        // Ajouter un bouton pour suivre
        JButton suivreButton = new JButton("Suivre");
        suivreButton.setFont(new Font("Arial", Font.BOLD, 22));
        suivreButton.setForeground(new Color(44, 62, 80));
        GridBagConstraints suivreButtonConstraints = new GridBagConstraints();
        suivreButtonConstraints.gridx = 1;
        suivreButtonConstraints.gridy = 2;
        suivreButtonConstraints.fill = GridBagConstraints.NONE;
        suivreButtonConstraints.insets = new Insets(10, 10, 10, 10);
        this.jpanel.add(suivreButton, suivreButtonConstraints);

        // Créer un JPanel pour afficher les tweets dans le JScrollPane
        JPanel usersPanel = new JPanel();
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.PAGE_AXIS));

        for (Iterator<User> it = this.listUsers.iterator(); it.hasNext(); ) {
            User u = it.next();
            JLabel tweetLabel = new JLabel(u.getName());
            usersPanel.add(tweetLabel);

        }


        // Ajouter le JPanel avec les tweets au JScrollPane
        JScrollPane scrollPane = new JScrollPane(usersPanel);
        scrollPane.setBackground(Color.black);
        GridBagConstraints scrollPaneConstraints = new GridBagConstraints();
        scrollPaneConstraints.gridx = 0;
        scrollPaneConstraints.gridy = 2;
        scrollPaneConstraints.fill = GridBagConstraints.BOTH;
        scrollPaneConstraints.weightx = 1.0;
        scrollPaneConstraints.weighty = 1.0;
        scrollPaneConstraints.insets = new Insets(10, 10, 10, 10);
        this.jpanel.add(scrollPane, scrollPaneConstraints);

        // Ajouter une action pour le bouton de recherche
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                usersPanel.removeAll();
                for (Iterator<User> it = listUsers.iterator(); it.hasNext(); ) {
                    User u = it.next();
                    if (u.getName().contains(searchText)) {
                        JLabel tweetLabel = new JLabel(u.getName());

                        usersPanel.add(tweetLabel);
                        System.out.println("Resultats utilisateurs"+u);
                    }
                }
                jpanel.revalidate();
                jpanel.repaint();
            }
        });


        suivreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();

                for (Iterator<User> it = listUsers.iterator(); it.hasNext(); ) {
                    User u = it.next();
                    if (u.getName().contains(searchText)) {

                        ListUserT.this.user.addFollowing(u.getUserTag())    ;
                        System.out.println(u.getUserTag());
                        System.out.println(ListUserT.this.user.getFollows().size());
                    }
                }

                String res = "";
                for (String f : ListUserT.this.user.getFollows() ) {
                    System.out.println(res);
                   res = res +" "+ f + " ";

                }
                JOptionPane.showMessageDialog(ListUserT.this.jpanel, "tag follows " + res , "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Créer un JLabel pour afficher le nombre de tweets
        JLabel tweetCountLabel = new JLabel("(" + this.listUsers.size() + "Utilisateurs)");
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
                usersPanel.removeAll();
                for (Iterator<User> it = listUsers.iterator(); it.hasNext(); ) {
                    User u = it.next();
                    if (u.getName().contains(searchText)) {
                        JLabel tweetLabel = new JLabel(u.getName());
                        usersPanel.add(tweetLabel);
                    }
                }
                jpanel.revalidate();
                jpanel.repaint();
            }
        });


    }
}

