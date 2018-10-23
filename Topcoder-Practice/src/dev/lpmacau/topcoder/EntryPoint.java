package dev.lpmacau.topcoder;
import dev.lpmacau.topcoder.challenges.*;

public class EntryPoint {
	public static void main(String args[]) {
		runUnblur();
	}

	private static void runHawaiian() {
		String s1 = "Hawaii is an island";
		String s2 = "Mauna Kea and Mauna Koa are two mountains";
		String s3 = "Pineapple grows in Hawaii";

		Hawaiian h = new Hawaiian();

		for(String s : h.getWords(s1))
			System.out.println(s);
		System.out.println("------------");
		for(String s : h.getWords(s2))
			System.out.println(s);
		System.out.println("------------");
		for(String s : h.getWords(s3))
			System.out.println(s);
		System.out.println("------------");
	}

	private static void runHex() {
		
		Hex h = new Hex();
		String[] a = {"00h","21h","01v","03v","02v"};
		String[] b = {"00v","01v","02v","11h","21h"};

		for(String s : h.picture(4,a) ) {
			System.out.println(s);
		}
	}
	
	private static void runUnblur() {
		Unblur u = new Unblur();
		String[] a = {
			  "1233321000000000123332100000000000000000000",
			  "1244422233222332334343323322232112332223321",
			  "1255523344343443545343434434343233432334432",
			  "0033303455465775633011445546454355753457753",
			  "0033303333364543533011433336333364521346542",
			  "0033303455464532445343545546454355753446542",
			  "0022202344342200234343434434343233432323221",
			  "0011101233221100123332223322232112332211111" };
		
		String[] b = {
			"1221", "1221", "1221"
		};
		
		for(String s : u.original(a)) {
			System.out.println(s);
		}
		
	}
	
}

