package main.java.com.ubo.tp.twitub.ihm.formulaire;

import main.java.com.ubo.tp.twitub.datamodel.IDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserCreateView {

    UserCreateControler userCreateControler;

    public JPanel jpanel;


    public UserCreateView(UserCreateControler userCreateControler) {
        this.userCreateControler = userCreateControler;
        this.jpanel = new JPanel(new GridBagLayout());


        JLabel labtitre = new JLabel("Formulaire d'inscription");
        labtitre.setBounds(60, 10, 300, 30);
        labtitre.setFont(new Font("Arial", Font.BOLD, 22));
        labtitre.setForeground(Color.white);


        JLabel labnom = new JLabel("Nom :");
        labnom.setBounds(20, 60, 300, 30);
        labnom.setFont(new Font("Arial", Font.BOLD, 18));
        labnom.setForeground(Color.BLACK);


        JTextField jtfnom = new JTextField();
        jtfnom.setBounds(130, 60, 200, 25);


        JLabel labprenom = new JLabel("Prénom :");
        labprenom.setBounds(20, 100, 300, 30);
        labprenom.setFont(new Font("Arial", Font.BOLD, 18));
        labprenom.setForeground(Color.BLACK);


        JTextField jtfprenom = new JTextField();
        jtfprenom.setBounds(130, 100, 200, 25);


        JButton btajout = new JButton("Enregistrer");
        btajout.setBounds(150, 360, 150, 30);
        btajout.setBackground(Color.orange);
        btajout.setFont(new Font("Arial", Font.BOLD, 18));
        btajout.setForeground(Color.blue);
        btajout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                    System.out.println(jtfprenom.getText()+jtfnom.getText());
                    UserCreateView.this.userCreateControler.inscription(jtfprenom.getText(),jtfnom.getText());
            }
        });

        this.jpanel.add(labprenom, new GridBagConstraints(0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 200, 70), 100, 0));
        this.jpanel.add(jtfprenom, new GridBagConstraints(0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 200, 0), 100, 0));

        this.jpanel.add(labnom, new GridBagConstraints(0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 100, 70), 100, 0));
        this.jpanel.add(jtfnom, new GridBagConstraints(0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 100, 0), 100, 0));
        this.jpanel.add(btajout, new GridBagConstraints(0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 100, 0));
    }

    public JPanel getJrame() {
        return jpanel;
    }
}
