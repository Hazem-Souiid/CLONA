package CLONA.OAEI;


import java.util.ArrayList;

public class RelationNeighbor {
    String Name; 
	ArrayList<Concept> ListDomains = new ArrayList<Concept>();
    ArrayList<Concept> ListRanges = new ArrayList<Concept>();
    

    public RelationNeighbor() {
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String lg) {
        this.Name = lg;
    }
    public ArrayList<Concept> getListDomains() {
        return this.ListDomains;
    }

    public void setListDomains(ArrayList<Concept> List) {
        this.ListDomains = List;
    }

    public ArrayList<Concept> getListRanges() {
        return this.ListRanges;
    }

    public void setLisRanges(ArrayList<Concept> List) {
        this.ListRanges = List;
    }

}
