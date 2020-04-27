package CLONA.OAEI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Dictionary {
String path_To_File ="";
File dict ;
private BufferedReader reader;

public Dictionary(String Path) throws FileNotFoundException{
	
	this.path_To_File=Path;
	if(!new File("dictionary/").exists())
    {
        // Créer le dossier avec tous ses parents
        new File("dictionary/").mkdirs();

    }
	this.dict =new File("dictionary/"+Path+".txt");
	
}
	public String translate(String input,Ontology o) throws Exception{
		 if(this.dict.exists()){
			 	
				
	        reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.dict)));
	        String line;
	       
	        while ((line = reader.readLine()) != null) {
	            if(line.toLowerCase().contains(input.toLowerCase()))
	            {   System.out.println("************         Word has been finded in the Local Translator     **************");
					System.out.println();
	            	reader.close();
	            	
	            	return(line.substring(line.indexOf("\t"), line.length()));
	            }
	        }
		 }
	          	FileWriter fw = new FileWriter(this.dict,true); 
	          	System.out.println("*****************   Translating  Word with Microsoft Translator  *******************");
				System.out.println();
	          	String output = bing.translate(input,o);
	          	fw.write(input+"\t"+output);
	          	fw.write("\n");
	            fw.close();
	            
	            return(output);
	            
	            
	        }
	        
	}
	

