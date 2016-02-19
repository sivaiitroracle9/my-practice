package designpatterns.creational.singleton;

public class Main {
	public static void main(String[] args){
		Dog dog = Dog.getInstance();
		
		System.out.println("instance-1 : " + dog.toString());
		
		Dog dog1 = Dog.getInstance();
		System.out.println("instance-2 : " + dog1.toString());
		
	}
}
