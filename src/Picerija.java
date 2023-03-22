import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class Picerija {
	
	static int picasIzvele(ArrayList<Object>Piiicas){
		String[] rSaraksts = new String[Piiicas.size()];
		for(int i=0; i<rSaraksts.length; i++){
			rSaraksts[i]=(((EsosasPicas)Piiicas.get(i)).dabutNosaukums()+" "+((EsosasPicas)Piiicas.get(i)).dabutCenaMin());
		}
		String izveletais = (String)JOptionPane.showInputDialog(null, "Kuru izveleties?", "Izvēle", 
				JOptionPane.QUESTION_MESSAGE, null, rSaraksts, rSaraksts[0]);
		return Arrays.asList(rSaraksts).indexOf(izveletais);
	}
	

	public static void main(String[] args) {
		ArrayList<Object> menu = new ArrayList<Object>();
		ArrayList<Object> picasSaraksts = new ArrayList<Object>();
		ArrayList<Object> pasutijumsSaraksts = new ArrayList<Object>();
		
		ArrayList<String> mafijaSastav = new ArrayList<String>();
		mafijaSastav.add("Malta liellopa gaļa, Gvinejas pipari, sīpoli, konservēti peperoni pipari, konservēti sīpoli, čili pipari, mocarella, tomātu mērce, eļļas un ķipoklu mērce, oregano");
		menu.add(new EsosasPicas("Mafija", mafijaSastav, 14.39));
		
		ArrayList<String> cetrugadalaikiSastav = new ArrayList<String>();
		cetrugadalaikiSastav.add("Garneles, mīdijas, mocarella, šķiņķis, šampinjoni, tomātu mērce, oregano");
		menu.add(new EsosasPicas("Četru gadalaiku", cetrugadalaikiSastav, 15.29));
		
		ArrayList<String> amerikanuSastav = new ArrayList<String>();
		amerikanuSastav.add("Vistas gaļa 'Pulled', tomātu mērce, mocarella, BBQ mērce, rukola, ķiršu tomāti, cietais siers, saulē kaltēti tomāti, eļļas un ķipoklu mērce, oregano");
		menu.add(new EsosasPicas("Amerikāņu", amerikanuSastav, 13.99));
		
		String izvele;
		int izveletaisIndekss=0;
		String[] darbibas = {"Pasūtiet picu", "Aizvērt programmu"};
		String[] atbildes = {"Nē", "Jā"};
		do {
			izvele = (String) JOptionPane.showInputDialog(null, "Izvēlies darbību", "Izvēle", JOptionPane.QUESTION_MESSAGE, null, darbibas, darbibas[1]);
			izveletaisIndekss = Arrays.asList(darbibas).indexOf(izvele);
			switch(izveletaisIndekss){
			case 0:
				int kuraPica = picasIzvele(menu);
				String izmers = (String) JOptionPane.showInputDialog("Izmers: ");
				picasSaraksts.add(new Pica(((EsosasPicas)menu.get(kuraPica)).dabutNosaukums(), ((EsosasPicas)menu.get(kuraPica)).dabutSastav(), ((EsosasPicas)menu.get(kuraPica)).dabutCenaMin(), izmers));
				String izvele1 = (String)JOptionPane.showInputDialog(null, "Pasūtījums līdzņemšanai?", "Izvele", JOptionPane.QUESTION_MESSAGE, null, atbildes, atbildes[0]);
				int izveletaisIndekss1 = Arrays.asList(atbildes).indexOf(izvele1);
				boolean irPiegade = (izveletaisIndekss1==0) ? false : true;
				if(irPiegade==true) {
					String vards = (String) JOptionPane.showInputDialog("Vārds: ");
					String adrese = (String) JOptionPane.showInputDialog("Adrese: ");
					int talrunis = Integer.parseInt(JOptionPane.showInputDialog("Tālrūnis: "));
					pasutijumsSaraksts.add(new Lidznemsana((Pica)picasSaraksts.get(kuraPica), irPiegade, adrese, talrunis, vards));
				}else {
					pasutijumsSaraksts.add(new Pasutijums((Pica)picasSaraksts.get(kuraPica), irPiegade));
					JOptionPane.showMessageDialog(null, "Gala cena: "+((Pica)picasSaraksts.get(kuraPica)).dabutGalaCena());
				}
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Programma apturēta", "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
				break;
			}
		}while(izveletaisIndekss!=1);
		
		
	}

}
