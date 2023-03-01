package main.java.com.ubo.tp.twitub.ihm;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.ConsoleWatch;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.formulaire.UserCreateView;
import main.java.com.ubo.tp.twitub.ihm.inscription.UserConnexionView;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

import static java.sql.DriverManager.println;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitubMainView {

    /**
     * ChoixFile
     */
    private String fileChooserString;
    /**
     * Fenetre du bouchon
     */
    protected JFrame mFrame;

    protected JFrame mFramePrincipale;

    /**
     * Base de donénes de l'application.
     */
    protected IDatabase mDatabase;

    /**
     * Gestionnaire de bdd et de fichier.
     */
    protected EntityManager mEntityManager;
    private JTextArea consoleTextArea;
    private File exchangeDirectory;

    /**
     * Constructeur.
     *
     * @param database , Base de données de l'application.
     */
    public TwitubMainView(IDatabase database, EntityManager entityManager) {
        this.mDatabase = database;
        this.mEntityManager = entityManager;
    }

    /**
     * Lance l'afficahge de l'IHM.
     */
    public void showGUI() {
        // Init auto de l'IHM au cas ou ;)
        if (mFrame == null) {
            this.initGUI();
        }

        // Affichage dans l'EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Custom de l'affichage
                JFrame frame = TwitubMainView.this.mFrame;
                // Configurer la JFrame
                TwitubMainView.this.mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                TwitubMainView.this.mFrame.setSize(800, 600);
                TwitubMainView.this.mFrame.setLocationRelativeTo(null);
                TwitubMainView.this.mFrame.setVisible(true);
            }
        });
    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        // Création de la fenetre principale
        mFrame = new JFrame("Twitub");

        // Charger les icônes des fichiers
        ImageIcon exitIcon = new ImageIcon("H:\\Documents\\IHM_TWI\\src\\main\\resources\\images\\exitIcon_20.png");
        ImageIcon aboutIcon = new ImageIcon("about.png");

        // Créer une zone de texte pour la console
        consoleTextArea = new JTextArea();
        consoleTextArea.setLineWrap(true);
        consoleTextArea.setColumns(25);
        consoleTextArea.setRows(2);


        consoleTextArea.setEditable(false);
        consoleTextArea.setPreferredSize(new Dimension(1000, 50));
        // Ajouter la zone de texte à un JScrollPane pour ajouter des barres de défilement


        //scrollPane = new JScrollPane(consoleTextArea);

        // Ajouter le JScrollPane à la JFrame
        //mFrame.getContentPane().add(scrollPane);

        mFramePrincipale = mFrame;

        // Configurer le menu
        JMenu fileMenu = new JMenu("Fichier");

        JMenuItem chooseExchangeDirMenuItem = new JMenuItem("Choisir un répertoire");
        chooseExchangeDirMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fileChooser.setDialogTitle("Choisir un répertoire d'échange");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    exchangeDirectory = fileChooser.getSelectedFile();
                    println("Répertoire d'échange sélectionné : " + exchangeDirectory.getAbsolutePath());
                    fileChooserString = exchangeDirectory.getAbsolutePath();
                }
            }
        });
        fileMenu.add(chooseExchangeDirMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Quitter", exitIcon);
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mFrame.dispose();
            }
        });
        fileMenu.add(exitMenuItem);

        JMenu helpMenu = new JMenu("?");
        ImageIcon aboutIconContent = new ImageIcon("C:\\Users\\bouaksel\\OneDrive - Capgemini\\Documents\\master_tiila\\projetIhm\\IHM_TWI\\src\\main\\resources\\images\\logo_50.jpg");

        JMenuItem aboutMenuItem = new JMenuItem("À propos", aboutIcon);
        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mFrame, "M2 UBO TILL\nDépartement Informatique", "À propos", JOptionPane.INFORMATION_MESSAGE, aboutIconContent);
            }
        });
        helpMenu.add(aboutMenuItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        mFrame.setJMenuBar(menuBar);
        mFrame.setSize(500,250);

        mFrame.setLayout(new GridBagLayout());
        JMenu menu;
        JMenuItem e1, e2, e3;
        JMenuBar menubar = new JMenuBar();

        menu = new JMenu("Menu");
        e1 = new JMenuItem("Inscription");
        e2 = new JMenuItem("Connexion");
        e3 = new JMenuItem("Annuler");

        menu.add(e1);

        menu.add(e2);
        menu.add(e3);

        menubar.add(menu);
        JButton btnConnexion = createButton("Connexion");
        JTextField textField1 = createTextField("Login", new Point(100, 40));
        JTextField textField2 = createTextField("Mot de passe", new Point(20, 80));
        mFrame.setJMenuBar(menubar);

        e1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Container contentPane = TwitubMainView.this.mFrame.getContentPane();
                contentPane.removeAll();
                UserCreateView userCreateView = new UserCreateView(TwitubMainView.this.mFramePrincipale,TwitubMainView.this.mDatabase);
             //   TwitubMainView.this.mFrame = userCreateView.getJrame();

                TwitubMainView.this.mFrame=userCreateView.getJrame();
// Rafraîchir la frame
                TwitubMainView.this.mFrame.revalidate();
                TwitubMainView.this.mFrame.repaint();
                System.out.println("eee");
            }
        });

        e2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Container contentPane = TwitubMainView.this.mFrame.getContentPane();
                contentPane.removeAll();
                UserConnexionView userCreateView = new UserConnexionView(TwitubMainView.this.mFramePrincipale,TwitubMainView.this.mDatabase);
                TwitubMainView.this.mFrame=userCreateView.getJrame();
// Rafraîchir la frame
                TwitubMainView.this.mFrame.revalidate();

                TwitubMainView.this.mFrame.repaint();
                System.out.println("eee");
            }
        });


        ConsoleWatch  consleConsoleWatch = new ConsoleWatch(consoleTextArea);
        this.mDatabase.addObserver(consleConsoleWatch);

    }

    private JTextField createTextField(String name, Point p){
        JTextField textField = new JTextField(name);
        textField.setBounds(p.x,p.y,200,28);
        return textField;
    }

    private JButton createButton(String name){
        JButton btn = new JButton(name);
        btn.setBounds(20,120,200,28);
        return btn;
    }


    /**
     * Ajoute un utilisateur fictif à la base de donnée.
     */
    protected void addUserInDatabase() {
        // Création d'un utilisateur fictif
        User newUser = this.generateUser();

        // Ajout de l'utilisateur à la base
        this.mDatabase.addUser(newUser);
    }

    /**
     * Génération et envoi d'un fichier utilisateur
     */
    protected void sendUser() {
        // Création d'un utilisateur fictif
        User newUser = this.generateUser();

        // Génération du fichier utilisateur
        this.mEntityManager.sendUser(newUser);
    }

    /**
     * Génération d'un utilisateur fictif.
     */
    protected User generateUser() {
        int randomInt = new Random().nextInt(99999);
        String userName = "MockUser" + randomInt;
        User newUser = new User(UUID.randomUUID(), userName, "--", userName, new HashSet<>(), "");

        return newUser;
    }

    /**
     * Ajoute un twit fictif à la base de données.
     */
    protected void addTwitInDatabase() {
        // Création 'un twit fictif
        Twit newTwit = this.generateTwit();

        // Ajout du twit
        this.mDatabase.addTwit(newTwit);
    }

    /**
     * Génération et envoi d'un fichier twit
     */
    protected void sendTwit() {
        // Création d'un twit fictif
        Twit newTwit = this.generateTwit();

        // Génération du fichier twit
        this.mEntityManager.sendTwit(newTwit);
    }

    /**
     * Génération d'un twit fictif.
     */
    protected Twit generateTwit() {
        // Si la base n'a pas d'utilisateur
        if (this.mDatabase.getUsers().size() == 0) {
            // Création d'un utilisateur
            this.addUserInDatabase();
        }

        // Récupération d'un utilisateur au hazard
        int userIndex = new Random().nextInt(this.mDatabase.getUsers().size());
        User randomUser = new ArrayList<User>(this.mDatabase.getUsers()).get(Math.max(0, userIndex - 1));

        // Création d'un twit fictif
        Twit newTwit = new Twit(randomUser, "Twit fictif!! #Mock #test ;)");

        return newTwit;
    }
}
