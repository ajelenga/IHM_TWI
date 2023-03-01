package main.java.com.ubo.tp.twitub.core;

import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.events.file.IWatchableDirectory;
import main.java.com.ubo.tp.twitub.events.file.WatchableDirectory;
import main.java.com.ubo.tp.twitub.ihm.TwitubMainView;
import main.java.com.ubo.tp.twitub.ihm.TwitubMock;
import main.java.com.ubo.tp.twitub.ihm.twitFolder.controler.UserController;

import java.io.File;

/**
 * Classe principale l'application.
 *
 * @author S.Lucas
 */
public class Twitub {
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
    protected boolean mIsMockEnabled = false;

    /**
     * Nom de la classe de l'UI.
     */
    protected String mUiClassName;

    /**
     * Controlleur login.
     */
    protected UserController mUserControler;

    /**
     * Constructeur.
     */
    public Twitub() {
        // Init du look and feel de l'application
        this.initLookAndFeel();

        // Initialisation de la base de données
        this.initDatabase();

        // Initialisation du controlleur
        this.initControler();

        if (this.mIsMockEnabled) {
            // Initialisation du bouchon de travail
            this.initMock();
        }

        this.mMainView = new TwitubMainView(this.mEntityManager);
        this.mMainView.showGUI(this.mUserControler);


        // Initialisation de l'IHM
        this.initGui();

        // Initialisation du répertoire d'échange
        this.initDirectory();

    }

    //initialisation du controlleur
    public void initControler() {
        this.mUserControler = new UserController(this.mDatabase);
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
        // this.mMainView...
    }

    /**
     * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
     * chooser). <br/>
     * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
     * pouvoir utiliser l'application</b>
     */
    protected void initDirectory() {
        this.initDirectory("C:\\Users\\bouaksel\\OneDrive - Capgemini\\Documents\\master_tiila\\projetIhm\\IHM_TWI\\BDD");
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
}
