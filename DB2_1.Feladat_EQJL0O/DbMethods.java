import java.sql.*;

public class DbMethods {

    final static String URL = "jdbc:sqlite:C:/EQJL0O_sql3/Kolcsonzes.db";

    public static void Register() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection Connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void Disconnect(Connection conn) {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    public static void InsertKonyv(String cim, String szerzo, String mufaj, int ev, int ar) {
        String sql = "INSERT INTO Konyv (Cim, Szerzo, Mufaj, KiadasEve, Ar) VALUES (?, ?, ?, ?, ?)";

        Connection conn = Connect();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cim);
            ps.setString(2, szerzo);
            ps.setString(3, mufaj);
            ps.setInt(4, ev);
            ps.setInt(5, ar);

            ps.executeUpdate();
            System.out.println("Könyv hozzáadva!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        Disconnect(conn);
    }

    
    public static void ListAll() {
    // Csak a könyv tábla adatait kérjük le
    String sql = "SELECT Cim, Szerzo, Mufaj, KiadasEve, Ar FROM Konyv";

    Connection conn = Connect();

    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        System.out.println("\n--- KÖNYVEK ADATAI ---");
        System.out.println("Cím | Szerző | Műfaj | Kiadás éve | Ár");
        System.out.println("---------------------------------------");

        while (rs.next()) {
            System.out.println(
                rs.getString("Cim") + " | " +
                rs.getString("Szerzo") + " | " +
                rs.getString("Mufaj") + " | " +
                rs.getInt("KiadasEve") + " | " +
                rs.getInt("Ar") + " Ft"
            );
        }

    } catch (SQLException e) {
        System.out.println("Hiba a listázáskor: " + e.getMessage());
    }

    Disconnect(conn);
}

   
    public static void UpdateAr(int id, int ujAr) {
        String sql = "UPDATE Konyv SET Ar=? WHERE KonyvID=?";

        Connection conn = Connect();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ujAr);
            ps.setInt(2, id);

            ps.executeUpdate();
            System.out.println("Frissítve!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        Disconnect(conn);
    }

    
    public static void DeleteKonyv(int id) {
        String sql = "DELETE FROM Konyv WHERE KonyvID=?";

        Connection conn = Connect();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Törölve!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        Disconnect(conn);
    }


public static void InsertKolcsonzes(int konyvID, String nev, String datum, int dij) {

    String sql = "INSERT INTO Kolcsonzes (KonyvID, KolcsonzoNev, KolcsonzesDatum, Dij) VALUES (?, ?, ?, ?)";

    Connection conn = Connect();

    try {
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, konyvID);
        ps.setString(2, nev);

        
        ps.setString(3, datum);

        ps.setInt(4, dij);

        ps.executeUpdate();

        System.out.println("Kölcsönzés rögzítve!");

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }

    Disconnect(conn);
}

 private static void listKolcsonzesek() {
        String sql = "SELECT * FROM Kolcsonzes";
        try (Connection conn = DbMethods.Connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            System.out.println("\n--- AKTÍV KÖLCSÖNZÉSEK ---");
            System.out.println("ID | KönyvID | Név | Dátum | Díj");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("KolcsonzesID") + " | " +
                    rs.getInt("KonyvID") + " | " +
                    rs.getString("KolcsonzoNev") + " | " +
                    rs.getString("KolcsonzesDatum") + " | " +
                    rs.getInt("Dij") + " Ft"
                );
            }
        } catch (SQLException e) {
            System.out.println("Hiba: " + e.getMessage());
        }
    }
}
