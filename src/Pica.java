import java.util.ArrayList;

public class Pica  extends EsosasPicas{
	
	private double galaCena;
	String izmers;
	
	public Pica(String nosaukums, ArrayList<String> sastav, double cenaMin, String izmers) {
		super(nosaukums, sastav, cenaMin);
		this.izmers = izmers;
	}
	public String dabutIzmers() {
		return izmers;
	}
	public double dabutGalaCena() {
		galaCena = dabutCenaMin()+5;
		return galaCena;
	}
	public String info() {
		return "\nPica: "+dabutNosaukums()+"\nIzmērs: "+dabutIzmers();
	}

}
