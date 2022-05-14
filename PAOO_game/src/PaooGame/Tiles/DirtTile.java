package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \public class DirtTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip dirt("lut").
 */
public class DirtTile extends Tile {

    /*! \fn  public DirtTile(int id)
       \brief Constructorul cu parametri de initializare a clasei DirtTile.

       \param id Id-ul asociat tile-ului care se creeaza.
    */
    public DirtTile(int id) {
        super(Assets.dirt1, id);
    }


}