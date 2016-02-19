package designpatterns.creational.abstractFactory;

public class AbstractFactory {
	
	// Abstract Factory is a factory of Factories.
	
	public static SpeciesFactory getFactory(String species) {

		if (species.equals("mammal")) {
			return new MammalFactory();
		}

		if (species.equals("reptile")) {
			return new ReptileFactory();
		}

		return null;
	}
	
	public static SpeciesFactory getMammalFactory(){
		return getFactory("mammal");
	}
	
	public static SpeciesFactory getReptileFactory(){
		return getFactory("reptile");
	}

}
