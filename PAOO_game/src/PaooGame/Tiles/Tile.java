package PaooGame.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile {


    public static Tile[] tiles=new Tile[2];             /*!< Vector de referinte de tipuri de dale.*/



    //CLASS
    public static final int TILE_WIDTH=32;              /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT=32;             /*!< Inaltimea unei dale.*/
    protected BufferedImage texture;                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                             /*!< Id-ul unic aferent tipului de dala.*/


    /*! \fn public Tile(BufferedImage texture,int id)
        \brief Constructorul de initializare al clasei.

        \param texture Imaginea tile-ului.
        \param id Id-ul unic al tile-ului.
     */
    public Tile(BufferedImage texture,int id){
        this.texture=texture;
        this.id=id;

        tiles[id]=this;
    }


    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a tile-ului.
     */
    public void Update(){}


    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a tile-ului.

        \param g Contextul grafic in care trebuie sa deseneze .
     */
    public void Render(Graphics g, int x, int y){
        g.drawImage(texture,x,y,TILE_WIDTH,TILE_HEIGHT,null);
    }


    /*! \fn public boolean isSolid()
        \brief Stabileste daca tile-ul este solid(zid) sau nu.
     */
    public boolean isSolid(){
        return false;
    }

    /*! \fn public int getId()
        \brief Returneaza id-ul tile-ului.
     */
    public int getId(){
        return id;
    }
}
