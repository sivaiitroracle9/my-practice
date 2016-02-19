package designpatterns.spring.dependencyinjection;

public abstract class AbstractMessageService {
	public abstract void sendMessage(String recv, String msg);
}
