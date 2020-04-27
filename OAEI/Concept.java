package CLONA.OAEI;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shazem
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shazem
 */
public class Concept {

	String Name;
	String Label;
	String LabelTraduction;
	String dt_neighbor="";
	ArrayList<Concept> ListNeighbor;

	org.semanticweb.owlapi.model.OWLEntity entity;

	String Type;

	public Concept() {
	}

	public Concept(String Name, String Label, String LabelTraduction, String Type) {
		this.Name = Name;
		this.Label = Label;
		this.LabelTraduction = LabelTraduction;
		this.Type = Type;
	}

	public String getName() {
		return this.Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getLabel() {
		return this.Label;
	}

	public void seDT_neighbor(String Label) {
		this.dt_neighbor = Label;
	}
	public String getDT_neighbor() {
		
		return this.dt_neighbor;
	}

	public void setLabel(String Label) {
		this.Label = Label;
	}
	public org.semanticweb.owlapi.model.OWLEntity getEntity() {
		return this.entity;
	}

	public void setEntity(org.semanticweb.owlapi.model.OWLEntity e) {
		this.entity = e;
	}

	public String getLabelTraduction() {
		return this.LabelTraduction;
	}

	public void setLabelTraduction(String LabelTraduction) {
		this.LabelTraduction = LabelTraduction;
	}

	public String getType() {
		return this.Type;
	}

	public void setType(String name) {
		this.Type = name;
	}

	public ArrayList<Concept> getListNeighbor() {
		return this.ListNeighbor;
	}

	public void setListNeighbor(ArrayList<Concept> List) {
		this.ListNeighbor = List;
	}

	public void NeighborTranslate(ArrayList<Concept> tab) {

		for (int i = 0; i < this.ListNeighbor.size(); i++) {
			for (int j = 0; j < tab.size(); j++) {

				
				
				
				if (this.ListNeighbor.get(i) != null){
				//	System.out.println("Seek: "+this.ListNeighbor.get(i).getName()+" => "+tab.get(j).getName());
					if (this.ListNeighbor.get(i).getName().equals(tab.get(j).getName())) {
						
						this.ListNeighbor.set(i, tab.get(j));
					}}
			}
		}
	}

	public void Neighborprint() {
		System.out.println("--------<Neighbor>--------");
		for (int i = 0; i < this.ListNeighbor.size(); i++)
			System.out.println("Neighbor "+this.ListNeighbor.get(i).getLabel()+" ==> "+this.ListNeighbor.get(i).getLabelTraduction());

		System.out.println("--------</Neighbor>--------");
		System.out.println();
	}

}
