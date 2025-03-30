package students.items;

public class Item {
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
		this.age+=1;
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
	

	
	
	
}
