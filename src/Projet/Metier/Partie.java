package Projet.Metier;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Partie {
	
	private Niveau niveauCourant;
	private File fichier;
	private int[] niveauxMaximum;
	
	public Partie()
	{
		this.niveauxMaximum = new int[4];
		this.fichier = new File("Sauvegarde/progression.chess");
		this.chargerPartie();
	}
	
	public void nouvellePartie()
	{
		FileWriter fw;
		this.fichier.delete();
		try {
			this.fichier.createNewFile();
		} catch (IOException e) {e.printStackTrace();}
		
		this.niveauCourant = new Niveau(0, "Debutant");
		this.enregistrerPartie();
	}
	
	public void chargerPartie()
	{
		FileReader fr;
		try {
			fr = new FileReader(this.fichier);
			Scanner sc = new Scanner(fr);
			String difficultee = sc.next();
			int niveau = sc.nextInt();
			this.niveauCourant = new Niveau(niveau, difficultee);
			System.out.println("charge niveau");
			for(int cpt  = 0;cpt < 4;cpt++)
			{
				sc.next();
				this.niveauxMaximum[cpt] = sc.nextInt();
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void enregistrerPartie()
	{
		FileWriter fw;
		try {
			fw = new FileWriter(this.fichier);
			
			fw.write(this.niveauCourant.getDifficulte()+" "  +this.niveauCourant.getNumNiveau()); //niveau courant
			fw.write("Debutant "                             +this.niveauxMaximum[0]+"\n");       //niveau max
			fw.write("Intermediaire "                        +this.niveauxMaximum[1]+"\n");       //pour chaque
			fw.write("Avance "                               +this.niveauxMaximum[2]+"\n");       //difficulte
			fw.write("Expert "                               +this.niveauxMaximum[3]+"\n");	
			fw.close();
		}catch (IOException e){e.printStackTrace();}
		this.niveauCourant = new Niveau(0, "Debutant");
	}
	
	public boolean peutJouerNiveau(int difficulte, int niveau)
	{
		return niveau<=this.niveauxMaximum[difficulte]; //ergarderles autres dfficultes
	}
	
	public Niveau getNiveauCourant(){return this.niveauCourant;}
	public void setNiveau(Niveau n){this.niveauCourant=n;}
}
