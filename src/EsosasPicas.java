import java.util.ArrayList;

public class EsosasPicas {
	String nosaukums;
	double cenaMin;
	ArrayList<String> sastav;
	public EsosasPicas(String nosaukums, ArrayList<String> sastav, double cenaMin) {
		this.nosaukums = nosaukums;
		this.sastav = sastav;
		this.cenaMin = cenaMin;
	}
	public String dabutNosaukums() {
		return nosaukums;
	}
	public ArrayList<String> dabutSastav() {
		return sastav;
	}
	public double dabutCenaMin() {
		return cenaMin;
	}

}
