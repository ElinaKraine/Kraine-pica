import java.text.DecimalFormat;
import java.util.ArrayList;

public class Pica  extends EsosasPicas{
	
	String izmers, dzer;
	boolean irPiegade;
	int indekss, picuSk;
	
	DecimalFormat dF = new DecimalFormat("##.##");
	
	public Pica(String nosaukums, ArrayList<String> sastav, double cenaMin, String izmers, boolean irPiegade, int indekss, int picuSk, String dzer) {
		super(nosaukums, sastav, cenaMin);
		this.izmers = izmers;
		this.irPiegade = irPiegade;
		this.indekss = indekss;
		this.picuSk = picuSk;
		this.dzer = dzer;
	}
	public int dabutIndekss() {
		return indekss;
	}
	public int dabutPiceSk() {
		return picuSk;
	}
	public String dabutIzmers() {
		return izmers;
	}
	public String dabutDzer() {
		return dzer;
	}
	public boolean dabutPiegade() {
		return irPiegade;
	}
	public double cena() {
		double cena=dabutCenaMin();
		if (izmers=="30cm") {
            cena += 5.0;
        }
		if (izmers=="50cm") {
            cena += 10.0;
        }
        if(picuSk>1) {
        	cena*=picuSk;
        }
        if (dabutPiegade()) {
            cena += 10.0;
        }
        switch (dzer) {
        case "Coca-Cola": case "Kafija": case "Pepsi": case "Sprite": 
            cena += 1.99;
            break;
        case "Nav":
            break;
        }
        dF.format(cena);
		return cena;
	}
	public String info() {
		return "Nr."+dabutIndekss()+"\nPica ("+dabutPiceSk()+") : "+dabutNosaukums()+"\nIzmērs: "+dabutIzmers()+"\nDzērieni: "+dabutDzer()+"\nPiegāde: "+((dabutPiegade()) ? "Ir\n" : "Nav\n")+"Cena: "+dF.format(cena())+"€";
	}

}