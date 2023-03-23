import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
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
	
	static void izveidot(ArrayList <Object> picasSaraksts){
		try{
			FileWriter fw1 = new FileWriter(new File("saraksts.txt"), true);
			PrintWriter pw1 = new PrintWriter(fw1);
			for(int i=0; i<picasSaraksts.size(); i++){
				pw1.println((i+1)+". pasutijums:\n"+((Pica) picasSaraksts.get(i)).info()+"\nPiegāde: Nav\n_____________");
			}
			pw1.close();
			JOptionPane.showMessageDialog(null, "IR");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Radās kļūda veidojot failu!", "Kļūda", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	static void izveidotArLidznemsana(ArrayList <Object> picasSaraksts, ArrayList <Object> pasutijumsSaraksts){
		try{
			FileWriter fw1 = new FileWriter(new File("saraksts.txt"), true);
			PrintWriter pw1 = new PrintWriter(fw1);
			for(int i=0; i<pasutijumsSaraksts.size(); i++){
				pw1.println((i+1)+". pasutijums:\n"+((Pica) picasSaraksts.get(i)).info()+"\nPiegāde: Ir\n"+((Lidznemsana) pasutijumsSaraksts.get(i)).info());
			}
			pw1.close();
			JOptionPane.showMessageDialog(null, "IR");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Radās kļūda veidojot failu!", "Kļūda", JOptionPane.ERROR_MESSAGE);
		}
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
		String[] izmers = {"20cm", "30cm", "50cm"};
		do {
			izvele = (String) JOptionPane.showInputDialog(null, "Izvēlies darbību", "Izvēle", JOptionPane.QUESTION_MESSAGE, null, darbibas, darbibas[1]);
			izveletaisIndekss = Arrays.asList(darbibas).indexOf(izvele);
			switch(izveletaisIndekss){
			case 0:
				int kuraPica = picasIzvele(menu);
				String izmers1 = (String) JOptionPane.showInputDialog(null, "Izvēlies izmēru", "Izvēle", JOptionPane.QUESTION_MESSAGE, null, izmers, izmers[2]);
				picasSaraksts.add(new Pica(((EsosasPicas)menu.get(kuraPica)).dabutNosaukums(), ((EsosasPicas)menu.get(kuraPica)).dabutSastav(), ((EsosasPicas)menu.get(kuraPica)).dabutCenaMin(), izmers1));
				String piegadeIzvele = (String)JOptionPane.showInputDialog(null, "Pasūtījums līdzņemšanai?", "Izvele", JOptionPane.QUESTION_MESSAGE, null, atbildes, atbildes[1]);
				int piegadeIndekss = Arrays.asList(atbildes).indexOf(piegadeIzvele);
				boolean irPiegade = (piegadeIndekss==0) ? false : true;
				if(irPiegade==true) {
					String vards = (String) JOptionPane.showInputDialog("Vārds: ");
					String adrese = (String) JOptionPane.showInputDialog("Adrese: ");
					int talrunis = Integer.parseInt(JOptionPane.showInputDialog("Tālrūnis: "));
					pasutijumsSaraksts.add(new Lidznemsana(adrese, talrunis, vards));
					izveidotArLidznemsana(picasSaraksts, pasutijumsSaraksts);
				}else {
					izveidot(picasSaraksts);
				}
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Programma apturēta", "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
				break;
			}
		}while(izveletaisIndekss!=1);
		
		
	}

}
