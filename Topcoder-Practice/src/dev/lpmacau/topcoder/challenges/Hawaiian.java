package dev.lpmacau.topcoder.challenges;

import java.util.ArrayList;
import java.util.List;

public class Hawaiian {

	public String[] getWords(String sentence) {

		List<Character> alphabet = new ArrayList<Character>();

		alphabet.add('a');
		alphabet.add('e');
		alphabet.add('i');
		alphabet.add('o');
		alphabet.add('u');

		alphabet.add('h');
		alphabet.add('k');
		alphabet.add('l');
		alphabet.add('m');
		alphabet.add('n');
		alphabet.add('p');
		alphabet.add('w');

		String[] split = sentence.split(" ");
		String[] resW = new String[split.length];
		String w;
		int wL;
		boolean discard = false;
		int n = 0;

		for (int i = 0; i < split.length; i++) {
			w = split[i];
			wL = w.length();

			for (int j = 0; j < wL && !discard; j++) {
				// Found one ilegal letter, break
				if (!alphabet.contains(Character.toLowerCase(w.charAt(j)))) {
					discard = true;
					break;
				}
			}
			if (!discard) {
				resW[n] = w;
				n++;
			}

			discard = false;
		}

		String[] nRes = new String[n];
		for (int i = 0; i < n; i++) {
			nRes[i] = resW[i];
		}

		return nRes;

	}
}
