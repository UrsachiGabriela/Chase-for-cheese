package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class DirtTile2 extends Tile
    \brief Abstractizeaza notiunea de dala de tip dirt2("lut").
 */
public class DirtTile2 extends Tile {

    /*! \fn  public DirtTile2(int id)
       \brief Constructorul cu parametri de initializare a clasei DirtTile2.

       \param id Id-ul asociat tile-ului care se creeaza.
    */
    public DirtTile2(int id) {
        super(Assets.dirt2, id);
    }


}