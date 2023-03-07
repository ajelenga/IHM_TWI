package main.java.com.ubo.tp.twitub.ihm.espacePerso.users;

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

public class ListUserT implements IDatabaseObserver {

    private User user;
    private Set<User> listUsers;

    private JPanel jpanel;
    private JPanel previousJpanel;

    public JPanel getJpanel() {
        return jpanel;
    }

    public ListUserT(Set<User> listUsers, JPanel jpanel, User user1, JPanel previousJpanel) {
        this.user = user1;
        this.listUsers = listUsers;
        this.jpanel = jpanel;
        this.jpanel.setBackground(new Color(255, 250, 240));
        this.jpanel.setLayout(new GridBagLayout());
        this.previousJpanel = previousJpanel;

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
        usersPanel.setLayout(new GridBagLayout());

        for (User u : this.listUsers) {
            constructionAuser(u, usersPanel);

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
                        constructionAuser(u, usersPanel);
                        System.out.println("Resultats utilisateurs" + u);
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

                        ListUserT.this.user.addFollowing(u.getUserTag());
                        System.out.println(u.getUserTag());
                        System.out.println(ListUserT.this.user.getFollows().size());
                    }
                }

                String res = "";
                for (String f : ListUserT.this.user.getFollows()) {
                    System.out.println(res);
                    res = res + " " + f + " ";

                }
                JOptionPane.showMessageDialog(ListUserT.this.jpanel, "tag follows " + res, "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Ajouter un bouton retour pour afficher le JPanel précédent
        JButton backButton = new JButton("Retour");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jpanel.removeAll();
                jpanel.add(previousJpanel);
                jpanel.revalidate();
                jpanel.repaint();
            }
        });
        backButton.setFont(new Font("Arial", Font.BOLD, 22));
        backButton.setForeground(new Color(44, 62, 80));
        GridBagConstraints backButtonConstraints = new GridBagConstraints();
        backButtonConstraints.gridx = 3;
        backButtonConstraints.gridy = 0;
        backButtonConstraints.fill = GridBagConstraints.NONE;
        backButtonConstraints.insets = new Insets(10, 10, 10, 10);
        this.jpanel.add(backButton, backButtonConstraints);

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
                        constructionAuser(u, usersPanel);
                    }
                }
                jpanel.revalidate();
                jpanel.repaint();
            }
        });


    }

    public void constructionAuser(User f, JPanel usersPanel) {
        JLabel userTagName = new JLabel("@" + f.getUserTag());
        JLabel userNameLabel = new JLabel(f.getName());
        JPanel atweet = new JPanel();
        atweet.setLayout(new GridBagLayout());
        atweet.setPreferredSize(new Dimension(usersPanel.getWidth(), 100));
        atweet.setBackground(new Color(255, 250, 240));

        // Ajouter les labels avec un GridBagConstraints pour les placer en haut
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 0, 10);
        atweet.add(userNameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 0, 10);
        atweet.add(userTagName, gbc);

        // Ajouter le JPanel "atweet" dans le JPanel "tweetsPanel"
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        // Ajouter un espace entre chaque tweet


        usersPanel.add(atweet, gbc);
        Component cc = Box.createRigidArea(new Dimension(0, 10));
        cc.setBackground(new Color(0, 0, 0));
        usersPanel.add(cc, gbc);
    }

    @Override
    public void notifyTwitAdded(Twit addedTwit) {

    }

    @Override
    public void notifyTwitDeleted(Twit deletedTwit) {

    }

    @Override
    public void notifyTwitModified(Twit modifiedTwit) {

    }

    @Override
    public void notifyUserAdded(User addedUser) {
        this.jpanel.removeAll();
        this.listUsers.add(addedUser);
        new ListUserT(this.listUsers, jpanel, this.user, previousJpanel);
        this.jpanel.revalidate();
        this.jpanel.repaint();


    }

    @Override
    public void notifyUserDeleted(User deletedUser) {

    }

    @Override
    public void notifyUserModified(User modifiedUser) {

    }
}

