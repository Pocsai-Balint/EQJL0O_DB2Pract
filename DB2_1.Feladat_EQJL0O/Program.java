public class Program {
    public static void main(String[] args) {

       ConsoleMethods cm = new ConsoleMethods();
        DbMethods.Register();

        while (true) {
            System.out.println("\n--- KÖNYVTÁR ---");
            System.out.println("1 - Könyv felvétel");
            System.out.println("2 - Ár módosítás");
            System.out.println("3 - Törlés");
            System.out.println("4 - Listázás");
            System.out.println("0 - Kilépés");

            int v = cm.readInt("Választás:");

            switch (v) {
                case 1:
                    String cim = cm.readString("Cím:");
                    String szerzo = cm.readString("Szerző:");
                    String mufaj = cm.readString("Műfaj:");
                    int ev = cm.readInt("Kiadás éve(ÉÉÉÉ-HH-NN):");
                    int ar = cm.readInt("Ár:");

                    DbMethods.InsertKonyv(cim, szerzo, mufaj, ev, ar);
                    break;

                case 2:
      
                    int id = cm.readInt("Módosítandó könyv ID-ja:");
                    int ujAr = cm.readPositiveInt("Új ár:");
                    DbMethods.UpdateAr(id, ujAr);
                    break;

                case 3:
                    int id2 = cm.readInt("Könyv ID:");
                    DbMethods.DeleteKonyv(id2);
                    break;

                case 4:
                    DbMethods.ListAll();
                    break;

                case 0:
                    return;
            }
        }
    }
}
