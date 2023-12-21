package BankVG;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static FileManager instance = new FileManager();
    private static final String USER_FILE = "src/BankVG/users.txt";

    private FileManager() {

    }

    public static FileManager getInstance() {
        return instance;
    }

    public boolean verifyUser(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(username) && userDetails[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void saveUser(String username, String password, double balance) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            bw.write(username + "," + password + "," + balance + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to file " + e.getMessage());
        }
    }

    public User getUserIfValid(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(username) && userDetails[1].equals(password)) {
                    double balance = Double.parseDouble(userDetails[2]);
                    return new StandardUser(username, password, balance);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file " + e.getMessage());
        }
        return null;
    }

    public void updateUserBalanceToFile(String username, double newBalance) {
        List<String> fileContent = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(username)) {
                    line = username + "," + userDetails[1] + "," + newBalance;
                }
                fileContent.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file " + e.getMessage());
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, false))) {
            for (String line : fileContent) {
                bw.write(line + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file " + e.getMessage());
        }
    }
    public void deleteUserFromFile(String username){
        List<String> fileContent = new ArrayList<>();
        boolean isUserFound = false;

        try(BufferedReader br = new BufferedReader(new FileReader(USER_FILE))){
            String line;
            while((line = br.readLine()) != null){
                String[] userDetails = line.split(",");
                if (!userDetails[0].equals(username)){
                    fileContent.add(line);
                } else {
                    isUserFound = true;
                    System.out.println("User found and deleted " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file " + e.getMessage());
        }
        if (!isUserFound){
            System.out.println("User not found, cannot delete");
            return;
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, false))){
            for (String line : fileContent) {
                bw.write(line + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file " + e.getMessage());
        }
    }
    public void updatePassword(String username, String newPassword){
        List<String> fileContent = new ArrayList<>();
        boolean isUserFound = false;

        try(BufferedReader br = new BufferedReader(new FileReader(USER_FILE))){
            String line;
            while((line = br.readLine()) != null){
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(username)){
                    line = username + "," + newPassword + "," + userDetails[2];
                    isUserFound = true;
                }
                fileContent.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file " + e.getMessage());
        }
        if (!isUserFound){
            System.out.println("User not found, cannot change password.");
            return;
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE))){
            for (String line : fileContent) {
                bw.write(line + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file " + e.getMessage());
        }
    }
}
//SINGLETON-PATTERN
