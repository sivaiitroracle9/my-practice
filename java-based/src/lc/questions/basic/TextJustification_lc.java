package leetcode.questions.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TextJustification_lc {

	public static void main(String[] args) {
		TextJustification_lc lc = new TextJustification_lc();
		/*
		 * String[] words = { "This", "is", "an", "example", "of", "text",
		 * "justification." }; int max_width = 16;
		 */
		String[] words = { "Listen","to","many,","speak","to","a","few." };
		int max_width = 6;
		for (String line : lc.fullJustify(words, max_width)) {
			System.out.println(line);
		}
	}

	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> lines = new ArrayList<String>();
		if (maxWidth == 0) {
			lines.add("");
			return lines;
		}

		char[] CSPACE = new char[maxWidth];
		Arrays.fill(CSPACE, ' ');
		String SPACE = new String(CSPACE);

		List<List<String>> linewords = new LinkedList<List<String>>();
		int len = 0;
		for (int i = 0; i < words.length;) {
			int newlen = 0;
			if (len > 0) {
				newlen = len + 1 + words[i].length();
			} else {
				newlen = len + words[i].length();
			}

			if (newlen <= maxWidth) {
				if (linewords.isEmpty())
					linewords.add(new LinkedList<String>());
				len = newlen;
				linewords.get(linewords.size() - 1).add(words[i]);
				i++;
			} else {
				len = 0;
				linewords.add(new LinkedList<String>());
			}
		}

		int l;
		for (l = 0; l < linewords.size() - 1; l++) {
			List<String> linewords1 = linewords.get(l);
			int sizeOfWords = 0;
			for (String word : linewords1) {
				sizeOfWords += word.length();
			}

			String line = "";
			int spaces = (maxWidth - sizeOfWords);
			int spacefillers = linewords1.size() - 1;
			if (spacefillers > 0) {
				line = linewords1.get(linewords1.size() - 1);
				for (int i = linewords1.size() - 2; i >= 0; i--) {
					int space = spaces / spacefillers;
					line = linewords1.get(i) + SPACE.substring(0, space) + line;
					spaces -= space;
					spacefillers -= 1;
				}
			} else {
				line = linewords1.get(0);
				if (line.length() < maxWidth) {
					line = line + SPACE.substring(0, maxWidth - line.length());
				}
			}
			lines.add(line);
		}

		String line = "";
		int m;
		for (m = 0; m < linewords.get(l).size() - 1; m++) {
			line = line + linewords.get(l).get(m) + " ";
		}
		line = line + linewords.get(l).get(m);
		if (line.length() <= maxWidth) {
			line = line + SPACE.substring(0, maxWidth - line.length());
		}
		lines.add(line);
		return lines;
	}
}
