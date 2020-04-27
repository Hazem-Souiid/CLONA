package CLONA.OAEI;

import java.util.ArrayList;


public class Condidat {

	    Concept concept;
	    ArrayList<Concept> ListConcept = new ArrayList<Concept>() ;
	    ArrayList<Float> SimilarityVector = new ArrayList<Float>() ;
	    String type ="";
	    
	    public Condidat() {
	    }

	        public Concept getInstance() {
	        return this.concept;
	    }

	    public void setInstance(Concept name) {
	        this.concept = name;
	    }
	    
	    public void setType(String c) {
	        this.type = c;
	    }
	    public String getType() {
	        return(this.type);
	    }
	    public ArrayList<Concept> getListInstance() {
	        return this.ListConcept;
	    }

	    public void setListInstance(ArrayList<Concept> name) {
	        this.ListConcept = name;
	    }
	    public ArrayList<Float> getListSimilarityVector() {
	        return this.SimilarityVector;
	    }

	    public void setListSimilarityVector(ArrayList<Float> name) {
	        this.SimilarityVector = name;
	    }
	    
	    public void tri ()
	    {
	    	int limit = this.SimilarityVector.size();
	    	boolean permutation = true;
	    	float s1; 
	    	float s2;
	    	Concept c1;
	    	Concept c2;

	    	while (permutation)
	    	{
	    		permutation = false;
	    		for (int i=0 ; i<limit-1 ; i++)
	    		{
	    			s1 = this.SimilarityVector.get(i);
	    			s2 = this.SimilarityVector.get(i+1);
	    			c1=this.ListConcept.get(i);
	    			c2=this.ListConcept.get(i+1);

	    			if (s1 > s2)
	    			{   this.SimilarityVector.set(i+1, s1);
	    			   this.SimilarityVector.set(i, s2);
	    				this.ListConcept.set(i+1, c1);
	    				this.ListConcept.set(i, c2);
	    				permutation = true;
	    			}
	    		}
	    	}
	    	
	    }
	    
	    
	    public void print(){
	    	System.out.println("______________________________________");
	    	System.out.println("Concept:  "+this.concept.Name);
	    	System.out.println("listConceptCondidat");
	    	for(int i=0;i<this.ListConcept.size();i++)
	    		System.out.print(ListConcept.get(i).getName()+"|");
	     	System.out.println("SimilarityVector");
	    	for(int i=0;i<this.SimilarityVector.size();i++)
	    		System.out.print(SimilarityVector.get(i)+"|");
	    }

}
