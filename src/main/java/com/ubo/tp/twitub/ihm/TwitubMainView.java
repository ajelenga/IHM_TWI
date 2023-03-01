package main.java.com.ubo.tp.twitub.ihm;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.ihm.twitFolder.controler.UserController;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
     * Gestionnaire de bdd et de fichier.
     */
    protected EntityManager mEntityManager;
    private JTextArea consoleTextArea;
    private File exchangeDirectory;

    /**
     * Constructeur.
     */
    public TwitubMainView(EntityManager entityManager) {
        this.mEntityManager = entityManager;
    }

    /**
     * Lance l'afficahge de l'IHM.
     *
     * @param userControler
     */
    public void showGUI(UserController userControler) {
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
        JMenu menu;
        menu = new JMenu("Menu");
        JMenuBar menuBar = new JMenuBar();

        mFrame.setJMenuBar(menuBar);
        mFrame.setSize(500, 250);
        mFrame.setLayout(new GridBagLayout());
        JMenuItem inscription, accueil, connexion;

        inscription = new JMenuItem("Inscription");
        connexion = new JMenuItem("Connexion");
        accueil = new JMenuItem("Accueil");

        menu.add(accueil);
        accueil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Container contentPane = TwitubMainView.this.mFrame.getContentPane();
                contentPane.removeAll();
                TwitubMainView.this.mFrame.revalidate();
                TwitubMainView.this.mFrame.repaint();
            }
        });
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

        // Menu Aide
        ImageIcon aboutIconContent = new ImageIcon("C:\\Users\\bouaksel\\OneDrive - Capgemini\\Documents\\master_tiila\\projetIhm\\IHM_TWI\\src\\main\\resources\\images\\logo_50.jpg");
        JMenuItem aboutMenuItem = new JMenuItem("À propos", aboutIcon);
        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mFrame, "M2 UBO TILL\nDépartement Informatique", "À propos", JOptionPane.INFORMATION_MESSAGE, aboutIconContent);
            }
        });
        JMenu helpMenu = new JMenu("?");
        helpMenu.add(aboutMenuItem);
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

        //ConsoleWatch consleConsoleWatch = new ConsoleWatch(consoleTextArea);
        //this.mDatabase.addObserver(consleConsoleWatch);

    }

}
