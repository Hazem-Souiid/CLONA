package CLONA.OAEI;


import java.util.ArrayList;

import com.memetix.mst.MicrosoftTranslatorAPI;
import com.memetix.mst.detect.Detect;



/**
 * DetectLanguageExample
 * 
 * Calls Microsoft to determine origin language of provided text.
 * 
 * Shows how to turn the two character response code into a language and how to localize the Language name
 *
 * @author griggs.jonathan
 * @date Jun 1, 2011
 * @since 0.3 June 1, 2011
 */
public class Bing_Detector {
  
	public static com.memetix.mst.language.Language getLanguage(ArrayList<Concept> listClass) throws Exception {
		MicrosoftTranslatorAPI.setClientId("Marouen_Kachroudi");
    	MicrosoftTranslatorAPI.setClientSecret("BTb6tqKPm1tnsdUIEVXoQwIRobcv4voPuIWYqugu+/M=");
        
		String string_to_detect = "";
		
	
		for(int k=0;k<listClass.size();k++){
			if(listClass.get(k).getLabel().length()>4)
			string_to_detect+=listClass.get(k).getLabel()+" ";
			else
				string_to_detect+=listClass.get(k).getName()+" ";
				
		}	
		return(Detect.execute(string_to_detect));
		
		
		
		
	}
}
