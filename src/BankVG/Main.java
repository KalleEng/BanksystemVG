package BankVG;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BankFacade bankFacade = new BankFacade();
        while (true){
            System.out.println("1. Log in\n2. Register\n3. Exit");
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice){
                case 1:
                    bankFacade.logIn(scan);
                    break;
                case 2:
                    bankFacade.register(scan);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
