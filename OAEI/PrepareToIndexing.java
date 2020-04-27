package CLONA.OAEI;


import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.util.Version;
import org.tartarus.snowball.ext.PorterStemmer;

import rita.RiWordNet;

public class PrepareToIndexing {
	public PrepareToIndexing(){
		
	}
	static ArrayList<String> synset(String word) {
           
            if (word != null) {
                String[] synonyms3 = null;

                ArrayList<String> syns = new ArrayList<>();
                RiWordNet wordnet = new RiWordNet("/home/hazem/workspace/InSem/WordNet-3.0/dict/");
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


	@SuppressWarnings("resource")
	public String remove(String string) throws IOException {

		@SuppressWarnings("deprecation")
		TokenStream tokenStream = new StandardTokenizer(Version.LUCENE_4_10_2, new StringReader(string));
		tokenStream.reset();
		;
		WordDelimiterFilter a;
		List<String> q = new LinkedList<String>();
		@SuppressWarnings("deprecation")
		CharArraySet b = new CharArraySet(Version.LUCENE_4_10_2, q, true);
		a = new WordDelimiterFilter(tokenStream, WordDelimiterFilter.GENERATE_WORD_PARTS, b);

		StringBuilder sb;
		sb = new StringBuilder();
		while (a.incrementToken()) {
			if (sb.length() > 0) {
				sb.append(" ");
			}
			sb.append(a.getAttributeImplsIterator().next());
		}
		tokenStream.close();
		return sb.toString();
	}

	public static String stemmer(String word) {
		PorterStemmer obj = new PorterStemmer();
		obj.setCurrent(word);
		obj.stem();
		return obj.getCurrent();
	}

	@SuppressWarnings("deprecation")
	public ArrayList<String> removeStopWords(String string) throws IOException {
		ArrayList<String> res = new ArrayList<String>();
		TokenStream tokenStream = new StandardTokenizer(Version.LUCENE_4_10_2, new StringReader(string));
		tokenStream = new StopFilter(Version.LUCENE_4_10_2, tokenStream, StandardAnalyzer.STOP_WORDS_SET);
		tokenStream.reset();
		CharTermAttribute token = tokenStream.getAttribute(CharTermAttribute.class);
		while (tokenStream.incrementToken()) {

			res.add((token.toString()));
		}
		tokenStream.close();
		return res;
	}

	public static ArrayList<String> CleanString(String word) throws IOException {
		PrepareToIndexing stopwords = new PrepareToIndexing();

		return (stopwords.removeStopWords(stopwords.remove(word)));
	}

	
	public static String GetReadyForIndexing(String word) throws IOException {
		ArrayList<String> tab1 = CleanString(word);
		ArrayList<String> tab2 = new ArrayList<String>();
		tab2.add(word);
		
		if(tab1!=null)
		for(int i=0;i<tab1.size();i++)
			if(synset(tab1.get(i).toLowerCase())!=null)
			tab2.addAll(synset(tab1.get(i).toLowerCase()));
		return(tab2.toString().replace(",", "").replace("]", "").replace("[", ""));
	}

	
	
	public static void main(String args[]) throws IOException, ParseException {

		
			System.out.println(GetReadyForIndexing("activity"));

	}
}