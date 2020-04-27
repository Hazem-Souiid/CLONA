package CLONA.OAEI;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;



public class Parser1 {
	
	
	
 public Parser1(){
	 
 }
	
 
       /* public  org.semanticweb.owlapi.model.OWLOntology GetOWLOntologyFromString(URL path) throws UnknownHostException, OWLOntologyCreationException,
			URISyntaxException, FileNotFoundException, UnsupportedEncodingException {
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		//org.semanticweb.owlapi.model.OWLOntology onto1 = manager.loadOntologyFromPhysicalURI(URI.create(path));
		org.semanticweb.owlapi.model.OWLOntology onto1 = manager.loadOntologyFromOntologyDocument(FileManager.get().open(path.getFile()));           
                        return(onto1);
        }*/
   
	public Ontology parse(URL path) throws Exception  {
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		org.semanticweb.owlapi.model.OWLOntology onto1 = null;
		if(path.toString().startsWith("file:"))
		{
		
		try {
			onto1 = manager.loadOntologyFromOntologyDocument(new File(path.getFile()));
		} catch (OWLOntologyCreationException e) {
			System.out.println("Cannot Parse This Ontology Format !! ");
		} 
		}
		
		else {
			IRI i = IRI.create(path);
			onto1 = manager.loadOntology(i);
		}
		ArrayList<Concept> ListClass = new ArrayList<Concept>();
		ArrayList<Concept> ListDataTypeProperty = new ArrayList<Concept>();
		ArrayList<Concept> ListObjectProperty = new ArrayList<Concept>();
		ArrayList<Concept> ListInstance = new ArrayList<Concept>();

		Ontology o = new Ontology();
		@SuppressWarnings("unused")
		int i =0;
		o.setAxiomCount(onto1.getAxiomCount());
		for (org.semanticweb.owlapi.model.OWLEntity entity : onto1.getSignature()) {
		  
			if (entity.isOWLClass()) {
				
				ArrayList<Concept>ListNeighbor = new ArrayList<Concept>();
				String Label = "";
				IRI cIRI = entity.getIRI();
				for (OWLAnnotationAssertionAxiom a : onto1.getAnnotationAssertionAxioms(cIRI)) {
					if (a.getProperty().isLabel()) {
						if (a.getValue() instanceof OWLLiteral) {
							OWLLiteral val = (OWLLiteral) a.getValue();
							Label += val.getLiteral() + " ";

						}
					}
				}
                Concept c = new Concept();
				
				c.setName(entity.getIRI().getFragment());
				c.setLabel(Label);
				c.setListNeighbor(ListNeighbor);
				c.setEntity(entity);
                @SuppressWarnings("unused")
				String Label2="";
                Label2+=String.valueOf(ListNeighbor.size());
                //c.seDT_neighbor(Label2);
				c.setType("cl");
				
						ListClass.add(c);
				if(ListClass.size()>400)
					return null;
			}
		
		
			if (entity.isOWLDataProperty()) {
				
			
					
				 Iterator<OWLDataRange> list = entity.asOWLDataProperty().getRanges(onto1).iterator();
				 String Label2 = "";
				 while (list.hasNext()){
					
					  Label2+= list.next().toString()+" ";
					
				 }
				
				
				String Label = "";
				IRI cIRI = entity.getIRI();
				for (OWLAnnotationAssertionAxiom a : onto1.getAnnotationAssertionAxioms(cIRI)) {
					if (a.getProperty().isLabel()) {
						if (a.getValue() instanceof OWLLiteral) {
							OWLLiteral val = (OWLLiteral) a.getValue();
							Label += val.getLiteral() + " ";

						}
					}
				}
				Concept c = new Concept();
				c.setName(entity.getIRI().getFragment());
				c.setLabel(Label);
				c.setEntity(entity);
				c.seDT_neighbor(Label2);
				c.setType("dt");
				ListDataTypeProperty.add(c);
			}
			if (entity.isOWLObjectProperty()) {
				
				String Label = "";
				String Label2 ="" ;
				IRI cIRI = entity.getIRI();
				
				
				if (entity.asOWLObjectProperty().isFunctional(onto1))
					Label2+="Functional ";
				
				if (entity.asOWLObjectProperty().isInverseFunctional(onto1))
					Label2+="InverseFunctional ";
				
				
				
			
				
				
				for (OWLAnnotationAssertionAxiom a : onto1.getAnnotationAssertionAxioms(cIRI)) {
					if (a.getProperty().isLabel()) {
						if (a.getValue() instanceof OWLLiteral) {
							OWLLiteral val = (OWLLiteral) a.getValue();
							Label += val.getLiteral() + " ";

						}
					}
				}
				Concept c = new Concept();
				c.setName(entity.getIRI().getFragment());
				c.setLabel(Label);
				c.setEntity(entity);
				c.seDT_neighbor(Label2);
				c.setType("op");
				ListObjectProperty.add(c);
			}
			
			if (entity.isOWLNamedIndividual()) {
				i++;
			
			
				Concept c = new Concept();
				c.setName(entity.getIRI().getFragment());
				c.setLabel(entity.getIRI().getFragment());
				c.setEntity(entity);
				c.setType("in");
				ListInstance.add(c);
			}
			
			
		}
		

		o.setListClass(ListClass);
		o.setListDataTypeProperty(ListDataTypeProperty);
		o.setListObjectProperty(ListObjectProperty);
		o.setListInstance(ListInstance);
		o.setLanguage();

	
		
		return (o);
	}

	
	

	

	
}