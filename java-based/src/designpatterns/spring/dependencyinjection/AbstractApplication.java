package designpatterns.spring.dependencyinjection;

public abstract class AbstractApplication {
	public abstract void processMessages(String recv, String msg);
	public abstract void setService(AbstractMessageService svc);
}
