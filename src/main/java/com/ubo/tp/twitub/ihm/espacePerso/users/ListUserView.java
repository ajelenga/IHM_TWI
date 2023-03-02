package main.java.com.ubo.tp.twitub.ihm.espacePerso.users;

import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.EspacePersoControler;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class ListUserView  implements IDatabaseObserver {
    private Set<User> listUsers;
    private JPanel jPanel;
    public ListUserView(Set<User> lu) {
        this.listUsers = lu;
        String display="";
        for (User u : listUsers){
            display = display +" "+u.getName()+" ";
        }

        JLabel userTagLabel = new JLabel("Tweet : " + display);


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
