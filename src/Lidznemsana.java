
public class Lidznemsana{
	String vards, adrese;
	int talrunis;
	public Lidznemsana(String adrese, int talrunis, String vards){
		this.vards = vards;
		this.adrese = adrese;
		this.talrunis = talrunis;
	}
	public String dabutAdrese() {
		return adrese;
	}
	public String dabutVards() {
		return vards;
	}
	public int dabutTalr() {
		return talrunis;
	}
	public String info() {
		return "Vards: "+dabutVards()+"\nAdrese: "+dabutAdrese()+"\nTalrunis: "+dabutTalr();
	}

}
