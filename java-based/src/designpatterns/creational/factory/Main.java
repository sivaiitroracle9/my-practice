package designpatterns.creational.factory;

public class Main {

	public static void main(String[] args){
		Currency indianCurrency = CurrencyFactory.getCurrency("India");
		
		System.out.println("Symbol : " + indianCurrency.getSymbol());
		
	}
}
