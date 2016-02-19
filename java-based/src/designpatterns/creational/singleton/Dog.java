package designpatterns.creational.singleton;

public class Dog {

	private static Dog _instance = null;

	/**
	 * This is very important. By private constructor no-one can directly
	 * instantiate the Dog Class.
	 */

	private Dog() {

	}

	public static Dog getInstance() {
		synchronized (Dog.class) {
			if (_instance == null) {
				_instance = new Dog();
			}
		}

		return _instance;
	}
}
