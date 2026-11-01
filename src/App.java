public class App {
    public static void main(String[] args) {
        BankAccount alice = new BankAccount("Alice", new Balance(100_000L, Currency.RUB), 50_000L);
        BankAccount bob = new BankAccount("Bob", new Balance(50_00L, Currency.USD), 100_000L);

        System.out.println(alice);
        System.out.println(bob);

        System.out.println("\ndroppin some money");
        alice.deposit(new Balance(500_00L, Currency.RUB));
        bob.deposit(new Balance(10_00L, Currency.USD));
        System.out.println(alice);
        System.out.println(bob);

        System.out.println("\nfew good transfers");
        try {
            System.out.println("Alice tryna slide Bob 100 USD...");
            alice.transfer(bob, new Balance(100_00L, Currency.USD));
        } catch (IllegalArgumentException e) {
            System.out.println("Hold up: " + e.getMessage());
        }

        try {
            System.out.println("Alice tryna slide Bob 10 USD...");
            alice.transfer(bob, new Balance(10_00L, Currency.USD));
            System.out.println("Fasho! Transfer went through.");
        } catch (IllegalArgumentException e) {
            System.out.println("Hold up: " + e.getMessage());
        }

        System.out.println("\nfinal stacks");
        System.out.println(alice);
        System.out.println(bob);

        System.out.println("\ntryna cash out (withdraw)");
        try {
            System.out.println("Alice tryna pull out 10 RUB...");
            alice.withdraw(new Balance(10_00L, Currency.RUB));
        } catch (IllegalArgumentException e) {
            System.out.println("Hold up: " + e.getMessage());
        }
    }
}