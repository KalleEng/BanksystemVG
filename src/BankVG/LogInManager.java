package BankVG;

import java.util.Scanner;

public class LogInManager {
    public static void handleLogInUser(Scanner scan, BankFacade bankFacade){
        System.out.println("Enter username: ");
        String username = scan.nextLine();

        System.out.println("Enter password: ");
        String password = scan.nextLine();

        User isValidUser = FileManager.getInstance().getUserIfValid(username, password);
        if (isValidUser != null){
            System.out.println("Login succesful.");
            bankFacade.setCurrentUser((StandardUser) isValidUser);
            bankFacade.userSession(scan);
        } else {
            System.out.println("Invalid username or password, please try again.");
        }
    }
}
