package Projet.Metier;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle.Control;
import java.util.Scanner;

import Projet.Controleur;

public class Partie {
	
	Controleur ctrl;
	private File fichier;
	private int[] niveauxMaximum;
	
	
	public Partie(Controleur ctrl)
	{
		this.ctrl = ctrl;
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
		
		this.ctrl.setNiveau(new Niveau(0, "Debutant"));
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
			this.ctrl.setNiveau(new Niveau(niveau, difficultee));
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
			
			fw.write(this.ctrl.getNiveau().getDifficulte()+" "  +this.ctrl.getNiveau().getNumNiveau()+"\n"); //niveau courant
			fw.write("Debutant "                             +this.niveauxMaximum[0]+"\n");       //niveau max
			fw.write("Intermediaire "                        +this.niveauxMaximum[1]+"\n");       //pour chaque
			fw.write("Avance "                               +this.niveauxMaximum[2]+"\n");       //difficulte
			fw.write("Expert "                               +this.niveauxMaximum[3]+"\n");	
			fw.close();
		}catch (IOException e){e.printStackTrace();}
	}
	
	public boolean peutJouerNiveau(int difficulte, int niveau)
	{
		return niveau<=this.niveauxMaximum[difficulte]; //ergarderles autres dfficultes
	}
	
}
