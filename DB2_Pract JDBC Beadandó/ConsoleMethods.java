import java.util.Scanner;

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
            } catch (Exception e) {
                System.out.println("Hibás szám! Próbáld újra.");
            }
        }
    }

    
    public String readDate(String message) {
        System.out.print(message + " (ÉÉÉÉ-HH-NN): ");
        return scanner.nextLine().trim();
    }

  
    public int readPositiveInt(String message) {
        while (true) {
            int value = readInt(message);
            if (value >= 0) return value;

            System.out.println("Nem lehet negatív!");
        }
    }
}