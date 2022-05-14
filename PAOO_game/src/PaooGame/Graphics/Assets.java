package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini,harti etc.
 */

public class Assets {

    /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage[] heroLeft;
    public static BufferedImage[] heroRight;
    public static BufferedImage[] heroUp;
    public static BufferedImage[] heroDown;

    public static BufferedImage grass;
    public static BufferedImage stone;
    public static BufferedImage cheese;

    public static BufferedImage[] catLeft;
    public static BufferedImage[] catRight;
    public static BufferedImage[] catUp;
    public static BufferedImage[] catDown;


    public static BufferedImage mouseTrap;
    public static BufferedImage door;
    public static BufferedImage dirt1;
    public static BufferedImage dirt2;
    public static BufferedImage stone2;


    public static BufferedImage[] map;



    public static BufferedImage[] pause_btn;
    public static BufferedImage[] help_btn;
    public static BufferedImage[] quit_btn;
    public static BufferedImage[] reload;
    public static BufferedImage[] resume;
    public static BufferedImage[] back_btn;
    public static BufferedImage[] loadGame_btn;
    public static BufferedImage[] newGame_btn;
    public static BufferedImage[] settings_btn;
    public static BufferedImage[] musicOn_btn;
    public static BufferedImage[] musicOff_btn;

    public static BufferedImage timer;


    public static BufferedImage menuBackground;
    public static BufferedImage cheeseFrame;
    public static BufferedImage mouseImage;

    public static BufferedImage ar_UP;
    public static BufferedImage ar_DOWN;
    public static BufferedImage ar_RIGHT;
    public static BufferedImage ar_LEFT;



        /*! \fn public static void Init()
           \brief Functia initializaza referintele catre elementele grafice utilizate.

        */
    public static void Init()
    {
        SpriteSheet sheet=new SpriteSheet(ImageLoader.LoadImage("/textures/sprite.png"));
        /// Se obtin subimaginile corespunzatoare elementelor necesare.

        heroUp=new BufferedImage[3];
        heroDown=new BufferedImage[3];
        heroRight=new BufferedImage[3];
        heroLeft=new BufferedImage[3];


        heroLeft[0] = sheet.Crop(0, 11,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        heroLeft[1] = sheet.Crop(1, 11,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        heroLeft[2] = sheet.Crop(2, 11,SpriteSheet.tileWidth,SpriteSheet.tileHeight);


        heroRight[0] = sheet.Crop(0, 9,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        heroRight[1] = sheet.Crop(1, 9,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        heroRight[2] = sheet.Crop(2, 9,SpriteSheet.tileWidth,SpriteSheet.tileHeight);

        heroUp[0] = sheet.Crop(0, 8,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        heroUp[1] = sheet.Crop(1, 8,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        heroUp[2] = sheet.Crop(2, 8,SpriteSheet.tileWidth,SpriteSheet.tileHeight);

        heroDown[0] = sheet.Crop(0, 10,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        heroDown[1] = sheet.Crop(1, 10,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        heroDown[2] = sheet.Crop(2, 10,SpriteSheet.tileWidth,SpriteSheet.tileHeight);




        grass = sheet.Crop(15, 11,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        stone = sheet.Crop(15, 9,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        cheese=sheet.Crop(7,15,SpriteSheet.tileWidth,SpriteSheet.tileHeight);



        catUp=new BufferedImage[3];
        catDown=new BufferedImage[3];
        catRight=new BufferedImage[3];
        catLeft=new BufferedImage[3];

        catRight[0]=sheet.Crop(12,0,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        catRight[1]=sheet.Crop(13,0,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        catRight[2]=sheet.Crop(14,0,SpriteSheet.tileWidth,SpriteSheet.tileHeight);



        catUp[0]=sheet.Crop(12,1,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        catUp[1]=sheet.Crop(13,1,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        catUp[2]=sheet.Crop(14,1,SpriteSheet.tileWidth,SpriteSheet.tileHeight);

        catDown[0]=sheet.Crop(12,2,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        catDown[1]=sheet.Crop(13,2,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        catDown[2]=sheet.Crop(14,2,SpriteSheet.tileWidth,SpriteSheet.tileHeight);

        catLeft[0]=sheet.Crop(12,3,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        catLeft[1]=sheet.Crop(13,3,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        catLeft[2]=sheet.Crop(14,3,SpriteSheet.tileWidth,SpriteSheet.tileHeight);




        mouseTrap=sheet.Crop(2,15,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        door=sheet.Crop(14,14,SpriteSheet.tileWidth,SpriteSheet.tileHeight);

        dirt1 = sheet.Crop(4, 15,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        stone2 = sheet.Crop(5, 15,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        dirt2=sheet.Crop(6,15,SpriteSheet.tileWidth,SpriteSheet.tileHeight);



        map=new BufferedImage[4];

        map[0]=ImageLoader.LoadImage("/textures/maze4.png");
        map[1]=ImageLoader.LoadImage("/textures/maze1.png");
        map[2]=ImageLoader.LoadImage("/textures/maze2.png");
        map[3]=ImageLoader.LoadImage("/textures/maze3.png");


        menuBackground=ImageLoader.LoadImage("/textures/bgg.png");





        pause_btn=new BufferedImage[2];
        help_btn=new BufferedImage[2];
        quit_btn=new BufferedImage[2];
        back_btn=new BufferedImage[2];
        reload=new BufferedImage[2];
        loadGame_btn=new BufferedImage[2];
        newGame_btn=new BufferedImage[2];
        settings_btn=new BufferedImage[2];
        musicOn_btn=new BufferedImage[2];
        musicOff_btn=new BufferedImage[2];


        pause_btn[0]=sheet.Crop(0,14,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        pause_btn[1]=sheet.Crop(4,13,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        help_btn[0]=sheet.Crop(12,8,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        help_btn[1]=sheet.Crop(8,12,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        quit_btn[0]= sheet.Crop(4,14,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        quit_btn[1]= sheet.Crop(8,13,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        back_btn[0]= sheet.Crop(8,8,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        back_btn[1]= sheet.Crop(4,12,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        newGame_btn[0]= sheet.Crop(0,12,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        newGame_btn[1]= sheet.Crop(0,13,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        loadGame_btn[0]= sheet.Crop(11,9,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        loadGame_btn[1]= sheet.Crop(12,12,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        settings_btn[0]=sheet.Crop(8,14,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        settings_btn[1]=sheet.Crop(12,13,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        musicOn_btn[0]= sheet.Crop(7,11,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        musicOn_btn[1]= sheet.Crop(11,11,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        musicOff_btn[0]=sheet.Crop(11,10,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        musicOff_btn[1]=sheet.Crop(3,11,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);


        reload[0]=sheet.Crop(4,8,SpriteSheet.tileWidth-2,SpriteSheet.tileHeight-2);
        reload[1]=sheet.Crop(4,8,SpriteSheet.tileWidth-2,SpriteSheet.tileHeight-2);


        resume=new BufferedImage[2];
        resume[0]=sheet.Crop(6,8,SpriteSheet.tileWidth,SpriteSheet.tileHeight);
        resume[1]=sheet.Crop(6,8,SpriteSheet.tileWidth,SpriteSheet.tileHeight);

        timer=sheet.Crop(1,15,SpriteSheet.tileWidth,SpriteSheet.tileHeight);


        ar_UP=sheet.Crop(9,9,SpriteSheet.tileWidth*2,SpriteSheet.tileHeight*2);
        ar_DOWN=sheet.Crop(3,9,SpriteSheet.tileWidth*2,SpriteSheet.tileHeight*2);
        ar_LEFT=sheet.Crop(5,9,SpriteSheet.tileWidth*2,SpriteSheet.tileHeight*2);
        ar_RIGHT=sheet.Crop(7,9,SpriteSheet.tileWidth*2,SpriteSheet.tileHeight*2);


        mouseImage=ImageLoader.LoadImage("/textures/mouse.png");
        cheeseFrame=ImageLoader.LoadImage("/textures/frame.png");

    }
}
