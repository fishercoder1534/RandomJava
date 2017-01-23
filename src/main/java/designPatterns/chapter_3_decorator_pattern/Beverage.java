package designPatterns.chapter_3_decorator_pattern;

public abstract class Beverage {
	String description = "Unknown beverage";

	public String getDescription() {
		return description;
	}

	public abstract double cost();
}
