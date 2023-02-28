package main.java.com.ubo.tp.twitub.datamodel;

import main.java.com.ubo.tp.twitub.ihm.TwitubMainView;

import javax.swing.*;

public class ConsoleWatch implements IDatabaseObserver {

    private JTextArea t;

    public  ConsoleWatch(JTextArea textArea) {
          t = textArea;
    }

    @Override
    public void notifyTwitAdded(Twit addedTwit) {
        System.out.println("Twit added: " + addedTwit);
      t.setText("User added: " + addedTwit);

    }

    @Override
    public void notifyTwitDeleted(Twit deletedTwit) {
        System.out.println("Twit deleted: " + deletedTwit);
        t.setText("User added: " + deletedTwit);
    }

    @Override
    public void notifyTwitModified(Twit modifiedTwit) {
        System.out.println("Twit modified: " + modifiedTwit);
        t.setText("User added: " + modifiedTwit);
    }

    @Override
    public void notifyUserAdded(User addedUser) {

        System.out.println("User added: " + addedUser);
        t.setText("User added: " + addedUser);
    }

    @Override
    public void notifyUserDeleted(User deletedUser) {
        System.out.println("User deleted: " + deletedUser);
        t.setText("User added: " + deletedUser);
    }

    @Override
    public void notifyUserModified(User modifiedUser) {
        System.out.println("User modified: " + modifiedUser);
        t.setText("User added: " + modifiedUser);
    }
}
