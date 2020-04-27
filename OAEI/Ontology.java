package CLONA.OAEI;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */






import com.memetix.mst.MicrosoftTranslatorAPI;
import com.memetix.mst.language.Language;

import java.util.ArrayList;

/**
 *
 * @author shazem
 */
public class Ontology {

    Language Language;
    int axiomCount=0;
    ArrayList<Concept> ListClass = new ArrayList<Concept>();
    ArrayList<Concept> ListObjectProperty = new ArrayList<Concept>();
    ArrayList<Concept> ListDataTypeProperty = new ArrayList<Concept>();
    ArrayList<Concept> ListInstance = new ArrayList<Concept>();
    public Ontology() throws Exception {
    	
    	
    }

    public Language getLanguage() {
    	
        return this.Language;
    	}

    public void setLanguage() throws Exception{
    this.Language=Bing_Detector.getLanguage(this.ListClass);
    }

    
    public int getAxiomCount() {
        return this.axiomCount;
    }

    public void setAxiomCount(int lg) {
        this.axiomCount = lg;
    }

    
    public ArrayList<Concept> getListClass() {
        return this.ListClass;
    }

    public void setListClass(ArrayList<Concept> List) {
        this.ListClass = List;
    }

    public ArrayList<Concept> getListObjectProperty() {
        return this.ListObjectProperty;
    }

    public void setListObjectProperty(ArrayList<Concept> List) {
        this.ListObjectProperty = List;
    }

    public ArrayList<Concept> getListDataTypeProperty() {
        return this.ListDataTypeProperty;
    }

    public void setListDataTypeProperty(ArrayList<Concept> List) {
        this.ListDataTypeProperty = List;
    }
    public ArrayList<Concept> getListInstance() {
        return this.ListInstance;
    }

    public void setListInstance(ArrayList<Concept> List) {
        this.ListInstance = List;
    }
    
    public int getPrmNumbre(){
    	return(this.getListClass().size()+this.getListDataTypeProperty()
    	.size()+this.getListObjectProperty().size());
    }

    public void Print() {
        System.out.println("----------------------Ontology Language------------------------");
        System.out.println(this.Language);
        System.out.println("----------------------List Class------------------------");
        
        for (int i = 0; i < this.ListClass.size(); i++) {
            System.out.println("Name: " + this.ListClass.get(i).getName());
            
            System.out.println("Label: " + this.ListClass.get(i).getLabel());
            
            	System.out.println("Label Traduction: " + this.ListClass.get(i).getLabelTraduction());
            System.out.println("Type: " + this.ListClass.get(i).getType());
            System.out.println("Liste Neighbor");
            this.ListClass.get(i).Neighborprint();
        }

        System.out.println("----------------------List Object Property------------------------");
        for (int i = 0; i < this.ListObjectProperty.size(); i++) {
            System.out.println("Name: " + this.ListObjectProperty.get(i).getName());
           
            System.out.println("Label: " + this.ListObjectProperty.get(i).getLabel());
            
            System.out.println("Label Traduction: " + this.ListObjectProperty.get(i).getLabelTraduction());
            System.out.println("Type: " + this.ListObjectProperty.get(i).getType());
            System.out.println();
        }

        System.out.println("----------------------List Data Property------------------------");
        for (int i = 0; i < this.ListDataTypeProperty.size(); i++) {
            System.out.println("Name: " + this.ListDataTypeProperty.get(i).getName());
        
                
            System.out.println("Label: " + this.ListDataTypeProperty.get(i).getLabel());
             System.out.println("Label Traduction: " + this.ListDataTypeProperty.get(i).getLabelTraduction());
            System.out.println("Type: " + this.ListDataTypeProperty.get(i).getType());
            System.out.println();
        }
        
        System.out.println("----------------------List Instance------------------------");
        for (int i = 0; i < this.ListInstance.size(); i++) {
            System.out.println("Name: " + this.ListInstance.get(i).getName());
        
                
            System.out.println("Label: " + this.ListInstance.get(i).getLabel());
             System.out.println("Label Traduction: " + this.ListInstance.get(i).getLabelTraduction());
            System.out.println("Type: " + this.ListInstance
            		.get(i).getType());
            System.out.println();
        }

    }
    
    public void Ontology_Translate() throws Exception {
    	  
    	    MicrosoftTranslatorAPI.setClientId("user106_app");
    	    MicrosoftTranslatorAPI.setClientSecret("Bv2+kRJ0lFnR9+KSaWP+jBYYjLHDgqBEI3uhqBLj9Bc=");
    	    Dictionary dict = new Dictionary(this.Language.toString());
    	    System.out.println("***************************                         ********************************");
			System.out.println("**************     Launching Local Translator Building Launching     ***************");
			System.out.println("***************************                         ********************************");
			System.out.println();
			System.out.println();
        for (int i = 0; i < this.ListClass.size(); i++) {
        	
            this.ListClass.get(i).setLabelTraduction(dict.translate(this.ListClass.get(i).getLabel(),this));
           
                 	
        	}
        
        
        //Translating Neighbor
        
        
     for (int i = 0; i < this.ListClass.size(); i++) {
	 this.ListClass.get(i).NeighborTranslate( this.ListClass);
            //this.ListClass.get(i).Neighborprint();
                 	
        	}
    
        for (int i = 0; i < this.ListObjectProperty.size(); i++) {
        	
                this.ListObjectProperty.get(i).setLabelTraduction(dict.translate(this.ListObjectProperty.get(i).getLabel(),this));
       //         System.out.println(this.ListObjectProperty.get(i).getLabel()+" ==> "+bing.translate(this.ListObjectProperty.get(i).getLabel()));
            	}
            	

        for (int i = 0; i < this.ListDataTypeProperty.size(); i++) {
        	
                this.ListDataTypeProperty.get(i).setLabelTraduction(dict.translate(this.ListDataTypeProperty.get(i).getLabel(),this));
           //     System.out.println(this.ListDataTypeProperty.get(i).getLabel()+" ==> "+bing.translate(this.ListDataTypeProperty.get(i).getLabel()));
            	}
    
        
    }
    	   
    
    
    
    public void Ontology_Translate_GeneralContext() throws Exception {
 	   
 	  
 	   		System.out.println("***************************                         ********************************");
			System.out.println("******************     Launching GeneralContext Translator      ********************");
			System.out.println("***************************                         ********************************");
			System.out.println();
			System.out.println();
     for (int i = 0; i < this.ListClass.size(); i++) {
     	
         this.ListClass.get(i).setLabelTraduction(this.ListClass.get(i).getName());
        
              	
     	}
     
     
     //Translating Neighbor
     
     
  for (int i = 0; i < this.ListClass.size(); i++) {
	 this.ListClass.get(i).NeighborTranslate( this.ListClass);
         //this.ListClass.get(i).Neighborprint();
              	
     	}
 
     for (int i = 0; i < this.ListObjectProperty.size(); i++) {
     	
             this.ListObjectProperty.get(i).setLabelTraduction(this.ListObjectProperty.get(i).getName());
    //         System.out.println(this.ListObjectProperty.get(i).getLabel()+" ==> "+bing.translate(this.ListObjectProperty.get(i).getLabel()));
         	}
         	

     for (int i = 0; i < this.ListDataTypeProperty.size(); i++) {
     	
             this.ListDataTypeProperty.get(i).setLabelTraduction(this.ListDataTypeProperty.get(i).getName());
        //     System.out.println(this.ListDataTypeProperty.get(i).getLabel()+" ==> "+bing.translate(this.ListDataTypeProperty.get(i).getLabel()));
         	}
 
     
 }
 }	   

