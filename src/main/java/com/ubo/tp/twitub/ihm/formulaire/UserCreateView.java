package main.java.com.ubo.tp.twitub.ihm.formulaire;

import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.UUID;

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

        JLabel labTag = new JLabel("Tag :");
        labTag.setBounds(20, 100, 300, 30);
        labTag.setFont(new Font("Arial", Font.BOLD, 18));
        labTag.setForeground(Color.BLACK);


        JTextField jtfTag = new JTextField();
        jtfTag.setBounds(130, 100, 200, 25);

        JLabel labMdp = new JLabel("Password :");
        labMdp.setBounds(20, 100, 300, 30);
        labMdp.setFont(new Font("Arial", Font.BOLD, 18));
        labMdp.setForeground(Color.BLACK);


        JTextField jtfMdp = new JTextField();
        jtfMdp.setBounds(130, 100, 200, 25);


        JButton btajout = new JButton("Enregistrer");
        btajout.setBounds(150, 360, 150, 30);
        btajout.setBackground(Color.orange);
        btajout.setFont(new Font("Arial", Font.BOLD, 18));
        btajout.setForeground(Color.blue);
        btajout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                    System.out.println(jtfprenom.getText()+jtfnom.getText());
                    if (jtfprenom.getText().isEmpty() || jtfTag.getText().isEmpty()){
                        System.out.println("valeur null");
                        JOptionPane.showMessageDialog(UserCreateView.this.jpanel, "Information manquantes pour créer un compte, merci de bien vouloir renseigner les champs (Tag et nom)", "Warning", JOptionPane.WARNING_MESSAGE);
                    }else{
                        User user = UserCreateView.this.userCreateControler.inscription(jtfTag.getText(), jtfMdp.getText(),jtfnom.getText(),jtfprenom.getText());
                        if(user!=null){
                            JOptionPane.showMessageDialog(UserCreateView.this.jpanel, "Bravo, inscription OK !", "Info", JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(UserCreateView.this.jpanel, "Inscription no efféctuée, le tag saisi existe dèja  !", "warnig", JOptionPane.WARNING_MESSAGE);
                        }
                    }
            }
        });

        this.jpanel.add(labprenom, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 110, 0));
        this.jpanel.add(jtfprenom, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 110, 0));

        this.jpanel.add(labnom, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 110, 0));
        this.jpanel.add(jtfnom, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 110, 0));

        this.jpanel.add(labTag, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 110, 0));
        this.jpanel.add(jtfTag, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 110, 0));

        this.jpanel.add(labMdp, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 110, 0));
        this.jpanel.add(jtfMdp, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 110, 0));

        this.jpanel.add(btajout, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 50, 0));
    }

    public JPanel getJrame() {
        return jpanel;
    }
}
