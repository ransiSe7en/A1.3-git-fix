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

    public void run() 
    {
    }
}
