package students.items;

public abstract class Food extends Item{

	public Food(double maturationAge, double deathAge, double monetaryValue) {
		super(maturationAge, deathAge, monetaryValue);
	}
	
//	getValue on mature
	public double getValue() {
		if(this.age>=this.maturationAge) {
			return this.monetaryValue;
		}
		return 0.0;
	}

	public double getMaturationAge() {
		return this.maturationAge;
	}


}
