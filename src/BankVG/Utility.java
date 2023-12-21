package BankVG;

import java.io.IOException;

public class Utility {
    public static void clearScreen(){
        try{
            String operatingSystem = System.getProperty("os.name");

            if (operatingSystem.contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Exception occurred while clearing screen " + e.getMessage());
        }
    }
}
