public class Program {
    public static void main(String[] args) {
        ConsoleMethods cm = new ConsoleMethods();
        DbMethods.Register();

        while (true) {
            System.out.println("\n=== FŐMENÜ ===");
            System.out.println("1 - Könyvtár kezelése");
            System.out.println("2 - Kölcsönzések kezelése");
            System.out.println("0 - Kilépés");

            int valasztas = cm.readInt("Választás:");

            if (valasztas == 1) {
                konyvtarMenu(cm);
            } else if (valasztas == 2) {
                kolcsonzesMenu(cm);
            } else if (valasztas == 0) {
                System.out.println("Viszlát!");
                return;
            } else {
                System.out.println("Nincs ilyen opció!");
            }
        }
    }

    // --- KÖNYVTÁR ALMENÜ ---
    public static void konyvtarMenu(ConsoleMethods cm) {
        boolean vissza = false;
        while (!vissza) {
            System.out.println("\n--- KÖNYVTÁR KEZELÉSE ---");
            System.out.println("1 - Könyv felvétel");
            System.out.println("2 - Ár módosítás");
            System.out.println("3 - Törlés");
            System.out.println("4 - Listázás");
            System.out.println("0 - Vissza a főmenübe");

            int v = cm.readInt("Választás:");

            switch (v) {
                case 1:
                    String cim = cm.readString("Cím:");
                    String szerzo = cm.readString("Szerző:");
                    String mufaj = cm.readString("Műfaj:");
                    int ev = cm.readInt("Kiadás éve:");
                    int ar = cm.readPositiveInt("Ár:");
                    DbMethods.InsertKonyv(cim, szerzo, mufaj, ev, ar);
                    break;
                case 2:
                    int id = cm.readInt("Módosítandó könyv ID-ja:");
                    int ujAr = cm.readPositiveInt("Új ár:");
                    DbMethods.UpdateAr(id, ujAr);
                    break;
                case 3:
                    int id2 = cm.readInt("Törlendő könyv ID:");
                    DbMethods.DeleteKonyv(id2);
                    break;
                case 4:
                    DbMethods.ListAll();
                    break;
                case 0:
                    vissza = true;
                    break;
                default:
                    System.out.println("Érvénytelen választás!");
            }
        }
    }

    // --- KÖLCSÖNZÉS ALMENÜ ---
    public static void kolcsonzesMenu(ConsoleMethods cm) {
        boolean vissza = false;
        while (!vissza) {
            System.out.println("\n--- KÖLCSÖNZÉSEK KEZELÉSE ---");
            System.out.println("1 - Új kölcsönzés rögzítése");
            System.out.println("2 - Kölcsönzések listázása");
            System.out.println("0 - Vissza a főmenübe");

            int v = cm.readInt("Választás:");

            switch (v) {
                case 1:
                    int konyvID = cm.readInt("Könyv ID:");
                    String nev = cm.readString("Kölcsönző neve:");
                    String datum = cm.readDate("Dátum:");
                    int dij = cm.readPositiveInt("Díj:");
                    DbMethods.InsertKolcsonzes(konyvID, nev, datum, dij);
                    break;
                case 2:
                    listKolcsonzesek(); 
                    break;
                case 0:
                    vissza = true;
                    break;
                default:
                    System.out.println("Érvénytelen választás!");
            }
        }
    }
