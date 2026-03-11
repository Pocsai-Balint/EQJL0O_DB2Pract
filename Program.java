public class Program {
    public static void main(String[]args){
        ConsoleMethods cm =new ConsoleMethods();
        DbMethods.Register();
        
        while (true){
            System.out.println("\n AUTÓ ADATBÁZIS: ");
            System.out.println("1 - Új rekord beszúrása");
            System.out.println("2 - Ár ,ódosítás rendszám alapján");
            System.out.println("3 - Törlés rendszám alapján");
            System.out.println("4 - Összes adat listázása");
            System.out.println("0 - Kilépés");

            int choice =cm.readInt("Választás");
            
            switch (choice){
                case 1:
                    String rendszam =cm.readstring("Kérem a rendszámot:");
                    String tipus =cm.readstring("Kérem a tipust:");
                    String szin =cm.readstring("Kérem a szin:");
                    int kor =cm.readInt("Kérem a kort: ");
                    int ar =cm.readInt("Kérem az árat: ");
                    String tulaj =cm.readstring("Kérem a tulajdonost:");
                    DbMethods.Insert(rendszam, tipus, szin, kor, ar, tulaj);
                    break;
                
                case 2:
                String r2 =cm.readstring("Kérem a rendszámot:");



                    
                    int ujAr =cm.readInt("Kérem az új árat: ");
                    DbMethods.UpdatedData(r2, ujAr);
                    break;

                case 3:
                    String r3 =cm.readstring("Kérem a rendszámot:");
                    
                    DbMethods.DeleteData(r3);
                    break;

                case 4:
                    DbMethods.ReadAllData();
                    break;

                case 0:
                    System.out.println("Kilépés...");;
                    return;

                    default:
                        System.out.println("Érvénytelen választás");;
                        break;
            }
        }
    }
}
