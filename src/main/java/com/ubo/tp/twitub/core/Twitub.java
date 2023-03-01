package main.java.com.ubo.tp.twitub.core;

import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.events.file.IWatchableDirectory;
import main.java.com.ubo.tp.twitub.events.file.WatchableDirectory;
import main.java.com.ubo.tp.twitub.ihm.TwitubMainView;
import main.java.com.ubo.tp.twitub.ihm.TwitubMock;
import main.java.com.ubo.tp.twitub.ihm.formulaire.UserCreateControler;
import main.java.com.ubo.tp.twitub.ihm.formulaire.UserCreateView;
import main.java.com.ubo.tp.twitub.ihm.inscription.UserConnexionController;
import main.java.com.ubo.tp.twitub.ihm.inscription.UserConnexionView;
import main.java.com.ubo.tp.twitub.ihm.interf.IObserversControler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Classe principale l'application.
 *
 * @author S.Lucas
 */
public class Twitub implements IObserversControler {


    /**
     * les controller de l'appli
     */
    protected UserCreateControler userCreateControler;

    protected UserConnexionController userConnexionController;


    protected JFrame mFramePrincipale;
    /**
     * Base de données.
     */
    protected IDatabase mDatabase;

    /**
     * Gestionnaire des entités contenu de la base de données.
     */
    protected EntityManager mEntityManager;

    /**
     * Vue principale de l'application.
     */
    protected TwitubMainView mMainView;

    /**
     * Classe de surveillance de répertoire
     */
    protected IWatchableDirectory mWatchableDirectory;

    /**
     * Répertoire d'échange de l'application.
     */
    protected String mExchangeDirectoryPath;

    /**
     * Idnique si le mode bouchoné est activé.
     */
    protected boolean mIsMockEnabled = true;

    /**
     * Nom de la classe de l'UI.
     */
    protected String mUiClassName;

    /**
     * Constructeur.
     */
    public Twitub() {
        // Init du look and feel de l'application
        this.initLookAndFeel();

        // Initialisation de la base de données
        this.initDatabase();

        if (this.mIsMockEnabled) {
            // Initialisation du bouchon de travail
            this.initMock();
        }
        this.userCreateControler = new UserCreateControler(mDatabase);

        this.userConnexionController = new UserConnexionController(mDatabase);

        this.userConnexionController.addObserver(this);
        this.mMainView = new TwitubMainView(this.mDatabase, this.mEntityManager);


        this.mFramePrincipale = this.mMainView.getmFrame();
        this.mMainView.addCreateListenre(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Container contentPane = Twitub.this.mMainView.getmFrame().getContentPane();
                contentPane.removeAll();
                UserCreateView userCreateView = new UserCreateView(userCreateControler);
                //   TwitubMainView.this.mFrame = userCreateView.getJrame();

                Twitub.this.mMainView.getmFrame().add(userCreateView.getJpanel());

// Rafraîchir la frame
                Twitub.this.mMainView.getmFrame().revalidate();
                Twitub.this.mMainView.getmFrame().repaint();
                System.out.println("eeeaaaaaa");
            }
        });


        this.mMainView.addConnexionListenre(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Container contentPane = Twitub.this.mMainView.getmFrame().getContentPane();
                contentPane.removeAll();
                UserConnexionView userCreateView = new UserConnexionView(Twitub.this.mMainView.getmFrame(), Twitub.this.userConnexionController);

                Twitub.this.mMainView.getmFrame().getContentPane().add(userCreateView.getJpanel());
// Rafraîchir la frame
                Twitub.this.mMainView.getmFrame().revalidate();
                Twitub.this.mMainView.getmFrame().repaint();
                System.out.println("eee");
            }
        });


        this.mMainView.showGUI(this);

        // Initialisation de l'IHM
        this.initGui();

        // Initialisation du répertoire d'échange
        this.initDirectory();


    }


    /**
     * Initialisation du look and feel de l'application.
     */
    protected void initLookAndFeel() {
    }

    /**
     * Initialisation de l'interface graphique.
     */
    protected void initGui() {
        //  this.mMainView
    }

    /**
     * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
     * chooser). <br/>
     * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
     * pouvoir utiliser l'application</b>
     */
    private File exchangeDirectory;

    protected void initDirectory() {
        if (exchangeDirectory != null) {
            this.initDirectory(exchangeDirectory.getAbsolutePath());
        } else {
            this.initDirectory("C:\\Users\\bouaksel\\OneDrive - Capgemini\\Documents\\master_tiila\\projetIhm\\IHM_TWI\\BDD");
        }
    }


    /**
     * Indique si le fichier donné est valide pour servire de répertoire
     * d'échange
     *
     * @param directory , Répertoire à tester.
     */
    protected boolean isValideExchangeDirectory(File directory) {
        // Valide si répertoire disponible en lecture et écriture
        return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
                && directory.canWrite();
    }

    /**
     * Initialisation du mode bouchoné de l'application
     */
    protected void initMock() {
        TwitubMock mock = new TwitubMock(this.mDatabase, this.mEntityManager);
        mock.showGUI();
    }

    /**
     * Initialisation de la base de données
     */
    protected void initDatabase() {
        mDatabase = new Database();
        mEntityManager = new EntityManager(mDatabase);

    }

    /**
     * Initialisation du répertoire d'échange.
     *
     * @param directoryPath
     */
    public void initDirectory(String directoryPath) {
        mExchangeDirectoryPath = directoryPath;
        mWatchableDirectory = new WatchableDirectory(directoryPath);
        mEntityManager.setExchangeDirectory(directoryPath);

        mWatchableDirectory.initWatching();
        mWatchableDirectory.addObserver(mEntityManager);
    }

    public void show() {
        // ... setVisible?
    }

    @Override
    public void update(JPanel jpanel) {
        Container contentPane = Twitub.this.mMainView.getmFrame().getContentPane();
        contentPane.removeAll();
        System.out.println(jpanel);

        Twitub.this.mMainView.getmFrame().add(jpanel);

// Rafraîchir la frame
        Twitub.this.mMainView.getmFrame().revalidate();
        Twitub.this.mMainView.getmFrame().repaint();

    }
}
