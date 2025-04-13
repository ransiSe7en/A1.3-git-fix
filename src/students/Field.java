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
}


