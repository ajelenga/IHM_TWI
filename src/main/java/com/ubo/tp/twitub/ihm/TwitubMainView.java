package main.java.com.ubo.tp.twitub.ihm;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.core.Twitub;
import main.java.com.ubo.tp.twitub.datamodel.ConsoleWatch;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.formulaire.UserCreateControler;

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

    ActionListener connexionListener;

    ActionListener createListener;

    UserCreateControler userCreateControler;

    /**
     * ChoixFile
     */
    private String fileChooserString;
    /**
     * Fenetre du bouchon
     */
    protected JFrame mFrame;


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
        this.userCreateControler = new UserCreateControler(this.mDatabase);

    }

    Twitub twitub;


    /**
     * Lance l'afficahge de l'IHM.
     */
    public void showGUI(Twitub twitubp) {
        this.twitub = twitubp;
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
                TwitubMainView.this.mFrame.setSize(1200, 800);
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


        // Configurer le menu
        JMenu menu;
        menu = new JMenu("Menu");
        JMenuBar menuBar = new JMenuBar();
        mFrame.setJMenuBar(menuBar);
        mFrame.setSize(500, 250);
        mFrame.setLayout(new GridBagLayout());
        JMenuItem inscription, accueil, connexion;

        inscription = new JMenuItem("Inscription");
        connexion = new JMenuItem("Connexion");

        menu.add(inscription);
        menu.add(connexion);

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
                    System.out.println(fileChooserString);
                    twitub.initDirectory(fileChooserString);
                }
            }
        });
        menu.add(chooseExchangeDirMenuItem);
        JMenuItem exitMenuItem = new JMenuItem("Quitter", exitIcon);
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mFrame.dispose();
            }
        });
        menu.add(exitMenuItem);

        JMenu helpMenu = new JMenu("?");
        ImageIcon aboutIconContent = new ImageIcon("C:\\Users\\bouaksel\\OneDrive - Capgemini\\Documents\\master_tiila\\projetIhm\\IHM_TWI\\src\\main\\resources\\images\\logo_50.jpg");

        JMenuItem aboutMenuItem = new JMenuItem("À propos", aboutIcon);
        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mFrame, "M2 UBO TILL\nDépartement Informatique", "À propos", JOptionPane.INFORMATION_MESSAGE, aboutIconContent);
            }
        });
        helpMenu.add(aboutMenuItem);
        mFrame.setLayout(new GridBagLayout());
        inscription.addActionListener(createListener);
        connexion.addActionListener(connexionListener);

        ConsoleWatch consleConsoleWatch = new ConsoleWatch(consoleTextArea);
        this.mDatabase.addObserver(consleConsoleWatch);

        Action HomeMenu = new AbstractAction("Accueil") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container contentPane = TwitubMainView.this.mFrame.getContentPane();
                contentPane.removeAll();
                TwitubMainView.this.mFrame.revalidate();
                TwitubMainView.this.mFrame.repaint();
            }
        };

        // Ajout du menu à la barre de menu
        JMenuBar menubar = new JMenuBar();
        menubar.add(new JToggleButton(HomeMenu));
        menubar.add(menu);
        menubar.add(helpMenu);
        mFrame.setJMenuBar(menubar);
        mFrame.getContentPane().setBackground(new Color(255, 250, 240));


    }

    private JTextField createTextField(String name, Point p) {
        JTextField textField = new JTextField(name);
        textField.setBounds(p.x, p.y, 200, 28);
        return textField;
    }

    public void addConnexionListenre(ActionListener connexionListener) {

        this.connexionListener = connexionListener;


    }

    private JButton createButton(String name) {
        JButton btn = new JButton(name);
        btn.setBounds(20, 120, 200, 28);
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

    public JFrame getmFrame() {
        return mFrame;
    }

    public void setmFrame(JFrame jrame) {
        this.mFrame = jrame;
    }

    public void addCreateListenre(ActionListener createListener) {
        this.createListener = createListener;
    }
}
