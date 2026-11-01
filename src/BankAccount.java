import java.util.Objects;

public class BankAccount{
    private final String ownerName;
    private Balance balance;
    private final long withdrawalLimit;
    private long todayWithdrawn;

    public BankAccount(String ownerName, Balance balance, long withdrawalLimit) {
        if (ownerName == null || ownerName.isBlank()){
            throw new IllegalArgumentException("Owners name cant be empty bro");
        }
        Objects.requireNonNull(balance, "Damn bro, are we really that broke?");

        this.ownerName = ownerName;
        this.balance = balance;
        this.withdrawalLimit = withdrawalLimit;
        this.todayWithdrawn = 0;
    }
    public void deposit(Balance amount){
        Objects.requireNonNull(amount, "Cant use zero amount");
        if (!amount.getCurrency().equals(this.balance.getCurrency())){
            amount = amount.convertTo(this.balance.getCurrency());
        }
        this.balance = this.balance.add(amount);
    }
    public void withdraw(Balance amount){
        Objects.requireNonNull(amount, "Cant use zero amount");
        if (!amount.getCurrency().equals(this.balance.getCurrency())){
            amount = amount.convertTo(this.balance.getCurrency());
        }
        if (this.todayWithdrawn + amount.getKopecks() > this.withdrawalLimit){
            throw new IllegalArgumentException("Sorry man youve got your limits");
        }
        this.balance = this.balance.subtract(amount);
        this.todayWithdrawn += amount.getKopecks();
    }
    public void transfer(BankAccount target, Balance amount){
        Objects.requireNonNull(amount, "Cant use empty amount");
        Objects.requireNonNull(target, "Cant use empty target");
        this.withdraw(amount);
        target.deposit(amount);
    }
    @Override
    public String toString() {
        return String.format("Account - %s: %s (today withdrawn: %d kopecks)", 
                ownerName, balance.toString(), todayWithdrawn);
    }
    }