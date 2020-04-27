package CLONA.OAEI;



/**
 *
 * @author shazem
 */
public class Align {

	public org.semanticweb.owlapi.model.OWLEntity E1;
	public org.semanticweb.owlapi.model.OWLEntity E2;
	public Float Sim;
	public String Rel;

	public Align() {
	}

	public Align(org.semanticweb.owlapi.model.OWLEntity E1, org.semanticweb.owlapi.model.OWLEntity E2, Float Sim,
			String Rel) {
		this.E1 = E1;
		this.E2 = E2;
		this.Sim = Sim;
		this.Rel = Rel;
	}

	public org.semanticweb.owlapi.model.OWLEntity getE1() {
		return this.E1;
	}

	public void setE1(org.semanticweb.owlapi.model.OWLEntity name) {
		this.E1 = name;
	}

	public org.semanticweb.owlapi.model.OWLEntity getE2() {
		return this.E2;
	}

	public void setE2(org.semanticweb.owlapi.model.OWLEntity Label) {
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
		System.out.println(this.E1);
		System.out.println(this.E2);
		System.out.println(this.Sim);
	}

}
