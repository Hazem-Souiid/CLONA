package CLONA.OAEI;
import java.util.ArrayList;

public class AlignGenerator {

	public AlignGenerator(){
		
	}
	
	public ArrayList<Align> Generate (Ontology o1,Ontology o2,ArrayList<Condidat> listCondidat)
	{int owlaxiomD = Math.abs(o1.getAxiomCount()-o2.getAxiomCount());
	int owlaxiomS = o1.getAxiomCount()+o2.getAxiomCount();
		ArrayList<Align> resultat = new ArrayList<Align>();
		
/*	if	(owlaxiomD<10){
		for (int i = 0; i < listCondidat.size(); i++) {
			if (listCondidat.get(i) != null) {
				ArrayList<Concept> firstRm = new ArrayList<Concept>();
				for (int j = 0; j < listCondidat.get(i).getListInstance().size(); j++) {
					if (listCondidat.get(i).getListInstance().get(j) != null) {
		              if(!listCondidat.get(i).getType().equals(listCondidat.get(i).getListInstance().get(j)))
		            	  firstRm.add(listCondidat.get(i).getListInstance().get(j));
					}
					listCondidat.get(i).ListConcept.removeAll(firstRm);
				}
			}
		}
	}
		
*/	
		
		for (int i = 0; i < listCondidat.size(); i++) {
			if (listCondidat.get(i) != null) {
				for (int j = 0; j < listCondidat.get(i).getListInstance().size(); j++) {
					if (listCondidat.get(i).getListInstance().get(j) != null) {

						Align a = new Align();
				
						if (listCondidat.get(i).getType().equals("0")) {
							if(listCondidat.get(i).getInstance().getDT_neighbor().equals(listCondidat.get(i).getListInstance().get(j).getDT_neighbor()))
							{a.setE1(listCondidat.get(i).getInstance().getEntity());
							a.setE2(listCondidat.get(i).getListInstance().get(j).getEntity());
							a.setSim((listCondidat.get(i).getListSimilarityVector().get(j)));
							resultat.add(a);}
						} else {
							if(listCondidat.get(i).getInstance().getDT_neighbor().equals(listCondidat.get(i).getListInstance().get(j).getDT_neighbor()))
							{
							a.setE2(listCondidat.get(i).getInstance().getEntity());
							a.setE1(listCondidat.get(i).getListInstance().get(j).getEntity());
							a.setSim((listCondidat.get(i).getListSimilarityVector().get(j)));
							resultat.add(a);
							}
						}
					}
				}
			}
		}
		
	ArrayList<Align> align1 = new ArrayList<Align>();
		ArrayList<Align> align2 = new ArrayList<Align>();
		ArrayList<Align> listtoremove = new ArrayList<Align>();
		align1 = resultat;
		/////////////////// filtre 1/////////////////////
		for (int x = 0; x < resultat.size(); x++) {
			for (int j = 0; j < align1.size(); j++) {
				if (resultat.get(x).getE1().toString().equals(align1.get(j).getE1().toString()))
					if (resultat.get(x).getSim() > align1.get(j).getSim()) {
						listtoremove.add(align1.get(j));

					}

			}
		}
		/////////////// filtre 2 ///////////////////

		for (int x = 0; x < resultat.size(); x++) {
			for (int j = 0; j < align1.size() - 1; j++) {
				if (resultat.get(x).getE2().toString().equals(align1.get(j).getE2().toString()))
					if (resultat.get(x).getSim() > align1.get(j).getSim()) {
						listtoremove.add(align1.get(j));
					}

			}
		}
        	
		//////////////// filtre 3 //////////////////
		
		align1.removeAll(listtoremove);
			
        	float Seuil =0;
        	
        	if ((owlaxiomD+owlaxiomS)>1300)
        		Seuil=(float) 3.6;
        	else{
        	if ((owlaxiomD+owlaxiomS)<900)
        		Seuil=(float) 2.9;
        	else
        		Seuil=(float) 2.6;
        	}
        	if(owlaxiomD<10){
        		Seuil=0;
        		
        	
        	}
		    for (int k = 0; k < align1.size(); k++) {

			if (align1.get(k).getSim() >Seuil) {
				align2.add(align1.get(k));
			}
		}
		
			return (align2);
	}


}
