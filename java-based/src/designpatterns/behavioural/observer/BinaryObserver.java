package designpatterns.behavioural.observer;

public class BinaryObserver extends Observer {

	public BinaryObserver(Subject subj) {
		this.subject = subj;
		this.subject.attachObserver(this);
	}

	@Override
	public void update() {
		System.out.println("Binaray State = " + Integer.toBinaryString(subject.getState()));

	}

}
