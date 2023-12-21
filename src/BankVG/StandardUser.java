package BankVG;

public class StandardUser implements User{
    private String username;
    private String password;
    private double balance;

    public StandardUser(String username, String password){
        this.username = username;
        this.password = password;
    }
    public StandardUser(String username, String password, double balance){
        this.username = username;
        this.password = password;
        this.balance = balance;
    }
    public void deposit(double amountFunds){
        if (amountFunds > 50000){
            System.out.println("Amount exceeds deposit limit, please contact your bank.");
        } else {
            this.balance += amountFunds;
            System.out.println("Deposit successful.\nNew balance: " + this.balance);
        }
    }
    public void withdraw(double amountFunds){
        if (this.balance < amountFunds){
            System.out.println("Insufficient funds.");
        } else {
            this.balance -= amountFunds;
            System.out.println("Withdrawal successful.\nNew balance: " + this.balance);
        }
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
