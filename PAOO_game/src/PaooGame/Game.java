package PaooGame;

import PaooGame.Display.Display;
import PaooGame.Graphics.Assets;
import PaooGame.Input.KeyManager;
import PaooGame.Input.MouseManager;
import PaooGame.SQLite.SQL;
import PaooGame.States.*;

import java.awt.*;
import java.awt.image.BufferStrategy;


/*! \class Game
    \brief Clasa principala a intregului proiect. Implementeaza Game - Loop (Update -> Draw)

                ------------
                |           |
                |     ------------
    60 times/s  |     |  Update  |  -->{ actualizeaza variabile, stari, pozitii ale elementelor grafice etc.
        =       |     ------------
     16.7 ms    |           |
                |     ------------
                |     |   Draw   |  -->{ deseneaza totul pe ecran
                |     ------------
                |           |
                -------------
    Implementeaza interfata Runnable:

        public interface Runnable {
            public void run();
        }

    Interfata este utilizata pentru a crea un nou fir de executie avand ca argument clasa Game.
    Clasa Game trebuie sa aiba definita metoda "public void run()", metoda ce va fi apelata
    in noul thread(fir de executie). Mai multe explicatii veti primi la curs.

    In mod obisnuit aceasta clasa trebuie sa contina urmatoarele:
        - public Game();            //constructor
        - private void init();      //metoda privata de initializare
        - private void update();    //metoda privata de actualizare a elementelor jocului
        - private void draw();      //metoda privata de desenare a tablei de joc
        - public run();             //metoda publica ce va fi apelata de noul fir de executie
        - public synchronized void start(); //metoda publica de pornire a jocului
        - public synchronized void stop()   //metoda publica de oprire a jocului
 */

public class Game implements Runnable {


    private Display display;/*!< Fereastra in care se va desena tabla jocului*/


    private boolean running=false; /*!< Flag ce starea firului de executie.*/
    private Thread thread; /*!< Referinta catre thread-ul de update si draw al ferestrei*/

    private BufferStrategy bs; /*!< Referinta catre un mecanism cu care se organizeaza memoria complexa pentru un canvas.*/
    /// Sunt cateva tipuri de "complex buffer strategies", scopul fiind acela de a elimina fenomenul de
    /// flickering (palpaire) a ferestrei.
    /// Modul in care va fi implementata aceasta strategie in cadrul proiectului curent va fi triplu buffer-at

    ///                         |------------------------------------------------>|
    ///                         |                                                 |
    ///                 ****************          *****************        ***************
    ///                 *              *   Show   *               *        *             *
    /// [ Ecran ] <---- * Front Buffer *  <------ * Middle Buffer * <----- * Back Buffer * <---- Draw()
    ///                 *              *          *               *        *             *
    ///                 ****************          *****************        ***************



    public int width;  /*!< Latimea ferestrei jocului.*/
    public int height; /*!< Inaltimea ferestrei jocului.*/
    public String title; /*!< Titlul ferestrei jocului.*/

    private Graphics g;  /*!< Referinta catre un context grafic.*/


    //States
    private State gameOverState;
    private State playState; /*!< Referinta catre joc.*/
    private State menuState; /*!< Referinta catre meniu.*/
    private State helpState; /*!< Referinta catre help.*/
    private State pauseState;/*!< Referinta catre pause.*/
    private State settingsState;/*!< Referinta catre settings.*/







    //RefLinks
    private RefLinks refLink; /*!< Referinta catre un obiect a carui sarcina este doar de a retine diverse referinte pentru a fi usor accesibile.*/



    //Input
    private KeyManager keyManager;  /*!< Referinta catre obiectul care gestioneaza intrarile de la tastatura din partea utilizatorului.*/
    private MouseManager mouseManager;/*!< Referinta catre obiectul care gestioneaza intrarile de la mouse din partea utilizatorului.*/


    /*! \fn public Game(String title, int width, int height)
            \brief Constructor de initializare al clasei Game.

            Acest constructor primeste ca parametri titlul ferestrei, latimea si inaltimea
            acesteia avand in vedere ca fereastra va fi construita/creata in cadrul clasei Game.

            \param title Titlul ferestrei.
            \param width Latimea ferestrei in pixeli.
            \param height Inaltimea ferestrei in pixeli.
         */
    public Game(String title,int width,int height)
    {
        this.width=width;
        this.height=height;
        this.title=title;
        keyManager=new KeyManager();
        mouseManager=new MouseManager();

    }




    /*! \fn private void init()
       \brief  Metoda construieste fereastra jocului, initializeaza aseturile, listenerul de tastatura etc.

       Fereastra jocului va fi construita prin apelul functiei BuildGameWindow();
       Sunt construite elementele grafice (assets): dale, player, elemente active si pasive.

    */
    private void Init()
    {
        display=new Display(title,width,height);
        display.GetFrame().addKeyListener(keyManager);
        display.GetFrame().addMouseListener(mouseManager);
        display.GetFrame().addMouseMotionListener(mouseManager);
        display.GetCanvas().addMouseListener(mouseManager);
        display.GetCanvas().addMouseMotionListener(mouseManager);

        /// pt a functiona atat input-ul de la mouse, cat si cel de la tastatura
        display.GetFrame().requestFocusInWindow();



        Assets.Init();
        refLink = new RefLinks(this);

        playState=new PlayState(refLink);
        menuState=new MenuState(refLink);
        helpState=new HelpState(refLink);
        pauseState=new PauseState(refLink);
        gameOverState=new GameOverState(refLink);
        settingsState=new SettingsState(refLink);



        refLink.GetMouseManager().setUIManager(menuState.getUiManager());

        State.SetState(menuState);



    }



    /*! \fn private void Update()
           \brief Actualizeaza starea elementelor din joc.

           Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
        */
    private void Update(){

        keyManager.Update();
        if(State.GetState()!=null)
           State.GetState().Update();
    }

    /*! \fn private void Render()
       \brief Deseneaza elementele grafice in fereastra corespunzator starilor actualizate ale elementelor.

       Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
    */
    private void Render(){
        bs=display.GetCanvas().getBufferStrategy();

        if(bs==null)
        {
            try
            {
                /// Se construieste triplul buffer
                display.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                /// Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }


        g= bs.getDrawGraphics();

        //Clear the screen
        g.clearRect(0,0,width,height);

        ///Draw Here!
        if(State.GetState()!=null)
            State.GetState().Render(g);
        ///End Drawing!



        /// Se afiseaza pe ecran
        bs.show();

        /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
        /// elementele grafice ce au fost desenate pe canvas).
        g.dispose();

    }

    /*! \fn public void run()
            \brief Functia ce va rula in thread-ul creat.

            Aceasta functie va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)
         */
    @Override
    public void run()
    {
        /// Initializeaza obiectul game
        Init();
        long oldTime = System.nanoTime();   /*!< Retine timpul in nanosecunde aferent frame-ului anterior.*/
        long curentTime;                    /*!< Retine timpul curent de executie.*/

        /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
        /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond   = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final double timeFrame      = 1000000000 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/

        /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (running )
        {
            /// Se obtine timpul curent
            curentTime = System.nanoTime();
            /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if((curentTime - oldTime) > timeFrame)
            {

                /// Actualizeaza pozitiile elementelor
                Update();
                /// Deseneaza elementele grafica in fereastra.
                Render();

                oldTime = curentTime;
            }
        }

    }



    /*! \fn public synchronized void Start()
            \brief Creaza si starteaza firul separat de executie (thread).

            Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
         */
    public synchronized void Start(){
        //verific daca jocul deja a inceput
        if(running)
            return;

        running=true;
        thread=new Thread(this);
        thread.start(); //apeleaza metoda run
    }

    /*! \fn public synchronized void Stop()
        \brief Opreste executie thread-ului.

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void Stop(){
        if(!running)
            return;
        running=false;
        try {
            thread.join();
            SQL.getInstance().closeConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /// Getters & Setters pt atribute
    public KeyManager GetKeyManager()
    {
        return keyManager;
    }

    public MouseManager GetMouseManager()
    {
        return mouseManager;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public State getPlayState() {
        return playState;
    }

    public State getMenuState() {
        return menuState;
    }

    public State getHelpState(){return helpState;}

    public State getPauseState(){return pauseState;}

    public State getGameOverState(){return gameOverState;}

    public State getSettingsState(){return settingsState;}

    public Display getDisplay() {
        return display;
    }

}
