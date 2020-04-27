package CLONA.OAEI;


import com.memetix.mst.MicrosoftTranslatorAPI;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
 
public class bing {
	public static String translate (String word,Ontology o) throws Exception{
		String res =Translate.execute(word, o.getLanguage(),Language.ENGLISH);
		if(res.contains("ArgumentOutOfRangeException"))
		return(word);
		else
			return(res.replaceAll("[^A-Za-z0-9]", " "));
	}
	
  public static void main(String[] args) throws Exception {
	  //Replace client_id and client_secret with your own.  
	  MicrosoftTranslatorAPI.setClientId("user106_app");
	    MicrosoftTranslatorAPI.setClientSecret("Bv2+kRJ0lFnR9+KSaWP+jBYYjLHDgqBEI3uhqBLj9Bc=");
	
    // Translate an english string to spanish
    
    String englishString = "membre de commité";
    
    Language L = Language.FRENCH;
	String res =Translate.execute(englishString, L,Language.ENGLISH);
	System.out.println("language= "+L.toString());
 
    System.out.println("Original english phrase: " + englishString );
    System.out.println("Translated spanish phrase: " + res +"  LEN " +res.length());
   // System.out.println (spanishTranslation.contains("ArgumentOutOfRangeException"));
  }
}
 
