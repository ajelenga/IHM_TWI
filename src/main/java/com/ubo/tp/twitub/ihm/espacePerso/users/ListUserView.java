package main.java.com.ubo.tp.twitub.ihm.espacePerso.users;

import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.tweet.ListViewT;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class ListUserView implements IDatabaseObserver {
    private Set<User> listUsers;
    private JPanel jPanel;

    private ListUserT listUserT;

    private JTextField messageField;

    public ListUserView(Set<User> lu) {
        this.listUsers = lu;

        messageField = new JTextField(20);
        messageField.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton publierButton = new JButton("Publier");
        publierButton.setBackground(Color.ORANGE);
        publierButton.setForeground(Color.BLACK);
        publierButton.setFont(new Font("Arial", Font.BOLD, 18));
        publierButton.addActionListener(e -> rechercherUser());

        // Création des composants
        //  userTagLabel = new JLabel("Tweet : " + this.listFollows.size());
        //  notificationLabel = new JLabel("");

        // Organisation des composants dans la JPanel
        jPanel = new JPanel(new GridBagLayout());
        //GridBagConstraints c = new GridBagConstraints();
        // jPanel.setBackground(new Color(255, 250, 240));
        //c.gridx = 0;
        // c.gridy = 0;
        // c.insets = new Insets(10, 10, 10, 10);
        //  jPanel.add(userTagLabel, c);
        //c.gridx = 1;
        //jPanel.add(notificationLabel, c);
        this.listUserT = new ListUserT(this.listUsers,jPanel);
    }


        private void rechercherUser() {
            System.out.println("okkkkkkkkkkk");
        }

    public JPanel getJPanel() {
        return jPanel;
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

        GridBagConstraints constraints = new GridBagConstraints();

        this.jPanel.removeAll();
        this.listUsers.add(addedUser);

        this.listUserT = new ListUserT(this.listUsers,jPanel);
        jPanel.revalidate();
        jPanel.repaint();

    }

    @Override
    public void notifyUserDeleted(User deletedUser) {

    }

    @Override
    public void notifyUserModified(User modifiedUser) {

    }
}
