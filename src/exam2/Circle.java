package test2;

public class Circle {

	private double radius;
	
	public Circle() {
		radius = 1;
	}
	public Circle(double radius) {
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public String toString() {
		return "has a radius of " + this.getRadius();
	}
	public boolean equals(Circle comparing) {
		return this.getRadius() == comparing.getRadius();
	}
	
	public double area() {
		return Math.PI*Math.pow(this.getRadius(), 2);
	}
	public double circumference() {
		return 2*Math.PI*this.getRadius();
	}
}
