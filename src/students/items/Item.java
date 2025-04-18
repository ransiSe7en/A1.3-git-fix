package students.items;

public abstract class Item {
	double age = 0;
	double maturationAge;
	double deathAge;
	double monetaryValue;
	
	public Item(double maturationAge, double deathAge, double monetaryValue) {
		this.maturationAge=maturationAge;
		this.deathAge= deathAge;
		this.monetaryValue= monetaryValue;
	}
	
//	tick
	public void tick() {
		this.age++;
	}
//  getAge
    public double getAge() {
        return this.age;
    }
// getDeathAge
    public double getDeathAge() {
        return this.deathAge;
    }
    
//	setAge
	public void setAge(int age) {
		this.age = age;
	}
	
//	to check if its dead
	public boolean died() {
		if(this.age>this.deathAge) {
			return true;
		}
		return false;
	}
	
//get value for food items if fully grown
	public double getValue() {
	    if (this.age >= this.maturationAge) {
	        return this.monetaryValue;
	    }
	    return 0;
	}

// check if any other object has equal values
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
	    if (!(obj instanceof Item)) return false;
  
	    Item other = (Item) obj;
	    if (this.age != other.age) return false;
	    if (this.maturationAge != other.maturationAge) return false;
	    if (this.deathAge != other.deathAge) return false;
	    if (this.monetaryValue != other.monetaryValue) return false;
	    
	    return true;
	}
	
//	abstract function for subclasses to return a string representation of the item
	public abstract String toString();

	//FERTILISER
		private boolean fertilised = false;
		private int fertilisedDaysLeft = 0;

		
		// to check if its fertilized to increase tick
		public boolean isFertilised() {
		    return fertilised;
		}

		//when fertliser is bought to apply fertiliser status
		public void setFertilised(boolean fertilised) {
		    this.fertilised = fertilised;
		    if (fertilised) {
		    	// Fertiliser lasts for 2 ticks
		        this.fertilisedDaysLeft = 2; 
		    }
		}
		// To keep track of the remaining days that the fertliser would be effective
		public void updateFertiliserStatus() {
		    if (fertilised) {
		        fertilisedDaysLeft--;
		        System.out.println("Fertiliser days left: " + fertilisedDaysLeft +"\n");
		        if (fertilisedDaysLeft <= 0) {
		            fertilised = false;
		            System.out.println("Fertiliser has worn off.\n");
		        }
		    }
		}
	
}
