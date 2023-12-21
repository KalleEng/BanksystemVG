package BankVG;

import java.util.Scanner;

public class RegistrationManager {
    public static void handleRegistration(Scanner scan){
        System.out.println("Choose username: ");
        String username = scan.nextLine();

        System.out.println("Choose password: ");
        String password = scan.nextLine();

        if (!FileManager.getInstance().verifyUser(username, password)){
            FileManager.getInstance().saveUser(username, password, 0);
            System.out.println("Registration successful.");
        } else {
            System.out.println("Username already exists, try different username.");
        }
    }
}
