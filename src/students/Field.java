package students;

import students.items.*;

public class Field {
    private int height;
    private int width;
    private Item[][] field;

    public Field(int height, int width) {
        this.height = height;
        this.width = width;
        this.field = new Item[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                field[row][col] = new Soil();
            }
        }
    }
    
    public void tick() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Item currentItem = field[row][col];

                // Call tick method for the item (ages it)
                currentItem.tick();

                // If the item is Soil, 20% chance it turns into Weed
                if (currentItem instanceof Soil && Math.random() < 0.2) {
                    field[row][col] = new Weed();  // Soil turns into Weed
                }

                // If the item has died (age exceeds death age), it turns into UntilledSoil
                if (currentItem.getAge() > currentItem.getDeathAge()) {
                    field[row][col] = new UntilledSoil();  // Turn into UntilledSoil
                }
            }
        }
    }
    
    @Override
    public String toString() {
        StringBuilder fieldRepresentation = new StringBuilder();

        // Add header with column numbers
        for (int i = 1; i <= width; i++) {
            fieldRepresentation.append(i).append(" ");
        }
        fieldRepresentation.append("\n");

        // Add rows with row numbers
        for (int row = 0; row < height; row++) {
            fieldRepresentation.append(row + 1).append(" ");  // Row number

            for (int col = 0; col < width; col++) {
                // Append the string representation of each item
                fieldRepresentation.append(field[row][col].toString()).append(" ");
            }
            fieldRepresentation.append("\n");
        }

        return fieldRepresentation.toString();
    }

    public void till(int row, int col) {
        // Change the item at the specified location to new Soil
        field[row][col] = new Soil();
    }
    
    public Item get(int row, int col) {
        // Return a copy of the item at the specified location
        return field[row][col];
    }
    
    public void plant(int row, int col, Item item) {
        // Place the given item at the specified location
        field[row][col] = item;
    }
    
    public double getValue() {
        double totalValue = 0;

        // Loop through each item in the field
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                // Add the value of each item to totalValue
                totalValue += field[row][col].getValue();
            }
        }

        return totalValue;
    }
    
    public String getSummary() {
        int apples = 0;
        int grain = 0;
        int soil = 0;
        int untilled = 0;
        int weed = 0;
        double totalValue = 0;

        // Loop through each item in the field and count them
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Item currentItem = field[row][col];
                if (currentItem instanceof Apple) apples++;
                if (currentItem instanceof Grain) grain++;
                if (currentItem instanceof Soil) soil++;
                if (currentItem instanceof UntilledSoil) untilled++;
                if (currentItem instanceof Weed) weed++;

                totalValue += currentItem.getValue();
            }
        }

        return String.format("Apples:        %d\n" +
                             "Grain:         %d\n" +
                             "Soil:          %d\n" +
                             "Untilled:      %d\n" +
                             "Weed:          %d\n" +
                             "For a total of $%.2f\n" +
                             "Total apples created: %d\n" +
                             "Total grain created: %d", apples, grain, soil, untilled, weed, totalValue, apples, grain);
    }


}


