package dev.lpmacau.topcoder;
import dev.lpmacau.topcoder.challenges.Hawaiian;

public class EntryPoint {
	public static void main(String args[]) {
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

	
}

