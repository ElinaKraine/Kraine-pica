import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;

public class Picerija extends JFrame implements ActionListener{
	
	static File darbvirsma = FileSystemView.getFileSystemView().getHomeDirectory();
	static String atrasanasVieta = darbvirsma.getAbsolutePath();
	
	DecimalFormat dF = new DecimalFormat("##.##");
	
	JButton viens, divi, tris, cetri, pieci;
	ImageIcon kartinka;
	int ind=1;
	static ArrayList<Object> menu = new ArrayList<Object>();
	static ArrayList<Object> picasSaraksts = new ArrayList<Object>();
	static ArrayList<Object> pasutijumsSaraksts = new ArrayList<Object>();
	static String[] atbildes = {"Nē", "Jā"};
	static String[] izmers = {"20cm", "30cm", "50cm"};
	static String[] dzerieni = {"Coca-Cola", "Pepsi", "Sprite", "Kafija", "Nav"};
	
	public Picerija() {
		try {
            File imageFile = new File(System.getProperty("user.home") + "/Desktop/2.jpg");
            Image originalImage = ImageIO.read(imageFile);
            Image resizedImage = originalImage.getScaledInstance(500, 200, Image.SCALE_SMOOTH);
            kartinka = new ImageIcon(resizedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel imageLabel = new JLabel(kartinka, SwingConstants.LEFT);
        viens = new JButton("Veikt pasūtījumu");
        divi = new JButton("Gaidīšanas saraksts");
        tris = new JButton("Izvēlne");
        cetri = new JButton("Apkalpot pasītījumu");
        pieci = new JButton("Close");
        
        viens.addActionListener(this);
        divi.addActionListener(this);
        tris.addActionListener(this);
        cetri.addActionListener(this);
        pieci.addActionListener(this);
        
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));
        buttonPanel.add(viens);
        buttonPanel.add(divi);
        buttonPanel.add(tris);
        buttonPanel.add(cetri);
        buttonPanel.add(pieci);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.EAST);
        
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Picērija");
        setSize(700, 300);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viens) {
        	int p;
        	String dz="";
        	int kuraPica = picasIzvele(menu);
        	ImageIcon imgC = new ImageIcon(atrasanasVieta+"\\c.png");
    		String izmers1 = (String) JOptionPane.showInputDialog(null, "Izvēlieties izmēru", "Izvēlne", JOptionPane.QUESTION_MESSAGE, imgC, izmers, izmers[2]);
    		do {
    			p = Integer.parseInt(JOptionPane.showInputDialog("Picu skaits: "));
    		}while(p<1 || p>20);
    		dz = (String) JOptionPane.showInputDialog(null, "Izvēlieties dzērienu (1.99€)", "Izvēlne", JOptionPane.QUESTION_MESSAGE, null, dzerieni, dzerieni[4]);
			String piegadeIzvele = (String)JOptionPane.showInputDialog(null, "Pasūtījums līdzņemšanai? (10€)", "Izvēlne", JOptionPane.QUESTION_MESSAGE, null, atbildes, atbildes[0]);
			int piegadeIndekss = Arrays.asList(atbildes).indexOf(piegadeIzvele);
			boolean irPiegade = (piegadeIndekss==0) ? false : true;
			if(irPiegade==true) {
				picasSaraksts.add(new Pica(((EsosasPicas)menu.get(kuraPica)).dabutNosaukums(), ((EsosasPicas)menu.get(kuraPica)).dabutSastav(), ((EsosasPicas)menu.get(kuraPica)).dabutCenaMin(), izmers1, irPiegade, ind, p, dz));
				String adrese, talrunis, vards;
				do {
					vards = (String) JOptionPane.showInputDialog("Vārds: ");
				}while(vards.length()<3);
				do {
					adrese = (String) JOptionPane.showInputDialog("Adrese: ");
				}while(adrese.length()<3);
				do {
					talrunis = (String)JOptionPane.showInputDialog("Tālrūnis: ", "+371 ");
				}while(talrunis.length()!=13);
				pasutijumsSaraksts.add(new Lidznemsana(adrese, talrunis, vards));
				izveidot(picasSaraksts, pasutijumsSaraksts);
				JOptionPane.showMessageDialog(null, "Cena: "+dF.format(((Pica) picasSaraksts.get(ind-1)).cena())+" €");
				ind++;
			}else {
				picasSaraksts.add(new Pica(((EsosasPicas)menu.get(kuraPica)).dabutNosaukums(), ((EsosasPicas)menu.get(kuraPica)).dabutSastav(), ((EsosasPicas)menu.get(kuraPica)).dabutCenaMin(), izmers1, irPiegade, ind, p, dz));
				pasutijumsSaraksts.add(new Lidznemsana(" ", " ", " "));
				izveidot(picasSaraksts, pasutijumsSaraksts);
				JOptionPane.showMessageDialog(null, "Cena: "+dF.format(((Pica) picasSaraksts.get(ind-1)).cena())+" €");
				ind++;
			}
        } else if (e.getSource() == divi) {
        	if(picasSaraksts.size()>0) {
        		nolasitFailu("saraksts.txt");
			}else {
				JOptionPane.showMessageDialog(null, "Pagaidām nav pasūtījumu", "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
			}
        } else if (e.getSource() == pieci) {
            System.exit(0);
        } else if (e.getSource() == tris) {
        	ImageIcon img = new ImageIcon(atrasanasVieta+"\\p.png");
        	ImageIcon img1 = new ImageIcon(atrasanasVieta+"\\3.jpg");
        	String[] mas = new String[menu.size()];
        	String str="";
    		for(int i=0; i<mas.length; i++){
    			mas[i]=(((EsosasPicas) menu.get(i)).dabutNosaukums()+"   "+((EsosasPicas)menu.get(i)).dabutCenaMin()+"€\n"+((EsosasPicas)menu.get(i)).dabutSastav()+"\n\n");
    			str+=mas[i];
    		}
    		JTextArea textArea1 = new JTextArea(str);
			JScrollPane scrollPane = new JScrollPane(textArea1);
			textArea1.setLineWrap(true);
			textArea1.setWrapStyleWord(true);
			scrollPane.setPreferredSize(new Dimension(700, 100));
			JOptionPane.showMessageDialog(null, scrollPane, "Pasūtījumu saraksts", JOptionPane.INFORMATION_MESSAGE, img);
        	JOptionPane.showMessageDialog(null, "1.99€\n"+dzerieni[0]+"\n"+dzerieni[1]+"\n"+dzerieni[2]+"\n"+dzerieni[3], "Izvēlne", JOptionPane.INFORMATION_MESSAGE, img1);
        } else if (e.getSource() == cetri) {
        	if(picasSaraksts.size()>0) {
        		int kura = izvele(picasSaraksts);
	        	picasSaraksts.remove(kura);
	        	pasutijumsSaraksts.remove(kura);
			}else {
	        	JOptionPane.showMessageDialog(null, "Pagaidām nav pasūtījumu", "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
			}
        }
    }
    
    static int izvele(ArrayList<Object>mas1){
		String[] rSaraksts = new String[mas1.size()];
		for(int i=0; i<rSaraksts.length; i++){
			rSaraksts[i]=("Nr."+((Pica)mas1.get(i)).dabutIndekss()+" ("+((EsosasPicas)mas1.get(i)).dabutNosaukums()+")");
		}
		String izveletais1 = (String)JOptionPane.showInputDialog(null, "Izvēlēties pasūtījumu: ", "Izvēle", JOptionPane.QUESTION_MESSAGE, null, rSaraksts, rSaraksts[0]);
		return Arrays.asList(rSaraksts).indexOf(izveletais1);
	}
	
	static int picasIzvele(ArrayList<Object>Piiicas){
		String[] rSaraksts = new String[Piiicas.size()];
		for(int i=0; i<rSaraksts.length; i++){
			rSaraksts[i]=(((EsosasPicas)Piiicas.get(i)).dabutNosaukums()+" ("+((EsosasPicas)Piiicas.get(i)).dabutCenaMin()+"€)");
		}
		String izveletais = (String)JOptionPane.showInputDialog(null, "Kāda picu vēlaties pasūtīt?", "Izvēlne", 
				JOptionPane.QUESTION_MESSAGE, null, rSaraksts, rSaraksts[0]);
		return Arrays.asList(rSaraksts).indexOf(izveletais);
	}
	
	static void izveidot(ArrayList <Object> picasSaraksts, ArrayList <Object> pasutijumsSaraksts){
		try{
			FileWriter fw = new FileWriter("saraksts.txt", false);
			PrintWriter pw = new PrintWriter(fw);
			for(int i=0; i<picasSaraksts.size(); i++){
				if(((Pica) picasSaraksts.get(i)).dabutPiegade()==true) {
					pw.println(((Pica) picasSaraksts.get(i)).info()+""+((Lidznemsana)pasutijumsSaraksts.get(i)).info()+"\n_________________________");
				}else {
					pw.println(((Pica) picasSaraksts.get(i)).info()+"\n_________________________");
				}
			}
			pw.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Radās kļūda veidojot failu!", "Kļūda", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	static void nolasitFailu(String h){
		String nolasitais="";
		try{
			FileReader fr = new FileReader(h);
			BufferedReader br = new BufferedReader(fr);
			String teksts;
			while((teksts = br.readLine())!=null){
				nolasitais += teksts+"\n";
			}
			br.close();
			JTextArea textArea = new JTextArea(nolasitais);
			JScrollPane scrollPane = new JScrollPane(textArea);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			scrollPane.setPreferredSize(new Dimension(100, 300));
			JOptionPane.showMessageDialog(null, scrollPane, "Pasūtījumu saraksts", JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Radās kļūda nolasot failu!", "Kļūda", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		try{
			FileWriter fw1 = new FileWriter("saraksts.txt", false);
			fw1.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Radās kļūda!", "Kļūda", JOptionPane.ERROR_MESSAGE);
		}
		ArrayList<String> mafijaSastav = new ArrayList<String>();
		mafijaSastav.add("Malta liellopa gaļa, Gvinejas pipari, sīpoli, konservēti sīpoli, čili pipari, mocarella, tomātu mērce, eļļas un ķipoklu mērce, oregano");
		menu.add(new EsosasPicas("Mafija", mafijaSastav, 9.39));
		
		ArrayList<String> cetrugadalaikiSastav = new ArrayList<String>();
		cetrugadalaikiSastav.add("Garneles, mīdijas, mocarella, šķiņķis, šampinjoni, tomātu mērce, oregano");
		menu.add(new EsosasPicas("Četru gadalaiku", cetrugadalaikiSastav, 6.29));
		
		ArrayList<String> amerikanuSastav = new ArrayList<String>();
		amerikanuSastav.add("Vistas gaļa 'Pulled', tomātu mērce, mocarella, BBQ mērce, rukola, ķiršu tomāti, cietais siers, saulē kaltēti tomāti, eļļas un ķipoklu mērce, oregano");
		menu.add(new EsosasPicas("Amerikāņu", amerikanuSastav, 7.69));
		
		ArrayList<String> margaritaSastav = new ArrayList<String>();
		margaritaSastav.add("Mocarella, tomātu mērce, eļļas un ķipoklu mērce, oregano");
		menu.add(new EsosasPicas("Margarita", margaritaSastav, 6.59));
		
		ArrayList<String> diavolaSastav = new ArrayList<String>();
		diavolaSastav.add("Malta liellopa gaļa, sarkanie sīpoli, Taco mērce, konservēti sīpoli, konservēti halapenjo pipari, tomātu mērce, eļļas un ķipoklu mērce, oregano");
		menu.add(new EsosasPicas("Diavola", diavolaSastav, 7.39));
		
		ArrayList<String> corizoSastav = new ArrayList<String>();
		corizoSastav.add("Čorizo desa, cepta vista, konservēta paprika, mocarella, sarkanie sīpoli, BBQ mērce, eļļas un ķipoklu mērce, oregano");
		menu.add(new EsosasPicas("Čorizo", corizoSastav, 7.39));
		
		ArrayList<String> laukuSastav = new ArrayList<String>();
		laukuSastav.add("Karsti kūpināts bekons, sīpoli, konservēti gurķi, mocarella ,tomātu mērce, eļļas un ķipoklu mērce, oregano");
		menu.add(new EsosasPicas("Lauku", laukuSastav, 6.59));
		
		ArrayList<String> capriSastav = new ArrayList<String>();
		capriSastav.add("Šķinķis, šampinjoni, konservēti gurķi, mocarella, tomātu mērce, eļļas un ķipoklu mērce, oregano");
		menu.add(new EsosasPicas("Kapri", capriSastav, 6.59));
		
		new Picerija();
	}

}
