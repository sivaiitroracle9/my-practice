package designpatterns.creational.abstractFactory;

public class ReptileFactory extends SpeciesFactory{

	@Override
	public Animal getAnimal(String animal) {
		// TODO Auto-generated method stub
		if(animal.equals("snake")){
			return new Snake();
		}
		
		if(animal.equals("lizard")){
			return new Lizard();
		}
		
		return null;
	}

}
