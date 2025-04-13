package students.items;

public class Grain extends Food{
	private static int totGrainObjects = 0;
	String grain;
	
	public Grain() {
		super(2,6,2);
		totGrainObjects++;
	}    
    public int getGenerationCount() {
    	return totGrainObjects;
    }
    
    @Override
    public String toString() {
    	if (this.age < this.maturationAge) {
            grain = "g";
        }
        grain = "G";
        
        return grain;
    }
}
