package PaooGame.Entities.Creatures;

import PaooGame.Graphics.Animations;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


/*! \public class Enemy extends Creature
    \brief Implementeaza notiunea de inamic

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        animatiile
        imaginea
        momentul ultimei deplasari (miscarea inamicului fiind generata aleator)

 */

public class Enemy extends Creature{

    private Animations animUp,animDown,animRight,animLeft; /*!< Animatiile din deplasarea inamicului.*/

    long lastTurn = System.currentTimeMillis();/*!< Ultimul moment de timp la care s-a deplasat inamicul.*/

    private BufferedImage image;    /*!< Referinta catre imaginea curenta a inamicului.*/


    /*! \fn  public Enemy()
                  \brief Constructorul cu parametri de initializare e clasei Enemy

                  \param reflink Referinta catre un obiect de tip shortcut
                  \param x Pozitia inamicului pe axa X
                  \param y Pozitia inamicului pe axa Y
      */
    public Enemy(RefLinks refLink, float x, float y) {
        super(refLink, x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT,"Enemy");

        animDown=new Animations(600,Assets.catDown);
        animUp=new Animations(600,Assets.catUp);
        animLeft=new Animations(600,Assets.catLeft);
        animRight=new Animations(600,Assets.catRight);

        image = animLeft.getCurrentFrame();

        bounds.x=8;
        bounds.y=16;
        bounds.width=16;
        bounds.height=16;


        speed=6;


    }

    /// actualizarea pozitiei curente , precum si a animatiilor
    @Override
    public void Update() {

        animDown.Update();
        animLeft.Update();
        animRight.Update();
        animLeft.Update();

        GetDirection();
        Move();

    }

     /*! \fn  private void GetDirection()
                   \brief Selecteaza o directie aleatoare pentru deplasarea inamicului
       */
    private void GetDirection() {

        //xMove=0;
        //yMove=0;
        int rand=new Random().nextInt(4);

        if (System.currentTimeMillis() - lastTurn >= 1000) {
            // Se schimba directia
            if(rand == 0){
                xMove = speed;
                image = animRight.getCurrentFrame();
            }
            else if(rand == 1){
                xMove = -speed;
                image = animLeft.getCurrentFrame();
            }
            else if(rand == 2){
                yMove = speed;
                image = animDown.getCurrentFrame();
            }
            else {
                yMove = -speed;
                image = animUp.getCurrentFrame();
            }

            lastTurn = System.currentTimeMillis();
        }





    }



    /*! \fn public void Render()
            \brief Deseneaza starea curenta a inamicului pe harta.

            \param g Contextul grafic in care se realizeaza desenarea.
      */
    @Override
    public void Render(Graphics g) {

        //g.fillRect((int)bounds.x,(int)bounds.y,bounds.width,bounds.height);
        g.drawImage(image,(int)x,(int)y,width,height,null);

    }


    /// Defineste notiunea de distrugere a inamicului
    @Override
    public void die() {

    }

}
