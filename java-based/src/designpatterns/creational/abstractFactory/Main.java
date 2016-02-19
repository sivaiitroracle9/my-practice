package designpatterns.creational.abstractFactory;

public class Main {
	public static void main(String[] args){
		
		// Abstract Factory is a factory of Factories.
		
		Animal animal = AbstractFactory.getFactory("mammal").getAnimal("dog");
		animal.makeSound();
		
		Animal animal2 = AbstractFactory.getMammalFactory().getAnimal("cat");
		animal2.makeSound();
		
		Animal animal3 = AbstractFactory.getReptileFactory().getAnimal("snake");
		animal3.makeSound();
	}
}
