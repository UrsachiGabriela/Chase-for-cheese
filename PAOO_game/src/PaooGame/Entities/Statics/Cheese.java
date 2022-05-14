package PaooGame.Entities.Statics;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Item;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

/*! \class Cheese extends StaticEntity
    \brief Implementeaza notiunea de item ce trebuie colectat de catre erou
 */

public class Cheese extends StaticEntity{

    /*! \fn public Cheese(RefLinks refLink, float x, float y)
           \brief Constructorul cu parametri al clasei Cheese

            \param reflink Referinta catre un obiect "shortcut"
            \param x Pozitia pe axa X a entitatii statice
            \param y Pozitia pe axa Y a entitatii statice
*/
    public Cheese(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT,"Cheese");

        bounds.x=0;
        bounds.y=0;
        bounds.width=width/2;
        bounds.height=height/3;
    }


    /*! \fn public Update()
       \brief Actualizarea starii curente
*/
    @Override
    public void Update() {}


    /*! \fn public Update()
   \brief Desenarea starii curente
*/
    @Override
    public void Render(Graphics g) {
        g.drawImage(Assets.cheese,(int)x,(int)y,width,height,null);
    }


    /*! \fn public void die()
   \brief Defineste notiunea de distrugere a entitatii pt obiecte de tip cascaval(itemi ce trebuie colectati)
*/
    @Override
    public void die()
    {
        /// va fi creat un item cascaval care la randul lui va disparea de pe harta
        refLink.GetWorld().getItemManager().addItem(Item.cItem.createNew((int)x,(int)y));
    }
}
