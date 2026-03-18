import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class DbMethods {

    final static String url = "jdbc:sqlite:C:/sqlite3/PBProjetctdb.db";

    public static void Register() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception: " + e.getMessage());
        }
    }

    public static Connection Connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void DisConnect(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

   
    public static void ReadAllData() {
        int id;
        String vnev;
        String knev;
        String szuli;
        String lakcim;

        String sqlp = "SELECT ID, Vnev, Knev, SzulI, Lakcim FROM Hallgato";
        Connection conn = Connect();

        try {
            Statement statement = conn.createStatement();
            ResultSet result_set = statement.executeQuery(sqlp);

            while (result_set.next()) {
                id = result_set.getInt("ID");
                vnev = result_set.getString("Vnev");
                knev = result_set.getString("Knev");
                szuli = result_set.getString("SzulI");
                lakcim = result_set.getString("Lakcim");

                System.out.println(
                    id + "\t" +
                    vnev + "\t" +
                    knev + "\t" +
                    szuli + "\t" +
                    lakcim
                );
            }

            result_set.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("Adatbázis hiba: "+e.getMessage());
        } finally {
            DisConnect(conn);
        }
    }
}