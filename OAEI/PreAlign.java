package CLONA.OAEI;

public class PreAlign {

	public Concept E1;
	public Concept E2;
	public Float Sim;
	public String Rel;

	public PreAlign() {
	}

	public PreAlign(Concept E1, Concept E2, Float Sim,
			String Rel) {
		this.E1 = E1;
		this.E2 = E2;
		this.Sim = Sim;
		this.Rel = Rel;
	}

	

	public Concept getE1() {
		return this.E1;
	}

	public void setE1(Concept name) {
		this.E1 = name;
	}

	public Concept getE2() {
		return this.E2;
	}

	public void setE2(Concept Label) {
		this.E2 = Label;
	}

	public Float getSim() {
		return this.Sim;
	}

	public void setSim(Float Sim) {
		this.Sim = Sim;
	}

	public String getRel() {
		return this.Rel;
	}

	public void setRel(String name) {
		this.Rel = name;
	}
	
	public void print(){
		System.out.println("-----------------------------------------");
		System.out.println(this.E1.getLabelTraduction());
		System.out.println(this.E2.getLabelTraduction());
		System.out.println(this.Sim);
	}

}
