package lincyu.chapter13_culture;

public class Category {
	
	String name;
	int type;
	
	Category(String name, int type) {
		this.name = name;
		this.type = type;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
