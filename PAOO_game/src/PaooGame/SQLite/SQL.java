package PaooGame.SQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/*! \public class SQL
    \brief Gestioneaza baza de date

    Aceasta clasa utilizeaza Singleton Pattern.
 */

public class SQL {

    private static SQL instance = null;
    private static Connection c = null;
    private static Statement stmt = null;

    /*! \fn  private SQL()
        \brief Constructorul privat al clasei SQL ; creeaza conexiunea cu baza de date
                si creeaza tabelele pentru scoruri , respectiv pentru setari
    */
    private SQL() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:javaGame.db");
            c.setAutoCommit(false);


            stmt = c.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS SCORING " +
                    "(ID TEXT PRIMARY KEY NOT NULL," +
                    " LEVEL INT NOT NULL, " +
                    " SCORE INT NOT NULL) ";
            stmt.executeUpdate(sql);




            sql = "CREATE TABLE IF NOT EXISTS SETTINGS " +
                    "(ID TEXT PRIMARY KEY NOT NULL," +
                    "AUDIO TEXT NOT NULL, " +
                    "ON_OF TEXT NOT NULL); " ;

            stmt.executeUpdate(sql);

            c.commit();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");

    }


    /*! \fn public static SQL getInstance()
            \brief Returneaza instanta bazei de date daca aceasta exista. In caz contrar, creeaza o noua
                    instanta, pe care apoi o returneaza.
    */
    public static SQL getInstance() {
        if (instance == null)
            instance = new SQL();
        return instance;
    }

    /*! \fn public void closeConnection()
                \brief Inchide conexiunea cu baza de date.
        */
    public void closeConnection() {
        try {

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


    /*! \fn public void addAudioStatus(String audioType,int status)
                  \brief Adauga in tabela pentru setari starea sunetelor/muzicii de fundal (ON/OFF)

                  \param audioType Tipul audio al carui status este inserat in baza de date (music/sound)
                  \param status Starea audio (ON/OFF)
    */
    public void addAudioStatus(String audioType,int status){
        Date date = new Date();

        try {
            String sql = "INSERT INTO SETTINGS (ID,AUDIO,ON_OF) " +
                    "VALUES (" + date.getTime() + ",\"" + audioType + "\" , \"" + status + "\");";
            stmt.executeUpdate(sql);
            c.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


    /*! \fn public int getMusicStatus()
                 \brief Returneaza starea de ON/OFF a muzicii de fundal
   */
    public int getMusicStatus(){
        try {
            ResultSet rs = stmt.executeQuery("SELECT ON_OF FROM SETTINGS WHERE ID=(SELECT MAX(ID) FROM SETTINGS WHERE AUDIO IN ('music')); ");


            if(rs.next())
                return rs.getInt("ON_OF");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return -1;
    }



    /*! \fn public int getSoundStatus()
                 \brief Returneaza starea de ON/OFF a sunetelor
   */
    public int getSoundStatus(){
        try {
            ResultSet rs = stmt.executeQuery("SELECT ON_OF FROM SETTINGS WHERE ID=(SELECT MAX(ID) FROM SETTINGS WHERE AUDIO IN ('sound')); ");
            if(rs.next())
                return rs.getInt("ON_OF");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return -1;
    }


    /*! \fn public void addScore(int level, int score)
          \brief  Insereaza in baza de date scorul pentru fiecare nivel (timpul in care se parcurge nivelul).

          \param level Nivelul al carui scor se adauga in baza de date.
          \param score Scorul care se adauga in baza de date.
  */
    public void addScore(int level, int score) {

        Date date = new Date();

        try {
            String sql = "INSERT INTO SCORING (ID,LEVEL,SCORE) " +
                    "VALUES (" + date.getTime() + "," + level + " , " + score + ");";
            stmt.executeUpdate(sql);
            c.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }


    /*! \fn public int getHighScore(int level)
          \brief  Interogheaza si returneaza din baza de date cel mai bun scor pentru nivelul dat ca parametru.

          \param level Nivelul pentru care se cere cel mai bun scor.
  */
    public int getHighScore(int level){
        try {
            ResultSet rs = stmt.executeQuery("SELECT SCORE FROM SCORING WHERE SCORE=(SELECT MAX(SCORE) FROM SCORING) " +
                    " AND LEVEL IN ("+level+");");

            if(rs.next())
                return rs.getInt(1);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return -1;
    }



    /*! \fn public void deleteALL()
          \brief In cazul in care se selecteaza optiunea "New game" , se sterg toate inregistrarile din tabela
                    de scoruri a bazei de date si se porneste cu noi recorduri.
  */
    public void deleteALL()
    {
        try{
            String sql = "DELETE FROM SCORING " ;

            stmt.executeUpdate(sql);
            c.commit();
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
