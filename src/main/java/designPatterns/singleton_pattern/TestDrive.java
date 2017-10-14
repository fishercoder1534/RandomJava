package designPatterns.singleton_pattern;

public class TestDrive {
	
	public static void main(String... args){
		System.out.println("Program started.");
		ThreadSafeSingleton threadSafeSingleton = ThreadSafeSingleton.getInstance();
		threadSafeSingleton.work();
		System.out.println("Program ended.");
	}

}
