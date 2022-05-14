package PaooGame.Entities.Creatures;

import PaooGame.Entities.Entity;
import PaooGame.Graphics.Animations;
import PaooGame.Graphics.Assets;
import PaooGame.Inventory.Inventory;
import PaooGame.Level.Level;
import PaooGame.RefLinks;
import PaooGame.SQLite.SQL;
import PaooGame.States.PlayState;
import PaooGame.States.State;

import java.awt.*;
import java.awt.image.BufferedImage;


/*! \public class Hero extends Creature
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea
        inventory
        animatiile
 */

public class Hero extends Creature{


    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private Inventory inventory;    /*!< Referinta catre inventory-ul in care sunt depuse elementele colectate.*/


    private Animations aDown,aUp,aRight,aLeft;  /*!< Animatiile prezente la deplasarea eroului.*/


    /*! \fn public Hero(RefLinks refLink, float x, float y)
               \brief Constructorul cu parametri al clasei Creature

                \param reflink Referinta catre un obiect "shortcut"
                \param x Pozitia pe axa X a imaginii eroului
                \param y Pozitia pe axa Y a imaginii eroului
   */
    public Hero(RefLinks refLink, float x, float y) {
        super(refLink, x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT,"Hero");



        bounds.x=8;
        bounds.y=16;
        bounds.width=16;
        bounds.height=16;

        health=3;

        aUp=new Animations(600,Assets.heroUp);
        aDown=new Animations(600,Assets.heroDown);
        aRight=new Animations(600,Assets.heroRight);
        aLeft=new Animations(600,Assets.heroLeft);


        image = aRight.getCurrentFrame();

        inventory=new Inventory(refLink);
    }


    /// actualizarea pozitiei curente , precum si a animatiilor
    @Override
    public void Update() {
        GetInput();
        Move();

        aDown.Update();
        aLeft.Update();
        aUp.Update();
        aRight.Update();

        if(refLink.GetKeyManager().left)
        {
            image = aLeft.getCurrentFrame();
        }
        if(refLink.GetKeyManager().right) {
            image = aRight.getCurrentFrame();
        }
        if(refLink.GetKeyManager().up) {
            image = aUp.getCurrentFrame();
        }
        if(refLink.GetKeyManager().down) {
            image = aDown.getCurrentFrame();
        }

        checkAttacks();

        inventory.Update();
    }



    /*! \fn  private void checkAttacks()
                   \brief Verifica coliziunile eroului cu entitatile si stabileste
                           ce trebuie modificat pe harta atunci cand eroul intalneste anumite entitati
       */
    private void checkAttacks(){
        /// cb -> dreptunghiul de coliziune al eroului
        Rectangle cb=getCollisionBounds(0,0);

        Rectangle ar=new Rectangle();
        int arSize=20;

        ar.width=arSize;
        ar.height=arSize;

        if(refLink.GetKeyManager().up){
            ar.x=cb.x+cb.width/2-arSize/2;
            ar.y=cb.y-arSize;
        }
        else if(refLink.GetKeyManager().down){
            ar.x=cb.x+cb.width/2-arSize/2;
            ar.y=cb.y+cb.height;
        }
        else if(refLink.GetKeyManager().left){
            ar.x=cb.x-arSize;
            ar.y=cb.y+cb.height/2-arSize/2;
        }
        else if(refLink.GetKeyManager().right){
            ar.x=cb.x+cb.width;
            ar.y=cb.y+cb.height/2-arSize/2;
        }
        else{
            return;
        }


        for(Entity e:refLink.GetWorld().getEntityManager().getEntities()){

            if(e.equals(this))
                continue;

            if(e.getName().equals("Door") && e.getCollisionBounds(0,0).intersects(ar) && inventory.getInventoryItems().get(0).getCount()== Level.getInstance().getLevelNr()*4){
                if(PlayState.countdown> SQL.getInstance().getHighScore(Level.getInstance().getLevelNr()))
                {
                    refLink.GetGame().getPlayState().setNotify(true);
                    PlayState.timer2.SetDelay(5000);

                    System.out.println("Old record: "+SQL.getInstance().getHighScore(Level.getInstance().getLevelNr()));
                    System.out.println("New score record at level: "+Level.getInstance().getLevelNr()+" : "+PlayState.countdown);
                }
                Level.getInstance().setChangeLevel(true);
            }


            if(e.getName().equals("Trap") && e.getCollisionBounds(0,0).intersects(ar)){
                PlayState.countdown--;
            }

            if(e.getName().equals("Enemy") && e.getCollisionBounds(0,0).intersects(ar)){
                die();
            }


            if(e.getName().equals("Cheese") && e.getCollisionBounds(0,0).intersects(ar)){
                PlayState.countdown+=2;
                e.hurt(1);
                return;
            }
        }



    }


    /*! \fn  public void die()
                  \brief Defineste notiunea de "moarte"/distrugere a eroului
      */
    @Override
    public void die()
    {
        refLink.GetMouseManager().setUIManager(refLink.GetGame().getGameOverState().getUiManager());
        State.SetState(refLink.GetGame().getGameOverState());
        refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();


        Level.getInstance().setLevel(0);
        Level.getInstance().setChangeLevel(true);

    }


    /*! \fn  private void GetInput()
                      \brief Asteapta input de la tastatura pentru a actualiza pozitia eroului pe harta
          */
    private void GetInput() {
        xMove=0;
        yMove=0;

        if(refLink.GetKeyManager().up)
            yMove=-speed;
        if(refLink.GetKeyManager().down)
            yMove+=speed;
        if(refLink.GetKeyManager().left)
            xMove-=speed;
        if(refLink.GetKeyManager().right)
            xMove+=speed;

    }


    /*! \fn public void Render()
                      \brief Deseneaza starea curenta a eroului pe harta.

                      \param g Contextul grafic in care se realizeaza desenarea.
    */
    @Override
    public void Render(Graphics g) {
        g.drawImage(image,(int)x,(int)y,width,height,null);
        inventory.Render(g);
    }


    /// Getters & Setters pentru atribute
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
