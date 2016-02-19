package designpatterns.spring.dependencyinjection.injector;

import designpatterns.spring.dependencyinjection.AbstractApplication;
import designpatterns.spring.dependencyinjection.AbstractMessageServiceInjector;
import designpatterns.spring.dependencyinjection.app.MyApplication;
import designpatterns.spring.dependencyinjection.service.EmailService;
import designpatterns.spring.dependencyinjection.service.SMSService;

public class EmailServiceInjector extends AbstractMessageServiceInjector {

	@Override
	public AbstractApplication getMessageApp() {
		// TODO Auto-generated method stub
		AbstractApplication app = new MyApplication();
		app.setService(new EmailService());;
		return app;
	}

}
