package designpatterns.behavioural.observer;

public class Main {
	
	public static void main(String[] args) {
		Subject subject = new Subject();
		
		new BinaryObserver(subject);
		HexaObserver ho = new HexaObserver(subject);
		new OctalObserver(subject);
		
		System.out.println("First state : 15");
		subject.setState(15);
		
		System.out.println();
		System.out.println("Second state : 70");
		subject.setState(70);
		
		System.out.println();
		System.out.println("Third set  Hexa state : 200");
		ho.subject.setState(200);
	}

}
