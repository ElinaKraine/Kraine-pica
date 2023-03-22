
public class Pasutijums {
	Pica piemers;
	boolean irPiegade;
	public Pasutijums(Pica piemers, boolean irPiegade){
		this.piemers = piemers;
		this.irPiegade = irPiegade;
	}
	public boolean dabutPiegade(){
		return irPiegade;
	}
	public String info(){
		return "\nPiegāde: "+((dabutPiegade()) ? "Ir\n" : "Nav\n");
	}

}
