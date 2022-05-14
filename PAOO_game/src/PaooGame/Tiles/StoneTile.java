package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \public class StoneTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip zid.
 */
public class StoneTile extends Tile{

    /*! \fn  public StoneTile(int id)
           \brief Constructorul cu parametri de initializare a clasei StoneTile.

           \param id Id-ul asociat tile-ului care se creeaza.
        */
    public StoneTile( int id) {
        super(Assets.stone, id);
    }

    /*! \fn  public boolean isSolid()
           \brief Creeaza proprietatea de solid tile pentru tile-ul de tip piatra.
   */
    @Override
    public boolean isSolid() {
        return true;
    }
}
