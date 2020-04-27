package CLONA.OAEI;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.lucene.queryparser.classic.ParseException;


public class ConceptMatcher {
	
	public ConceptMatcher(){
		
	}
	public static Concept getConceptdeomIndex(Ontology o, String s, String type) {

		if (type.equals("cl")) {
			for (int i = 0; i < o.getListClass().size(); i++) {
				if (o.getListClass().get(i).getName().equals(s))
					return (o.getListClass().get(i));
			}
		}
		if (type.equals("op")) {
			for (int i = 0; i < o.getListObjectProperty().size(); i++) {
				if (o.getListObjectProperty().get(i).getName().equals(s))
					return (o.getListObjectProperty().get(i));
			}
		}
		if (type.equals("dt")) {
			for (int i = 0; i < o.getListDataTypeProperty().size(); i++) {
				if (o.getListDataTypeProperty().get(i).getName().equals(s))
					return (o.getListDataTypeProperty().get(i));
			}
		}
                if (type.equals("all")) {
			for (int i = 0; i < o.getListClass().size(); i++) {
				if (o.getListClass().get(i).getEntity().getIRI().toString().contains(s))
					return (o.getListClass().get(i));
			}
                        for (int i = 0; i < o.getListObjectProperty().size(); i++) {
				if (o.getListObjectProperty().get(i).getEntity().getIRI().toString().contains(s))
					return (o.getListObjectProperty().get(i));
			}
                        for (int i = 0; i < o.getListDataTypeProperty().size(); i++) {
				if (o.getListDataTypeProperty().get(i).getEntity().getIRI().toString().contains(s))
					return (o.getListDataTypeProperty().get(i));
			}
		}
		return (null);
	}

	public ArrayList<Condidat> Match(Ontology o, Ontology o1,Properties p) throws IOException, ParseException{
		ArrayList<Condidat> ListCondidat = new ArrayList<Condidat>();
for (int i = 0; i < o1.getListClass().size(); i++) {

			
			ArrayList<PairInsideCondidat> result = new ArrayList<PairInsideCondidat>();
			if ((o1.getListClass().get(i).getLabel() != null)) {
				result = SearchEngine.search( PrepareToIndexing.GetReadyForIndexing(o1.getListClass().get(i)
						.getLabelTraduction()), "ListeClasseO1");
				Condidat cond = new Condidat();
                cond.setType("0");
				cond.setInstance(o1.getListClass().get(i));
				ArrayList<Concept> ListConcept = new ArrayList<Concept>();
				ArrayList<Float> Listsim = new ArrayList<Float>();
				for (int k = 0; k < result.size(); k++)
					if (result.get(k) != null) {
						ListConcept.add(getConceptdeomIndex(o, result.get(k).getname(),
								"cl"));
						Listsim.add(result.get(k).getsim());
						/*
						 * Align a = new
						 * Align(o1.getListClass().get(i).getEntity(),
						 * getEntitydeomIndex(o, result.get(k), "cl"), "1",
						 * "="); AlignementTab.add(a);
						 */
					}
				cond.setListInstance(ListConcept);
				cond.setListSimilarityVector(Listsim);
				ListCondidat.add(cond);

			}

		}
		//---------------------------------------------------------------------------------------------------//
	for (int i = 0; i < o.getListClass().size(); i++) {

			
			ArrayList<PairInsideCondidat> result = new ArrayList<PairInsideCondidat>();
			if ((o.getListClass().get(i).getLabel() != null)) {
				result = SearchEngine.search( PrepareToIndexing.GetReadyForIndexing(o.getListClass().get(i)
						.getLabelTraduction()), "ListeClasseO2");
				Condidat cond = new Condidat();
				cond.setType("1");
				cond.setInstance(o.getListClass().get(i));
				ArrayList<Concept> ListConcept = new ArrayList<Concept>();
				ArrayList<Float> simvect = new ArrayList<Float>();
				for (int k = 0; k < result.size(); k++)
					if (result.get(k) != null) {
						ListConcept.add(getConceptdeomIndex(o1, result.get(k).getname(),
								"cl"));
						simvect.add(result.get(k).getsim());
						
					}
				cond.setListInstance(ListConcept);
				cond.setListSimilarityVector(simvect);
				ListCondidat.add(cond);

			}

		}
	return(ListCondidat);
	}
}