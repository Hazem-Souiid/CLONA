package CLONA.OAEI;



import fr.inrialpes.exmo.align.impl.BasicParameters;
import fr.inrialpes.exmo.align.impl.eval.PRecEvaluator;
import fr.inrialpes.exmo.align.parser.AlignmentParser;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Enumeration;
import org.semanticweb.owl.align.Alignment;
import org.semanticweb.owl.align.AlignmentException;
import org.semanticweb.owl.align.Cell;
import org.semanticweb.owl.align.Evaluator;

/**
 *
 * @author shazem
 */
@SuppressWarnings("deprecation")
public class Evaluate {
	public static void Evale(String al, String ref) throws AlignmentException, MalformedURLException {
		AlignmentParser aparser = new AlignmentParser(0);

		Alignment reference = aparser.parse((new File(ref)).toURI());
		Alignment match = aparser.parse((new File(al)).toURI());
		Evaluator evaluator = new PRecEvaluator(reference, match);
		evaluator.eval(new BasicParameters());
		System.out.println(evaluator.getResults());

	}

	public static void Test(String ref) throws AlignmentException, MalformedURLException {
		AlignmentParser aparser = new AlignmentParser(0);

		Alignment reference = aparser.parse((new File(ref)).toURI());
		Enumeration<Cell> elements = reference.getElements();
		Cell el = null;
		while (elements.hasMoreElements()) {
			el = elements.nextElement();
			System.out.println("E1 :  " + el.getObject1().toString());
			System.out.println("E2 :  " + el.getObject2().toString());
		}

	}

	public static void main(String[] args) throws AlignmentException, MalformedURLException {

		Evale("alignment.rdf", "edas-edas-en-fr.rdf");
	}
}
