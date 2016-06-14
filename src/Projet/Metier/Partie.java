package Projet.Metier;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Partie {
	
	private Niveau niveauCourant;
	private int[] tabNiveaux;
	
	public Partie(String nom)
	{
		tabNiveaux = new int[4];
		try {
			File f = new File("Parties/"+nom+".txt");
			FileReader fr = new FileReader(f);
			FileWriter fw = new FileWriter(f);
			if(!f.exists())
			{
				f.createNewFile();
				fw.write("Debutant 0");       //niveau Courant
				fw.write("Debutant 0");       //niveau max
				fw.write("Intermediaire 0");  //pour chaque
				fw.write("Avance 0");         //difficulte
				fw.write("Expert 0");
			}
			Scanner sc = new Scanner(fr);
			String difficultee = sc.next();
			int niveau = sc.nextInt();
			this.niveauCourant = new Niveau(niveau, difficultee);
			for(int cpt  = 0;cpt < 4;cpt++)
			{
				sc.next();
				this.tabNiveaux[cpt] = sc.nextInt();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
