package students;

import students.items.*;

//Represents a 2D farm field containing Items like crops, soil, weeds, etc.
public class Field {
    private int height;
    private int width;
    private Item[][] field;

    //Created the field with soil based on dimensions
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
    //Returns width of field
    public int getWidth() {
        return width;
    }

    //Returns height of field
    public int getHeight() {
        return height;
    }

    
    // Ages each item when called and updates field
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
    
    // Returns the field as a string (with row and column numbers in human readable format)
    @Override
    public String toString() {
        StringBuilder fieldRepresentation = new StringBuilder();

        // Add header with column numbers
        fieldRepresentation.append("  ");
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

    //Tills by turning a specific location in field to soil
    public void till(int row, int col) {
        // Change the item at the specified location to new Soil
        field[row][col] = new Soil();
    }
    
    //Returns the item at a specific location
    public Item get(int row, int col) {
        // Return a copy of the item at the specified location
        return field[row][col];
    }

    //Plants an item at a given location
    public void plant(int row, int col, Item item) {
        System.out.printf("Planting at row=%d, col=%d\n", row, col);
        field[row][col] = item;
    }
    
    //Calculates the total value of the field
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
    
    //REturns a summary of the field
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
                if (currentItem instanceof Apples) apples++;
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


