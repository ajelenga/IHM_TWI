package main.java.com.ubo.tp.twitub.ihm.twitFolder.view;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.sql.DriverManager.println;

public class HomeView {
    private JTextArea consoleTextArea;
    private File exchangeDirectory;
    private String fileChooserString;
    private JMenu menu;
    private JMenuBar menuBar;


    public HomeView(JFrame mFrame) {
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

        menu = new JMenu("Menu");
        menuBar = new JMenuBar();

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
                    System.out.println(fileChooserString);
                }
            }
        });
        menu.add(chooseExchangeDirMenuItem);
        JMenuItem exitMenuItem = new JMenuItem("Quitter", exitIcon);
        exitMenuItem.addActionListener(e -> mFrame.dispose());
        menu.add(exitMenuItem);

        // Menu Aide
        ImageIcon aboutIconContent = new ImageIcon("C:\\Users\\bouaksel\\OneDrive - Capgemini\\Documents\\master_tiila\\projetIhm\\IHM_TWI\\src\\main\\resources\\images\\logo_50.jpg");
        JMenuItem aboutMenuItem = new JMenuItem("À propos", aboutIcon);
        aboutMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(mFrame, "M2 UBO TILL\nDépartement Informatique", "À propos", JOptionPane.INFORMATION_MESSAGE, aboutIconContent));
        JMenu helpMenu = new JMenu("?");
        helpMenu.add(aboutMenuItem);
        Action HomeMenu = new AbstractAction("Accueil") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container contentPane = mFrame.getContentPane();
                contentPane.removeAll();
                mFrame.revalidate();
                mFrame.repaint();
            }
        };

        // Ajout du menu à la barre de menu
        JMenuBar menubar = new JMenuBar();
        menubar.add(new

                JToggleButton(HomeMenu));
        menubar.add(menu);
        menubar.add(helpMenu);
    }

    public JMenu getMenu() {
        return menu;
    }

    public void setMenu(JMenu menu) {
        this.menu = menu;
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }
}
