package students.items;

public class Weed extends Item {

    public Weed() {
        super(999999 , 999999 , -1);
    }

    @Override
    public String toString() {
        return "#";  // represented by "#"
    }

    @Override
    public double getValue() {
        return -1;  // weed value - -1
    }
}
