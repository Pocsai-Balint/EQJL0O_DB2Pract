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
                    String rendszam = cm.readString("Kérem a rendszámot:");
                    String tipus = cm.readString("Kérem a tipust:");
                    String szin = cm.readString("Kérem a színt:");
                    int kor = cm.readInt("Kérem a kort:");
                    int ar = cm.readInt("Kérem az árat:");
                    String tulaj = cm.readString("Kérem a tulajdonost");
                    DbMethods.Insert(rendszam, tipus, szin, kor, ar, tulaj);
                    break;
                
                case 2:
                    String r2 =cm.ReadString("Kérem a rendszámot:")
                    int ujAr =cm.readInt("Kérem az új árat: ");
                    DbMethods.UpdateData(r2, ujAr);
                    break;

                case 3:
                    String r3 =cm.readString("Kérem a rendszámot: ");
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
