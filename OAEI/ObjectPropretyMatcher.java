package CLONA.OAEI;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.lucene.queryparser.classic.ParseException;

public class ObjectPropretyMatcher {

	
	
	public ArrayList<Condidat> Match(Ontology o, Ontology o1,Properties p) throws IOException, ParseException{
		ArrayList<Condidat> ListCondidat = new ArrayList<Condidat>();
		 for (int k = 0; k < o1.getListObjectProperty().size(); k++) {
				ArrayList<PairInsideCondidat> result = new ArrayList<PairInsideCondidat>();
				if ((o1.getListObjectProperty().get(k).getLabel() != null)) {
					result = SearchEngine.search( PrepareToIndexing.GetReadyForIndexing(o1.getListObjectProperty().get(k)
							.getLabelTraduction()), "ListeObjectProperty01");
					Condidat cond = new Condidat();
					cond.setType("0");
					cond.setInstance(o1.getListObjectProperty().get(k));
					ArrayList<Concept> ListConcept = new ArrayList<Concept>();
					ArrayList<Float> simvect = new ArrayList<Float>();
					for (int b = 0; b < result.size(); b++)
						if (result.get(b) != null) {
							ListConcept.add(getConceptdeomIndex(o, result.get(b).getname(),
									"op"));
							simvect.add(result.get(b).getsim());
							
						}
					cond.setListInstance(ListConcept);
					cond.setListSimilarityVector(simvect);
					ListCondidat.add(cond);
				}
			}
			   //-----------------------------------------------------------------------------------------//
			   for (int k = 0; k < o.getListObjectProperty().size(); k++) {
					ArrayList<PairInsideCondidat> result = new ArrayList<PairInsideCondidat>();
					if ((o.getListObjectProperty().get(k).getLabel() != null)) {
						result = SearchEngine.search( PrepareToIndexing.GetReadyForIndexing(o.getListObjectProperty().get(k)
								.getLabelTraduction()), "ListeObjectProperty02");
						Condidat cond = new Condidat();
						cond.setType("1");
						cond.setInstance(o.getListObjectProperty().get(k));
						ArrayList<Concept> ListConcept = new ArrayList<Concept>();
						ArrayList<Float> simvect = new ArrayList<Float>();
						for (int b = 0; b < result.size(); b++)
							if (result.get(b) != null) {
								ListConcept.add(getConceptdeomIndex(o1, result.get(b).getname(),
										"op"));
								simvect.add(result.get(b).getsim());
							}
						cond.setListInstance(ListConcept);
						cond.setListSimilarityVector(simvect);
						ListCondidat.add(cond);
					}
				}
	return(ListCondidat);
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
		return (null);
	}
	
}
