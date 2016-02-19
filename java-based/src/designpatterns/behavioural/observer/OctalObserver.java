package designpatterns.behavioural.observer;

public class OctalObserver extends Observer{

	public OctalObserver(Subject subj) {
		this.subject = subj;
		this.subject.attachObserver(this);
	}
	
	@Override
	public void update() {
		System.out.println("Octal State = " + Integer.toOctalString(subject.getState()));
		
	}

}
