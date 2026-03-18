import java.util.Scanner;
public class ConsoleMethods { 
    private final Scanner scanner =new Scanner(System.in);
    //a scammer osztályok megnyitjuk a könyvtárat.
    //mely megkapja a system.in bemenetet - ez a bil.
    //stringet kér a usertől

    public String readstring(String message)
    {
        System.out.println(message);
        return scanner.nextLine().trim();
    }

    public int readInt(String message) {
        while (true) {
            System.out.println(message);
            String line = scanner.nextLine().trim();
            
            try {
                return Integer.parseInt(line);
            }
            catch (NumberFormatException e){
                System.out.println("Hibás szám, próbáld újra!");
            }


        }

    }
    

}