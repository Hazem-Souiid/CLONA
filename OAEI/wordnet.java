package CLONA.OAEI;

import java.util.ArrayList;
import java.util.Arrays;

import rita.RiWordNet;

/**
 * Displays word forms and definitions for synsets containing the word form
 * specified on the command line. To use this application, specify the word form
 * that you wish to view synsets for, as in the following example which displays
 * all synsets containing the word form "airplane": <br>
 * java TestJAWS airplane
 */
public class wordnet {

	/**
	 * Main entry point. The command-line arguments are concatenated together
	 * (separated by spaces) and used as the word form to look up.
	 */
	static ArrayList<String> synset(String obj) {
		if (obj == null) {
			return (null);
		} else {
			String word = obj.toString();
			if (word != null) {
				String[] synonyms3 = null;

				ArrayList<String> syns = new ArrayList<>();
				RiWordNet wordnet = new RiWordNet(("WordNet-3.0/dict/"));
				if (wordnet.isNoun(word)) {
					synonyms3 = wordnet.getAllSynsets(word, "n");
					if (synonyms3 != null) {
						syns.addAll(Arrays.asList(synonyms3));
						return (syns);
					}
				}
				if (wordnet.isVerb(word)) {
					synonyms3 = wordnet.getAllSynsets(word, "v");
					if (synonyms3 != null) {
						syns.addAll(Arrays.asList(synonyms3));
						return (syns);
					}
				}
				if (wordnet.isAdjective(word)) {
					synonyms3 = wordnet.getAllSynsets(word, "a");
					if (synonyms3 != null) {
						syns.addAll(Arrays.asList(synonyms3));
						return (syns);
					}
				}
				if (wordnet.isAdverb(word)) {
					synonyms3 = wordnet.getAllSynsets(word, "r");
					if (synonyms3 != null) {
						syns.addAll(Arrays.asList(synonyms3));
						return (syns);
					}
				}
				return (null);
			} else {
				return (null);
			}
		}
	}
	public static void main(String[] args) throws Exception {
		
		System.out.println(synset("car").toString());
	}
	
}