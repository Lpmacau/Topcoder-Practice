package dev.lpmacau.topcoder.challenges;

public class Substitute {
	public static void main(String args[]) {

		String kA = "TRADINGFEW";
		String cA = "LGXWEV";
		String kB = "ABCDEFGHIJ";
		String cB = "XJ";
		String kC = "CRYSTALBUM";
		String cC = "MMA";

		System.out.println(getValue(kA,cA));
		System.out.println(getValue(kB,cB));
		System.out.println(getValue(kC,cC));
	}

	private static int getValue(String key, String code) {
		int codeL = code.length();
		StringBuilder codePos = new StringBuilder();
		
		for(int i = 0; i<codeL;i++) {
			if(key.contains(""+code.charAt(i))) {
				if((key.indexOf(code.charAt(i))+1) == 10) codePos.append(0+"");
				else codePos.append(key.indexOf(code.charAt(i))+1);
			}
		}
		
		return Integer.parseInt(codePos.toString());
	}
}
