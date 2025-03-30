package students.items;

public class Grain extends Food{
	int totGrainObjects;
	String grain;
	
	public Grain() {
		super(2,6,2);
		totGrainObjects++;
		if (this.age < this.maturationAge) {
	        grain = "g";
	    }
	    grain = "G";
	}    
    public int getGenerationCount() {
    	return totGrainObjects;
    }
	
}
