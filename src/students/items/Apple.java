package students.items;

public class Apple extends Food {
    private static int totAppleObjects = 0;

    public Apple() {
    	// maturation- 3, death- 5, mValue- 3
        super(3, 5, 3);  
        totAppleObjects++;
    }

    public static int getGenerationCount() {
        return totAppleObjects;
    }

    @Override
    public String toString() {
    	if (this.age < this.maturationAge) {
            return "a";
        } else {
        	return "A";
        }
    }
}
