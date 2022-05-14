package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \public class GrassTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba.
 */
public class GrassTile extends Tile {


    /*! \fn  public GrassTile(int id)
       \brief Constructorul cu parametri de initializare a clasei GrassTile.

       \param id Id-ul asociat tile-ului care se creeaza.
    */
    public GrassTile(int id) {
        super(Assets.grass, id);
    }


}
