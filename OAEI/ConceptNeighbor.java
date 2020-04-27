package CLONA.OAEI;


import java.util.ArrayList;




public class ConceptNeighbor {

	
    ArrayList<Concept> ListNeighbor = new ArrayList<Concept>();
   
    public ConceptNeighbor() {
    }

   
    public ArrayList<Concept> getListNeighbor() {
        return this.ListNeighbor;
    }

    public void setListNeighbor(ArrayList<Concept> List) {
        this.ListNeighbor = List;
    }
    public void translate (ArrayList<Concept> tab){
    	
    	for(int i=0;i<this.ListNeighbor.size();i++)
    		for (int j=0;i<tab.size();j++)
    			if(this.ListNeighbor.get(i).getEntity().equals(tab.get(j).getEntity().toString())){
    				this.ListNeighbor.get(i).setLabelTraduction(tab.get(j).getLabelTraduction());
    			}
    	
    }
    public void print()
    {System.out.println("--------<Neighbor>--------");
    	for(int i=0;i<this.ListNeighbor.size();i++)
    		System.out.println(this.ListNeighbor.get(i));
    	
    	System.out.println("--------</Neighbor>--------");
    	System.out.println();
    }
   }

