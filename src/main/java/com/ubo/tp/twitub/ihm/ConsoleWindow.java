package main.java.com.ubo.tp.twitub.ihm;


import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ConsoleWindow extends JFrame {
    private JTextArea consoleTextArea;
    private File exchangeDirectory;

    public ConsoleWindow() {
        super("Twit UBO");

        // Charger les icônes des fichiers
        ImageIcon exitIcon = new ImageIcon("exit.png");
        ImageIcon aboutIcon = new ImageIcon("about.png");

        // Créer une zone de texte pour la console
        consoleTextArea = new JTextArea();
        consoleTextArea.setEditable(false);

        // Ajouter la zone de texte à un JScrollPane pour ajouter des barres de défilement
        JScrollPane scrollPane = new JScrollPane(consoleTextArea);

        // Ajouter le JScrollPane à la JFrame
        getContentPane().add(scrollPane);

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
                }
            }
        });
        fileMenu.add(chooseExchangeDirMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Quitter", exitIcon);
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        fileMenu.add(exitMenuItem);

        JMenu helpMenu = new JMenu("?");

        JMenuItem aboutMenuItem = new JMenuItem("À propos", aboutIcon);
        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ConsoleWindow.this, "M2 UBO TILL\nDépartement Informatique UBO", "À propos", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        helpMenu.add(aboutMenuItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        // Configurer la JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void print(String text) {
        consoleTextArea.append(text);
    }

    public void println(String text) {
        consoleTextArea.append(text + "\n");
    }
}
