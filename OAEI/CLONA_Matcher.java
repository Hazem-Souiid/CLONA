package CLONA.OAEI;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

public class CLONA_Matcher {
	
	
private Ontology o;
Properties prop = new Properties();
public CLONA_Matcher(){
	try {
		this.prop.load(new FileInputStream("config.properties"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("File config.properties not found!!!");
	}

}

public URL Match(URL path1 , URL path2) throws Exception{
	System.out.println("************************************************************************************");
	System.out.println("************************************************************************************");
	System.out.println("***************************|                         |******************************");
	System.out.println("************************|        CLONA Process         |****************************");
	System.out.println("***************************|                         |******************************");
	System.out.println("************************************************************************************");
	System.out.println("************************************************************************************");
	System.out.println();
	System.out.println();
	//Path to the config file
	
	


		
		
	    o = new Ontology();
		Ontology o1 = new Ontology();
		System.out.println("***************************                         ********************************");
		System.out.println("************************   Parsing and Preprocessing   *****************************");
		System.out.println("***************************                         ********************************");
		System.out.println();
		System.out.println();
		Parser1 parser = new Parser1();
		o = parser.parse(path1);
		
		if(o==null){
			
			
			System.out.println("Internal Problem when preprocessing");
		}
		else{
			o1 = parser.parse(path2);
			if(o1==null){
				  
				System.out.println("Internal Problem when preprocessing");
			  }
			else{
			System.out.println("***************************                         ********************************");
		System.out.println("************************         Translation           *****************************");
		System.out.println("***************************                         ********************************");
		System.out.println();
		System.out.println();
		System.out.println(o1.getLanguage().toString()+o.getLanguage().toString());
		if(!o.getLanguage().toString().equals(o1.getLanguage().toString()))
		{o1.Ontology_Translate();
		o.Ontology_Translate();}
		else
		{
			o1.Ontology_Translate_GeneralContext();
		 o.Ontology_Translate_GeneralContext();}
	
		
		
		System.out.println("***************************                         ********************************");
		System.out.println("************************         Indexation            *****************************");
		System.out.println("***************************                         ********************************");
		System.out.println();
		System.out.println();
		Indexer c = new Indexer();
		Indexer c1 = new Indexer();
		Indexer c2 = new Indexer();

		c.rebuildIndexes(o.getListClass(), "ListeClasseO1");
		c1.rebuildIndexes(o.getListDataTypeProperty(), "ListeDataTypePropertyO1");
		c2.rebuildIndexes(o.getListObjectProperty(), "ListeObjectProperty01");

		Indexer o2c = new Indexer();
		Indexer o2c1 = new Indexer();
		Indexer o2c2 = new Indexer();

		o2c.rebuildIndexes(o1.getListClass(), "ListeClasseO2");
		o2c1.rebuildIndexes(o1.getListDataTypeProperty(), "ListeDataTypePropertyO2");
		o2c2.rebuildIndexes(o1.getListObjectProperty(), "ListeObjectProperty02");
		System.out.println("***************************                         ********************************");
		System.out.println("************   Crossing and candidate alignments lists computation   ***************");
		System.out.println("***************************                         ********************************");
		System.out.println();
		System.out.println();
		ArrayList<Condidat> ListCondidat = new ArrayList<Condidat>();
		ArrayList<Condidat> ListConceptCondidat = new ArrayList<Condidat>();
		ArrayList<Condidat> ListDataTypeCondidat = new ArrayList<Condidat>();
		ArrayList<Condidat> ListObjectPropertyCondidat = new ArrayList<Condidat>();

		ObjectPropretyMatcher opm = new ObjectPropretyMatcher();
		ListObjectPropertyCondidat = opm.Match(o, o1,prop);

		DataTypeMatcher dtm = new DataTypeMatcher();
		ListDataTypeCondidat = dtm.Match(o, o1,prop);

		ConceptMatcher cm = new ConceptMatcher();
		ListConceptCondidat = cm.Match(o, o1,prop);

		ListCondidat.addAll(ListConceptCondidat);
		ListCondidat.addAll(ListDataTypeCondidat);
		ListCondidat.addAll(ListObjectPropertyCondidat);
		System.out.println("***************************                         ********************************");
		System.out.println("************************   Filtering and Thresholding   ****************************");
		System.out.println("***************************                         ********************************");
		System.out.println();
		System.out.println();
		AlignGenerator AG = new AlignGenerator();
	    ArrayList<Align> beforerepair = AG.Generate(o,o1,ListCondidat);
	    Align2Rander.ColToRdf(path1.toString(), path2.toString(),beforerepair, "alignment.rdf");
	    
	    System.out.println("***************************                         ********************************");
		System.out.println("************************       Alignment Generation        *************************");
		System.out.println("***************************                         ********************************");
		}
		
		}
		return(new URL("file:alignment.rdf"));
	
}
public static void main(String[] args) throws Exception {
	
	
	
	
	
	CLONA_Matcher insMat = new CLONA_Matcher();
    @SuppressWarnings("unused")
	URL alignFile = insMat.Match(new URL("file:/home/hazem/workspace/InSem/dataset-2015-testing/ont/nl/confOf-nl.owl"),
			new URL ("file:/home/hazem/workspace/InSem/dataset-2015-testing/ont/ru/confOf-ru.owl"));
	
    
   
}
}
