package CLONA.OAEI;




import com.hp.hpl.jena.rdf.model.Statement;

import java.io.IOException;
import java.io.Serializable.*;
import java.io.StringReader;
import java.lang.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.miscellaneous.SingleTokenTokenStream;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilter;
import org.apache.lucene.analysis.snowball.*;
import org.apache.lucene.analysis.snowball.SnowballAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.IOUtils;
import org.apache.lucene.util.Version;
import org.tartarus.snowball.ext.PorterStemmer;


public class PreTr {

    
    public String remove(String string) throws IOException 
    {
        
        TokenStream tokenStream = new StandardTokenizer(Version.LUCENE_4_10_2,new StringReader(string));
        tokenStream.reset();;
        WordDelimiterFilter a;
        List<String> q=new LinkedList();
        CharArraySet b=new CharArraySet(Version.LUCENE_4_10_2,q,true);
        a=new WordDelimiterFilter(tokenStream,WordDelimiterFilter.GENERATE_WORD_PARTS,b);
        
        
      

        StringBuilder sb;
        sb = new StringBuilder();
        while (a.incrementToken()) 
        {
            if (sb.length() > 0) 
            {
                sb.append(" ");
            }
            sb.append(a.getAttributeImplsIterator().next());
        }
        tokenStream.close();
        return sb.toString();
    }
    
    

    
    public String removeStopWords(String string) throws IOException 
    {   String res = "";
        StandardAnalyzer ana = new StandardAnalyzer(Version.LUCENE_4_10_2);
        TokenStream tokenStream = new StandardTokenizer(Version.LUCENE_4_10_2,new StringReader(string));
        StringBuilder sb = new StringBuilder();
        tokenStream = new StopFilter(Version.LUCENE_4_10_2, tokenStream, ana.STOP_WORDS_SET);
        tokenStream.reset();
        CharTermAttribute token = tokenStream.getAttribute(CharTermAttribute.class);
        while (tokenStream.incrementToken()) 
        {
            
            res+=" "+token.toString();
        }
        tokenStream.close();
        return res;
    }
    
  
    public static String  CleanString(String word) throws IOException{
       PreTr stopwords = new PreTr();
       
        
     return(stopwords.removeStopWords(stopwords.remove(word)));   
    }
    
    
    
    public static void main(String args[]) throws IOException, ParseException
    {                             
        
          ArrayList<String> res = new ArrayList<String>();
          String text = "Sevening__jogging%%%9";
          
     /*     res=CleanString(text);
          for(int i=0; i<res.size();i++)
        	  System.out.println(res.get(i));

       */   


          
          
    }
}