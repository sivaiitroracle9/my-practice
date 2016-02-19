package designpatterns.spring.dependencyinjection.service;

import designpatterns.spring.dependencyinjection.AbstractMessageService;

public class SMSService extends AbstractMessageService {

	@Override
	public void sendMessage(String recv, String msg) {
		// TODO Auto-generated method stub
		System.out.println("SMS Sent -- > RECV: " + recv + " -- MSG: " + msg);
	}

}
