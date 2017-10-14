package designPatterns.strategy_pattern;

public class ModelWife extends Wife {

	public ModelWife() {
		loveBehavior = new LoveHusbandBehavior();
		cookBehavior = new CookRiceFlour();
	}
	
}
