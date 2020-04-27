package CLONA.OAEI;



/**
 *
 * @author shazem
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.ClassicAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class SearchEngine {

	private IndexSearcher searcher = null;
	private QueryParser parser = null;

	/**
	 * Creates a new instance of SearchEngine
	 */
	public SearchEngine(String sdirectory) throws IOException {
		searcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(new File(sdirectory))));
		parser = new QueryParser("content", new ClassicAnalyzer());
	}

	public TopDocs performSearch(String queryString, int n) throws IOException, ParseException {
        
		Query query = parser.parse(queryString);
		return searcher.search(query, n);
	}

	public Document getDocument(int docId) throws IOException {
		return searcher.doc(docId);
	}

	public static ArrayList<PairInsideCondidat> search(String text, String sdirectory) throws IOException, ParseException {
		ArrayList<PairInsideCondidat> best = new ArrayList<PairInsideCondidat>();
	 
		String Text_To_Search=PreTr.CleanString(text);
		if(Text_To_Search.length()>1){
		SearchEngine se = new SearchEngine(sdirectory);
		TopDocs topDocs = se.performSearch(Text_To_Search.toString(), 4);
		

		ScoreDoc[] hits = topDocs.scoreDocs;
		if (hits.length>0){
	        float score = hits[0].score ;
		for (int i = 0; i < hits.length; i++) {
			Document doc = se.getDocument(hits[i].doc);
			
        	
			if(hits[i].score>=score&&hits[i].score>0.0&&!doc.get("text").toLowerCase().contains("thing")&&!doc.get("text").toLowerCase().contains("null")){
        	
			PairInsideCondidat p = new PairInsideCondidat();
			Float sDoc=hits[i].score;
			
			p.setname(doc.get("id"));
			p.setsim(sDoc);
			best.add(p);
			}
		}
		return (best);
	}}
		return(best);}}


