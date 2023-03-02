package main.java.com.ubo.tp.twitub.ihm.espacePerso.tweet;

import main.java.com.ubo.tp.twitub.datamodel.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Set;

public class TweetsView implements IDatabaseObserver {

    private Set<Twit> listFollows;
    private JPanel jPanel;


    public TweetsView(Set<Twit> tweet) {
        this.listFollows = tweet;

        // Création des composants
        JLabel userTagLabel = new JLabel("Tweet : " + this.listFollows.size());


        // Organisation des composants dans la JPanel
        jPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        jPanel.add(userTagLabel, c);

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

    }

    @Override
    public void notifyUserDeleted(User deletedUser) {

    }

    @Override
    public void notifyUserModified(User modifiedUser) {

    }
}
