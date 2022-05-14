package PaooGame.Tiles;

/*! \public class Factory1 extends TileFactory
    \brief Implementeaza notiunea de fabrica de dale pentru nivelul 1 al jocului

 */


public class Factory1 extends TileFactory{

    /*! \fn  public Tile createSolid()
           \brief Creeaza si returneaza tile-ul solid al hartii.
   */
    @Override
    public Tile createSolid() {
        return new StoneTile(1);
    }



    /*! \fn  public Tile createNonSolid()
           \brief Creeaza si returneaza tile-ul non-solid al hartii.
   */
    @Override
    public Tile createNonSolid() {
        return new GrassTile(0);
    }
}
