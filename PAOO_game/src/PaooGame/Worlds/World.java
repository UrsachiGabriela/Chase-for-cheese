package PaooGame.Worlds;

import PaooGame.Entities.Creatures.Enemy;
import PaooGame.Entities.Creatures.Hero;
import PaooGame.Entities.EntityManager;
import PaooGame.Entities.Statics.Cheese;
import PaooGame.Entities.Statics.Door;
import PaooGame.Entities.Statics.Trap;
import PaooGame.Exceptions.NullContentException;
import PaooGame.Items.ItemManager;
import PaooGame.Level.Level;
import PaooGame.RefLinks;
import PaooGame.Tiles.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


/*! \public class World
    \brief Implementeaza notiunea de harta a jocului.
 */
public class World {

    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    private int [][] tiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/

    private final int spawnX=0;/*!< Pozitia de start a jucatorului pe axa X*/
    private final int spawnY=30;    /*!< Pozitia de start a jucatorului pe harta Y*/


    private Tile solid;         /*!< Zidurile labirintului */
    private Tile nonSolid;      /*!< Zona "libera" a labirintului */
    TileFactory tileFactory;    /*!< Referinta catre un factory ce va construi tipurile de dale*/


    /// Entities
    private EntityManager entityManager; /*!< Referinta catre un manager de entitati*/

    /// Items
    private ItemManager itemManager; /*!< Referinta catre un manager de itemi*/



    /*! \fn public World(RefLinks refLink)
          \brief Constructorul de initializare al clasei.

          \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
          \param img Imaginea din care se incarca harta.
       */
    public World(RefLinks refLink, BufferedImage img){
        this.refLink = refLink;

        try
        {
            loadWorld(img);
        }
        catch (Exception m)
        {
            ///in cazul in care imaginea este nula (fisierul nu are continut),  se initializeaza harta static
            width=33;
            height=17;
            tiles=new int[][]{
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1},
            {1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1}};

            System.out.println("Exception occured: "+m);

        }



        entityManager=new EntityManager(refLink,new Hero(refLink,100,100));
        entityManager.getHero().setX(spawnX);
        entityManager.getHero().setY(spawnY);


        itemManager=new ItemManager(refLink);

        switch (Level.getInstance().getLevelNr()) {
            case 1 -> {
                tileFactory = new Factory1();
                solid = tileFactory.createSolid();
                nonSolid = tileFactory.createNonSolid();

            }
            case 2 -> {
                tileFactory = new Factory2();
                solid = tileFactory.createSolid();
                nonSolid = tileFactory.createNonSolid();
                addTraps();
            }
            case 3 -> {
                tileFactory = new Factory3();
                solid = tileFactory.createSolid();
                nonSolid = tileFactory.createNonSolid();
                addTraps();
                entityManager.addEntity(new Enemy(refLink, 300, 300));
            }
            default -> {
                System.out.println("Ai castigat!");

            }
        }

        addCheese();
        entityManager.addEntity(new Door(refLink,32*Tile.TILE_WIDTH,15*Tile.TILE_HEIGHT));

    }



    /*! \fn public void addCheese()
          \brief Se adauga random itemi de tip cascaval pe harta.

       */
    public void addCheese()
    {
        /// adaugare bucati de cascaval (nr lor este in functie de nivelul la care s-a ajuns)
        Random random = new Random();
        for(int i = 0; i< Level.getInstance().getLevelNr()*4; i++)
        {
            int w=random.nextInt(width);
            int h=random.nextInt(height);
            Tile t=GetTile(w,h);

            /// cascavalul va fi plasat doar pe zona in care eroul se poate deplasa
            if(!t.isSolid())
            {
                entityManager.addEntity(new Cheese(refLink, w*32, h*32));
            }
            else {
                i--;
            }
        }
    }

    /*! \fn public void addTraps()
         \brief Se adauga random itemi de tip capcana pe harta.

      */
    public void addTraps()
    {
        /// adaugare capcane (nr lor este in functie de nivelul la care s-a ajuns)
        Random random = new Random();
        for(int i = 0; i< Level.getInstance().getLevelNr()*2-1; i++)
        {
            int w=random.nextInt(width);
            int h=random.nextInt(height);
            Tile t=GetTile(w,h);

            /// capcanele vor fi plasate doar pe zona in care eroul se poate deplasa
            if(!t.isSolid())
            {
                entityManager.addEntity(new Trap(refLink, w*32, h*32));
            }
            else {
                i--;
            }
        }
    }

    /*! \fn public  void Update()
            \brief Actualizarea hartii in functie de evenimente (un obiect a fost colectat)
         */
    public void Update(){
        itemManager.Update();
        entityManager.Update();
        refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();
    }


    /*! \fn public void Render(Graphics g)
           \brief Functia de desenare a hartii.

           \param g Contextl grafic in care se realizeaza desenarea.
        */
    public void Render(Graphics g){

        /// randare tiles
        for(int y=0;y<height;y++)
            for(int x=0;x<width;x++)
            {
                GetTile(x,y).Render(g,x*Tile.TILE_WIDTH,y*Tile.TILE_HEIGHT);
            }

        /// randare items
        itemManager.Render(g);

        ///randare entitati
        entityManager.Render(g);



    }

    /*! \fn public Tile GetTile(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

        In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
        intoarce o dala predefinita (ex. grassTile, mountainTile)
     */
    public Tile GetTile(int x, int y){
        if(x<0 || y<0 || x>=width || y>=height)
            return nonSolid;

        Tile t=Tile.tiles[tiles[x][y]];

        if(t==null)
            return solid;
        return t;
    }



      /*! \fn private void LoadWorld()
        \brief Functie de incarcare a hartii jocului.

        \param img Imaginea ce genereaza tile-urile de pe harta.
     */
    private void loadWorld(BufferedImage img) throws NullContentException
    {
        ///incarcare harta din fisier.png
        if(img ==null)
        {
            throw new NullContentException("Null content");
        }
        else
        {
            width=img.getWidth();
            height=img.getHeight();

            tiles= new int[width][height];

            for(int xx=0;xx<width;xx++)
                for(int yy=0;yy<height;yy++)
                {
                    Color mycolor = new Color(img.getRGB(xx, yy));
                    int red = mycolor.getRed();
                    int green = mycolor.getGreen();
                    int blue = mycolor.getBlue();


                    // pt culoarea alba -> nonSolid Tile
                    if(red==255 && green==255 && blue==255)
                        tiles[xx][yy]=0;

                    // pt culoarea neagra -> Solid Tile
                    if(red==0 && green==0 && blue==0)
                        tiles[xx][yy]=1;
                }
        }


    }


    /// Getters & Setters pt atribute.
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public RefLinks getRefLink() {
        return refLink;
    }

    public void setRefLink(RefLinks refLink) {
        this.refLink = refLink;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }


}
