package designpatterns.spring.dependencyinjection.Main;

import designpatterns.spring.dependencyinjection.AbstractApplication;
import designpatterns.spring.dependencyinjection.AbstractMessageServiceInjector;
import designpatterns.spring.dependencyinjection.injector.EmailServiceInjector;
import designpatterns.spring.dependencyinjection.injector.SMSServiceInjector;

public class Main {
    public static void main(String[] args) {
        String msg = "Hi Pankaj";
        String email = "pankaj@abc.com";
        String phone = "4088888888";
        AbstractMessageServiceInjector injector = null;
        AbstractApplication app = null;
         
        //Send email
        injector = new EmailServiceInjector();
        app = injector.getMessageApp();
        app.processMessages(email, msg);
         
        //Send SMS
        injector = new SMSServiceInjector();
        app = injector.getMessageApp();
        app.processMessages(phone, msg);
    }
}
