import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;


public class DbMethods {
    private static final String url= "jdbc:sqlite:C:/sqlite3/autodb";

    public static void Register(){

        try{
            Class.forName(className "org.sqlite.JDBC");

        } 
        catch (ClassNotFoundException e){
            System.out.println("SQLite JDBC driver nem található: " + e.getMessage());
        }

        String sql =
        "CREATE TABLE IF NOT EXIST Auto(" + "Rendszam char PRIMARY KEY," + "Tipus char NOT NULL," + "sZIN CHAR NOT NULL," + "Kor INTEGER NOT NULL,"+"Ár INTEGER NOT NULL,"+ "Tulaj char NOT NULL"+");";

        try (Connection conn = Connect();
        Statement st=conn.createStatement()){
            st.execute(sql);
        }catch (SQLException e){
            System.out.println("Register hiba" +e.getMessage());
        }
    }
    public static Connection Connet() throws SQLException {
        return DriverManager.getConnection(url);
    }

    public static void ReadAllData(){
        String sql="SELECT Rendszam, Tipus, Szin, Kor, Ar, Tulaj FROM Auto ORDER BY Rendszam";

        try (Connection conn = Connect();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(sql)){
             System.out.println("\nRendszer\tTipus\tSzin\tKor\tAr\tTulaj");
            System.out.println("-------------------------------------");

            while (rs.next()){
                System.out.println(
                    rs.getString(columnLabel: "Rendszam")+ "\t"+
                    rs.getString(columnLabel: "Tipus") +"\t"+
                    rs.getString(columnLabel: "Szin")+ "\t"+
                    rs.getString(columnLabel: "Kor")+ "\t"+
                    rs.getString(columnLabel: "Ar")+ "\t"+
                    rs.getString(columnLabel: "Tulaj")
                );
            }
        }
        catch (SQLException e){
            System.out.println("ReadAll hiba: "+ e.getMessage());
        }
    }
    public static void Insert(String rendszam, String tipus, String szin, int kor, int ar, String tulaj) {
        String sql ="INSERT INTO Auot(Rendszam, Tipus, Szin, Kor, Ar, Tulaj) VALUES(?,?,?,?,?,?)";
        
    }
}
