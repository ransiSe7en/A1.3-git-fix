package students;

import java.util.Scanner;

public class Farm {
    private Field field;
    private int money;

    public Farm(int fieldWidth, int fieldHeight, int startingFunds) {
        field = new Field(fieldWidth, fieldHeight);
        money = startingFunds;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public boolean spendMoney(int amount) {
        if (money >= amount) {
            money -= amount;
            return true;
        } else {
            return false;
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            // Print field and money
            System.out.println(field.toString());
            System.out.println("Bank balance: $" + money);
            System.out.println("Enter your next action:");
            System.out.println("  t x y: till");
            System.out.println("  h x y: harvest");
            System.out.println("  p x y: plant");
            System.out.println("  s: field summary");
            System.out.println("  w: wait");
            System.out.println("  q: quit");
            System.out.print("> ");

            input = scanner.nextLine().trim();
            String[] parts = input.split(" ");

            if (parts.length == 0) {
                System.out.println("Invalid input.");
                continue;
            }

            String command = parts[0];

            if (command.equals("q")) {
                System.out.println("Quitting the game.");
                break;
            } else if (command.equals("s")) {
                System.out.println(field.getSummary());
            } else if (command.equals("w")) {
                field.tick();
            } else {
                System.out.println("Invalid command.");
            }
        }

        scanner.close();
    }

}
