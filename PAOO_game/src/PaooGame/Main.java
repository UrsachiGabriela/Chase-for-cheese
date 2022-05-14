package PaooGame;

import PaooGame.SQLite.SQL;

public class Main {

    public static void main(String[] args)
    {

        SQL bd= SQL.getInstance();
        Game game=new Game("Chase for cheese",1056,544);


        //game.start() va apela game.run() iar game.run() va apela game.init()
        game.Start();







    }
}
