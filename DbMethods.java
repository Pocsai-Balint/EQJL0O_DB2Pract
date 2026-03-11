import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;


public class DbMethods 
{
    private static final String url= "jdbc:sqlite:C:/sqlite3/autodb";

    public static void Register()
    {

        try{
            Class.forName( "org.sqlite.JDBC");
        

        } 
        catch (ClassNotFoundException e)
        {
            System.out.println("SQLite JDBC driver nem található: " + e.getMessage());
        }

        String sql =
        "CREATE TABLE IF NOT EXIST Auto(" + "Rendszam char PRIMARY KEY," + "Tipus char NOT NULL," + "Szin CHAR NOT NULL," + "Kor INTEGER NOT NULL,"+"Ár INTEGER NOT NULL,"+ "Tulaj char NOT NULL"+");";
       
        try (Connection conn = Connect();
        Statement st=conn.createStatement())
        {
            st.execute(sql);
        }
        catch (SQLException e)
        {
            System.out.println("Register hiba" +e.getMessage());
        }
    }
    public static Connection Connect() throws SQLException 
    {
        return DriverManager.getConnection(url);
    }

    public static void ReadAllData()
    {
        String sql="SELECT Rendszam, Tipus, Szin, Kor, Ar, Tulaj FROM Auto ORDER BY Rendszam";

        try (Connection conn = Connect();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(sql))
        {
             System.out.println("\nRendszer\tTipus\tSzin\tKor\tAr\tTulaj");
            System.out.println("-------------------------------------");

            while (rs.next())
            {
                    System.out.println(
                    rs.getString("Rendszam")+ "\t"+
                    rs.getString("Tipus")+ "\t"+
                    rs.getString("Szin")+ "\t"+
                    rs.getString("Kor")+ "\t"+
                    rs.getString("Ar")+ "\t"+
                    rs.getString("Tulaj")
                );
            }
        }
        catch (SQLException e)
        {
            System.out.println("ReadAll hiba: "+ e.getMessage());
        }
    }
    public static void Insert(String rendszam, String tipus, String szin, int kor, int ar, String tulaj) 
    {
        String sql ="INSERT INTO Auot(Rendszam, Tipus, Szin, Kor, Ar, Tulaj) VALUES(?,?,?,?,?,?)";

        try (Connection conn = Connect();
        PreparedStatement ps=conn.prepareStatement(sql))
        {
            ps.setString(1, rendszam);
            ps.setString(2, tipus);
            ps.setString(3, szin);
            ps.setInt(4, kor);
            ps.setInt(5, ar);
            ps.setString(6, tulaj);

            ps.executeUpdate();
            System.out.println("Sikeres beszúrás!"); 
        }
       
        catch (SQLException e)
        {
            System.out.println("Insert hiba: " +e.getMessage());
        }
          
    }
       


public static void DeleteData(String rendszam)
    { 
        String sql = "DELETE FROM Auto WHERE Rendszam= ?";
        
        try (Connection conn =Connect();
        PreparedStatement ps =conn.prepareStatement(sql))
        {
            ps.setString(1, rendszam);
            int db =ps.executeUpdate();
            if (db==0) System.out.println("A megadott rendszámú autó nem létezik!");
                else System.out.println("Törlődött"+db+ "rekord");

        }
        catch (SQLException e)
        {
            System.out.println("Delete hiba: "+e.getMessage());
        }
    }

    public static void UpdatedData(String rendszam, int ar)
    { 
        String sql ="UPDATE Auto SET Ar = ? WHERE Rendszam = ?";

        try (Connection conn =Connect();
        PreparedStatement ps =conn.prepareStatement(sql))
        {
            ps.setInt(1, ar);
            ps.setString(2, rendszam);

            int db =ps.executeUpdate();
            if (db==0) System.out.println("A megadott rendszámú autó nem létezik!");
                else System.out.println("Módosult"+db+ "rekord");
                
        }
        catch (SQLException e)
        {
            System.out.println("Update hiba: " + e.getMessage());
        }
   
    }
}




