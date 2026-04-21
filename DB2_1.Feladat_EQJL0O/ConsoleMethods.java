import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ConsoleMethods {

    private final Scanner scanner = new Scanner(System.in);
    
    public String readString(String message) {
        System.out.print(message + " ");
        return scanner.nextLine().trim();
    }

    public int readInt(String message) {
        while (true) {
            try {
                System.out.print(message + " ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Hibás formátum! Kérlek számot adj meg.");
            }
        }
    }

    public int readPositiveInt(String message) {
        while (true) {
            int value = readInt(message);
            if (value >= 0) {
                return value;
            }
            System.out.println("A szám nem lehet negatív!");
        }
    }

    public String readDate(String message) {
        while (true) {
            System.out.print(message + " (ÉÉÉÉ-HH-NN): ");
            String input = scanner.nextLine().trim();
            try {
                LocalDate.parse(input);
                return input;
            } catch (DateTimeParseException e) {
                System.out.println("Hibás dátum! Használd az ÉÉÉÉ-HH-NN formátumot (pl. 2024-05-10)!");
            }
        }
    }

    public void waitForEnter() {
        System.out.println("\nNyomjon Enter-t a folytatáshoz...");
        scanner.nextLine();
    }
}
