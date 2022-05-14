package PaooGame.Tiles;

import PaooGame.Graphics.Assets;


/*! \public class Stone2Tile extends Tile
    \brief Abstractizeaza notiunea de dala de tip zid2.
 */
public class Stone2Tile extends Tile{


    /*! \fn  public Stone2Tile(int id)
           \brief Constructorul cu parametri de initializare a clasei Stone2Tile.

           \param id Id-ul asociat tile-ului care se creeaza.
    */
    public Stone2Tile( int id) {
        super(Assets.stone2, id);
    }

    /*! \fn  public boolean isSolid()
            \brief Creeaza proprietatea de solid tile pentru tile-ul de tip piatra.
    */
    @Override
    public boolean isSolid() {
        return true;
    }
}