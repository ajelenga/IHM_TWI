package main.java.com.ubo.tp.twitub.ihm.espacePerso.tweet;

import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class TweetsView implements IDatabaseObserver {

    private Set<Twit> listFollows;
    private JPanel jPanel;

    private JLabel userTagLabel;
    private JLabel notificationLabel;

    private ListViewT listViewT;

    public TweetsView(Set<Twit> tweet) {
        this.listFollows = tweet;

        // Création des composants
        userTagLabel = new JLabel("Tweet : " + this.listFollows.size());
        notificationLabel = new JLabel("");

        // Organisation des composants dans la JPanel
        jPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        jPanel.setBackground(new Color(255, 250, 240));
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        jPanel.add(userTagLabel, c);
        c.gridx = 1;
        jPanel.add(notificationLabel, c);
        this.listViewT = new ListViewT(this.listFollows,jPanel);

    }

    public JPanel getJPanel() {
        return jPanel;
    }

    @Override
    public void notifyTwitAdded(Twit addedTwit) {

        this.jPanel.removeAll();
        this.listFollows.add(addedTwit);
        userTagLabel.setText("Tweet : " + this.listFollows.size());
        notificationLabel.setText("New tweet added !");
        notificationLabel.setForeground(Color.RED);

        // Organisation des composants dans la JPanel
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        jPanel.add(userTagLabel, c);
        c.gridx = 1;
        jPanel.add(notificationLabel, c);
        this.listViewT = new ListViewT(this.listFollows,jPanel);
        jPanel.revalidate();
        jPanel.repaint();

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
}
