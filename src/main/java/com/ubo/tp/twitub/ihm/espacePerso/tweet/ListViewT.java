package main.java.com.ubo.tp.twitub.ihm.espacePerso.tweet;

import main.java.com.ubo.tp.twitub.datamodel.Twit;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Set;

import static java.awt.Color.black;
import static java.awt.Color.red;

public class ListViewT {

    private Set<Twit> listFollows;

    private JPanel jpanel;


    public ListViewT(Set<Twit> listFollows, JPanel jPanel) {
        System.out.println("ooooooooooooo"+ listFollows.size());
        this.listFollows = listFollows;
        this.jpanel = jPanel;
        this.jpanel.setBackground(red);
        JPanel jpanel1 = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(black);
        this.jpanel.add(scrollPane);


        for (Iterator<Twit> it = this.listFollows.iterator(); it.hasNext(); ) {
            Twit f = it.next();
            System.out.println("fffffffff"+f.getText().toString());
            JLabel user = new JLabel("Tweet : " + f.getText().toString() );
            scrollPane.add(user);
        }


        this.jpanel.add(jpanel1);


    }


}
