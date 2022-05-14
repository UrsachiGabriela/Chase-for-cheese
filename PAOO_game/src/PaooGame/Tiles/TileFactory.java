package PaooGame.Tiles;

/*! \public abstract class TileFactory
    \brief Abstractizeaza notiunea de fabrica de dale.

    Se utilizeaza AbstractFactory Pattern
 */

public abstract class TileFactory {

    /// metoda abstracta pentru crearea unui solid tile
    public abstract Tile createSolid();
    /// metoda abstracta pentru crearea unui non-solid tile
    public abstract Tile createNonSolid();
}






