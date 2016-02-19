package visa.Strings.FindAllPalindromes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FindAllPalindromes {
	
	public static void main(String[] args){
		
		Scanner s = new Scanner(System.in);
		String str = s.next().trim();
		Set<String> palindromes = new HashSet<String>();
		for(int i=0; i<str.length(); i++){
			Collection<String> pali = getPalindromes(str.substring(i));
			for(String p: pali){
				if(p.length()>1) palindromes.add(p);
			}
		}
		
		for(String p : palindromes){
			System.out.println(p);
		}

}
	
	static Collection<String> getPalindromes(String str){
		Set<String> palindromes = new HashSet<String>();
		for(int i=0; i<str.length(); i++){
			String substr = str.substring(i);
			char left = substr.charAt(0);
			int lastidx = substr.lastIndexOf(left);
			if(lastidx>0) {
				Collection<String> pali = getPalindromes(substr.substring(1, lastidx));
				if(pali.size()==0){
					String pn = String.valueOf(left) + String.valueOf(left);
					palindromes.add(pn);
				}
				for(String p: pali){
					String pn = String.valueOf(left) + p + String.valueOf(left);
					palindromes.add(pn);
				}
			} else {
				palindromes.add(String.valueOf(left));
			}
			
		}
		return palindromes;
	}
	
}
