
public class Lidznemsana{
	String vards, adrese, talrunis;
	public Lidznemsana(String adrese, String talrunis, String vards){
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
	public String dabutTalr() {
		return talrunis;
	}
	public String info() {
		return "\nVards: "+dabutVards()+"\nAdrese: "+dabutAdrese()+"\nTalrunis: "+dabutTalr();
	}

}
