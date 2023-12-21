package BankVG;

import java.util.Scanner;

public class BankFacade {
    private StandardUser currentUser;
    boolean sessionActive;
    public void register(Scanner scan){
        RegistrationManager.handleRegistration(scan);
    }
    public void logIn(Scanner scan){
        LogInManager.handleLogInUser(scan, this);
    }
    public void userSession(Scanner scan){
        if (currentUser == null){
            System.out.println("No user logged in.");
            return;
        }
        sessionActive = true;
        while(sessionActive){
            System.out.println("1. Withdraw\n2. Deposit\n3. Account balance\n4. Delete account\n5. Change password\n6. Log out");
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice){
                case 1:
                    handleWithdraw(scan);
                    break;
                case 2:
                    handleDeposit(scan);
                    break;
                case 3:
                    System.out.println("Current balance: " + currentUser.getBalance());
                    break;
                case 4:
                    deleteAccount(scan);
                    break;
                case 5:
                    handleChangePassword(scan);
                    break;
                case 6:
                    currentUser = null;
                    sessionActive = false;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private void handleWithdraw(Scanner scan){
        System.out.print("Enter amount to withdraw: ");
        double amount = scan.nextDouble();
        scan.nextLine();
        currentUser.withdraw(amount);
        FileManager.getInstance().updateUserBalanceToFile(currentUser.getUsername(), currentUser.getBalance());
    }
    private void handleDeposit(Scanner scan){
        System.out.print("Enter amount to deposit: ");
        double amount = scan.nextDouble();
        scan.nextLine();
        currentUser.deposit(amount);
        FileManager.getInstance().updateUserBalanceToFile(currentUser.getUsername(), currentUser.getBalance());
    }
    private void deleteAccount(Scanner scan){
        System.out.println("Are you sure you want to delete this account? y/n");
        String deleteConfirmation = scan.nextLine();
        if ("y".equalsIgnoreCase(deleteConfirmation)){
            FileManager.getInstance().deleteUserFromFile(currentUser.getUsername());
            currentUser = null;
            sessionActive = false;
            System.out.println("Account deleted successfully");
        } else {
            System.out.println("Account deletion cancelled");
        }
    }
    private void handleChangePassword(Scanner scan){
        System.out.println("Enter new password");
        String newPassword = scan.nextLine();
        FileManager.getInstance().updatePassword(currentUser.getUsername(), newPassword);
        currentUser.setPassword(newPassword);
        System.out.println("Password changed successfully.");
    }
    public void setCurrentUser(StandardUser user){
        this.currentUser = user;
    }
}
