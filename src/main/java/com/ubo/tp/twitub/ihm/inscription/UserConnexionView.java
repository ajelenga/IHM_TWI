package main.java.com.ubo.tp.twitub.ihm.inscription;

import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.EspacePersoControler;
import main.java.com.ubo.tp.twitub.ihm.espacePerso.EspacePersoView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserConnexionView {

    EspacePersoControler espacePersoControler;

    UserConnexionControler userConnexionControler;



    public JPanel jpanel;

    public UserConnexionView(UserConnexionControler userConnexionControler, EspacePersoControler espacePersoControler) {

        this.espacePersoControler = espacePersoControler;
        this.userConnexionControler = userConnexionControler;

        this.jpanel = new JPanel(new GridBagLayout());




        JLabel labtitre = new JLabel("Formulaire de connexion");
        labtitre.setBounds(60, 10, 300, 30);
        labtitre.setFont(new Font("Arial", Font.BOLD, 22));
        labtitre.setForeground(Color.white);


        JLabel labLogin = new JLabel("login :");
        labLogin.setBounds(20, 60, 300, 30);
        labLogin.setFont(new Font("Arial", Font.BOLD, 18));
        labLogin.setForeground(Color.BLACK);


        JTextField jtfLogin = new JTextField();
        jtfLogin.setBounds(130, 60, 200, 25);


        JLabel labpassword = new JLabel("password :");
        labpassword.setBounds(20, 100, 300, 30);
        labpassword.setFont(new Font("Arial", Font.BOLD, 18));
        labpassword.setForeground(Color.BLACK);


        JTextField jtfpassword = new JTextField();
        jtfpassword.setBounds(130, 100, 200, 25);


        JButton btconnexion = new JButton("Connexion");
        btconnexion.setBounds(150, 360, 150, 30);
        btconnexion.setBackground(Color.orange);
        btconnexion.setFont(new Font("Arial", Font.BOLD, 18));
        btconnexion.setForeground(Color.blue);
        btconnexion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("connexxxion "+ jtfpassword.getText()+jtfLogin.getText());
                User u = UserConnexionView.this.userConnexionControler.connect(jtfpassword.getText(),jtfLogin.getText());

            }
        });

        this.jpanel.add(labpassword, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.jpanel.add(jtfpassword, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 100, 0));

        this.jpanel.add(labLogin, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.jpanel.add(jtfLogin, new GridBagConstraints(2, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 100, 0));
        this.jpanel.add(btconnexion, new GridBagConstraints(2  , 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.espacePersoControler.setJpanel(this.jpanel);
    }

    public UserConnexionView() {

    }


    public JPanel getjpanel() {
        return jpanel;
    }
}
