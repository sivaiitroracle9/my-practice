package org.sivaiitroracle9.spring.app;

public class HelloWorld {
	private String message;

	public void getMessage() {
		System.out.println("Your message = " + this.message);
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
