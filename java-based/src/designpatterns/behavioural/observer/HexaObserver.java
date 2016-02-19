package designpatterns.behavioural.observer;

public class HexaObserver extends Observer{

	public HexaObserver(Subject subj) {
		this.subject = subj;
		this.subject.attachObserver(this);
	}
	
	@Override
	public void update() {
		System.out.println("Hexa State = " + Integer.toHexString(subject.getState()));
		
	}

}
