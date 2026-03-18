import java.util.Scanner;

public class ProgramMenu {

    // a metódus ellenőrzi a biztos szám beolvasását
    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Hibás szám, próbáld újra!");
            }
        }
    }

    // a metódus egy logikai értéket (boolean) olvas be a felhasználótól
    private static boolean readBool(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim().toLowerCase();
            if (line.equals("true") || line.equals("t") || line.equals("1") || line.equals("igen") || line.equals("i")) return true;
            if (line.equals("false") || line.equals("f") || line.equals("0") || line.equals("nem") || line.equals("n")) return false;
            System.out.println("Hibás érték! Írd: true/false.");
        }
    }

    // a metódus beolvas és visszaad
    private static String readText(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    public static void main(String[] args) {

        DbMethods.Register();

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nFŐMENÜ");
            System.out.println("1 - Rekordok száma - task_a");
            System.out.println("2 - Oszlop összeg és átlag - task_b");
            System.out.println("3 - Rendezett lista - task_c");
            System.out.println("4 - Hallgatók projektjeinek száma - task_d");
            System.out.println("5 - Tábla listázása");
            System.out.println("0 - Kilépés");

            int choice = readInt(sc, "Választás: ");

            switch (choice) {
                case 1: {
                    String tableA = readText(sc, "Tábla neve: ");
                    ListingMethods.task_a(tableA);
                    break;
                }
                case 2: {
                    String tableB = readText(sc, "Tábla neve: ");
                    String column = readText(sc, "Oszlop neve: ");
                    ListingMethods.task_b(tableB, column);
                    break;
                }
                case 3: {
                    String tableC = readText(sc, "Tábla neve: ");
                    String order = readText(sc, "Rendezési oszlop: ");
                    boolean asc = readBool(sc, "Növekvő sorrend? (true/false): ");
                    ListingMethods.task_c(tableC, order, asc);
                    break;
                }
                case 4: {
                    ListingMethods.task_d();
                    break;
                }
                case 5: {
                    // Fix választás
                    System.out.println("Tábla kiválasztása");
                    System.out.println("1 - Hallgato");
                    System.out.println("2 - Projekt");
                    System.out.println("3 - Dolgozik");
                    int t = readInt(sc, "Választás: ");

                    if (t == 1) ListingMethods.listTable("Hallgato");
                    else if (t == 2) ListingMethods.listTable("Projekt");
                    else if (t == 3) ListingMethods.listTable("Dolgozik");
                    else System.out.println("Érvénytelen választás!");
                    break;
                }
                case 0: {
                    running = false;
                    System.out.println("Program vége.");
                    break;
                }
                default:
                    System.out.println("Érvénytelen választás!");
            }
        }

        sc.close();
    }
}