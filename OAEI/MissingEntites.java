package CLONA.OAEI;
import java.util.ArrayList;

public class MissingEntites {
	public MissingEntites() {

	}

	public ArrayList<PreAlign> TryToFindMissingEntites(ArrayList<PreAlign> tab) {
		ArrayList<PreAlign> Missing = new ArrayList<PreAlign>();

		for (int i = 0; i < tab.size(); i++) {
			Concept ElementENCOURE1 =tab.get(i).getE1();
			Concept ElementENCOURE2 =tab.get(i).getE2();
			
			
			for (int j = 0; j < ElementENCOURE1.getListNeighbor().size(); j++) {
				 Concept best = GetBest(ElementENCOURE1.getListNeighbor().get(j),ElementENCOURE2.getListNeighbor());
				 if(best!=null){
				 PreAlign pa = new PreAlign(ElementENCOURE1.getListNeighbor().get(j), best, (float) 1.0, "=");
				
				Missing.add(pa);}}
		}	
			
			
		
		return (Missing);
	}

	private Concept GetBest(Concept c, ArrayList<Concept> tab) {
		double max =ISub.stringSimilarity(c.getLabelTraduction(),tab.get(0).getLabelTraduction());
		Concept best = tab.get(0);
		
		for (int k = 1; k < tab.size(); k++){
			System.out.println("Comparer: "+ISub.stringSimilarity(c.getLabelTraduction(),tab.get(k).getLabelTraduction())+
					" ? "+max+" "+(ISub.stringSimilarity(c.getLabelTraduction(),tab.get(k).getLabelTraduction()) > max));
			if (ISub.stringSimilarity(c.getLabelTraduction(),tab.get(k).getLabelTraduction()) > max) {
				
				max = ISub.stringSimilarity(c.getLabelTraduction(),tab.get(k).getLabelTraduction());
				best = tab.get(k);
			}}
		if(max>=0.7)
		return best;
		else return null;
	}
}
