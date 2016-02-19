package designpatterns.creational.abstractFactory;

public class MammalFactory extends SpeciesFactory{

	@Override
	public Animal getAnimal(String animal) {
		// TODO Auto-generated method stub
		
		if(animal.equals("dog")) {
			return new Dog();
		}
		
		if(animal.equals("cat")) {
			return new Cat();
		}
		
		return null;
	}

}
