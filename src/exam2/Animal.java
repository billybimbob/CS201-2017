package test2;

public abstract class Animal {

	protected String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "the animal is a " + name;
	}
	public boolean equals(Animal comparing) {
		return this.getName().equals(comparing.getName());
	}
	public abstract void speak();
}
