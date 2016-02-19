package designpatterns.spring.dependencyinjection.app;

import designpatterns.spring.dependencyinjection.AbstractApplication;
import designpatterns.spring.dependencyinjection.AbstractMessageService;

public class MyApplication extends AbstractApplication {

	private AbstractMessageService service;

	public MyApplication() {

	}

	public void setService(AbstractMessageService svc) {
		this.service = svc;
	}

	@Override
	public void processMessages(String recv, String msg) {
		// TODO Auto-generated method stub
		this.service.sendMessage(recv, msg);
	}

}
