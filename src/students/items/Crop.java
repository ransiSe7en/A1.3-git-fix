package students.items;

//For added functionality to potentially include more items such as oranges etc
public class Crop extends Food {
    private String name;

    public Crop(String name, double maturationAge, double deathAge, double monetaryValue) {
        super(maturationAge, deathAge, monetaryValue);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " (Age: " + (int)getAge() + ")";
    }
}
