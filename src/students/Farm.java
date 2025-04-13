package students;

import java.util.Scanner;

import students.items.Apples;
//import students.items.Crop;
import students.items.Food;
import students.items.Grain;
import students.items.Item;
import students.items.Soil;

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
            
            // If no input
            if (parts.length == 0) {
                System.out.println("Invalid input.");
                continue;
            }
            String command = parts[0];

            // Quit
            if (command.equals("q")) {
                System.out.println("Quitting the game.");
                break;
            
            // Summary
            } else if (command.equals("s")) {
            	// Summary
                System.out.println(field.getSummary());
            
            // Wait to tick
            } else if (command.equals("w")) {
            	// Ticking
                field.tick();
                
             // Till
            } else if (command.equals("t") && parts.length == 3) {
            	// Get coordinates
                int col = Integer.parseInt(parts[1]);
                int row = Integer.parseInt(parts[2]);
                
                // Validate input
                if (col < 1 || col > field.getWidth() || row < 1 || row > field.getHeight()) {
                    System.out.println("Invalid coordinates. Try again.");
                } else {
                    field.till(row, col);
                    System.out.println("Tilled the soil at (" + col + "," + row + ").");
                }
            
            // Harvest
            } else if (command.equals("h") && parts.length == 3) {
            	// Get coordinates
                int col = Integer.parseInt(parts[1]); 
                int row = Integer.parseInt(parts[2]); 

                // Validate input
                if (col < 1 || col >= field.getWidth() || row < 1 || row >= field.getHeight()) {
                    System.out.println("Invalid coordinates. Try again.");
                } else {
                    Item item = field.get(row, col); 

                    if (item instanceof Food) {
                        Food foodItem = (Food) item;
                        //Check to ensure that the item is available for harvest - has matured
                        if (foodItem.getAge() >= foodItem.getMaturationAge()) {
                            double value = foodItem.getValue();  
                            // sells the harvest to get money 
                            addMoney((int) value);  
                            System.out.println("Harvested " + item.toString() + " and gained $" + value);
                            field.till(row, col); 
                        } else {
                            System.out.println("This crop hasn't matured yet. Try again later.");
                        }
                    } else {
                        System.out.println("There is no crop to harvest at this location.");
                    }
                }
                
             // Plant
            } else if((command.equals("p") && parts.length == 3)) {
	            // Get coordinates
	            int col = Integer.parseInt(parts[1]);
	            int row = Integer.parseInt(parts[2]);
	
	            // Validate input
	            if (col < 1 || col > field.getWidth() || row < 1 || row > field.getHeight()) {
	                System.out.println("Invalid coordinates. Try again.");
	            } else {
	                Item currentItem = field.get(row, col);
	
	                // Check if the location is available to plant (is soil)
	                if (currentItem instanceof Soil) {
	                    System.out.println("Enter:");
	                    System.out.println("- 'a' to buy an apple for $");
	                    System.out.println("- 'g' to buy grain for ");
	                    String cropChoice = scanner.nextLine().trim();
	
	                    // Planting pending to check if theres enought money in bank
	                    Food newPlant = null;
	                    if (cropChoice.equals("a")) {
	                    	// In sample output the value for an apple is indicated as just '$', However if we change this value to the price of an apple, the leftover money is calculated accordinly
	                        if (spendMoney(0)) {
	                            newPlant = new Apples();
	                            System.out.println("Bank Balance: $ " + getMoney());
	                        } else {
	                            System.out.println("Not enough money to plant an apple.");
	                            continue;
	                        }
	                    } else if (cropChoice.equals("g")) {
	                    	// In sample output the value for a grain is not, However if we change this value to the price of the grain, the leftover money is calculated accordinly
	                        if (spendMoney(0)) {
	                            newPlant = new Grain();
	                            System.out.println("Bank Balance: $ " + getMoney());
	                        } else {
	                            System.out.println("Not enough money to plant grain.");
	                            continue;
	                        }
	                    }
	                    
	                    // If theres enough money then can plant
	                    if (newPlant != null) {
	                        field.plant(row, col, newPlant);
	                    }
	
	                } else {
	                    System.out.println("You can't plant here. The soil is not ready.");
	                }
	            }
            } else {
                System.out.println("Invalid command.");
            }
        }

        scanner.close();
    }

}
