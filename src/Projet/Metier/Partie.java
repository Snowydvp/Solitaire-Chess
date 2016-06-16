package Projet.Metier;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Partie {
	
	private Niveau niveauCourant;
	private File fichier;
	private int[] tabDifficultee;
	
	public Partie()
	{
		tabDifficultee = new int[4];
		this.fichier = new File("Sauvegarde/progression.chess");
	}
	
	public void nouvellePartie()
	{
		FileWriter fw;
		this.fichier.delete();
		try {
			this.fichier.createNewFile();
			fw = new FileWriter(this.fichier);
			fw.write("Debutant 0\n");       //niveau Courant
			fw.write("Debutant 0\n");       //niveau max
			fw.write("Intermediaire 0\n");  //pour chaque
			fw.write("Avance 0\n");         //difficulte
			fw.write("Expert 0\n");
			fw.close();
		}catch (IOException e){e.printStackTrace();}
		this.niveauCourant = new Niveau(0, "Debutant");
	}

	
	public void chargerPartie()
	{
		try {
			FileReader fr = new FileReader(this.fichier);
			Scanner sc = new Scanner(fr);
			String difficultee = sc.next();
			int niveau = sc.nextInt();
			this.niveauCourant = new Niveau(niveau, difficultee);
			
			for(int cpt  = 0;cpt < 4;cpt++)
			{
				sc.next();
				this.tabDifficultee[cpt] = sc.nextInt();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean peutJouerNiveau(int difficulte, int niveau)
	{
		return niveau<=tabDifficultee[difficulte];
	}
}
