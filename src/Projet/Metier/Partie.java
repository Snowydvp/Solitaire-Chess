package Projet.Metier;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import Projet.Controleur;

public class Partie
{
	private Controleur ctrl;
	private File fichier;
	private HashMap<String, Integer> niveauxMaximum;
	
	public Partie(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.niveauxMaximum = new HashMap<>(4);
		this.fichier = new File("Sauvegarde/progression.chess");
		this.chargerPartie();
	}
	
	public void nouvellePartie()
	{
		this.fichier.delete();
		try 
		{
			this.fichier.createNewFile();
		}catch (IOException e){e.printStackTrace();}
		//Modifier pr mettre a jour le hashmap lors d'une nouvelle partie
		this.ctrl.setNiveau(new Niveau(1, "Debutant"));
		this.enregistrerPartie();
	}
	
	public void chargerPartie()
	{
		FileReader fr;
		try 
		{
			fr = new FileReader(this.fichier);
			Scanner sc = new Scanner(fr);
			
			String difficultee = sc.next();	
			int niveau = sc.nextInt();
			
			this.ctrl.setNiveau(new Niveau(niveau, difficultee));
			
			for(int cpt  = 0;cpt < 4;cpt++)
				this.niveauxMaximum.put(sc.next(), sc.nextInt());

			sc.close();
		}catch(IOException e){e.printStackTrace();}
	}
	
	public void enregistrerPartie()
	{
		FileWriter fw;
		this.niveauxMaximum.put(this.ctrl.getNiveau().getDifficulte(), this.ctrl.getNiveau().getNumNiveau());
		try {
			fw = new FileWriter(this.fichier);
			
			fw.write(this.ctrl.getNiveau().getDifficulte()+ " " +this.ctrl.getNiveau().getNumNiveau() + "\n"); //niveau courant
			for(String s : this.niveauxMaximum.keySet())
				fw.write(s + "\t"+this.niveauxMaximum.get(s) + "\n");
			fw.close();
		}catch (IOException e){e.printStackTrace();}
	}
	
	public boolean peutJouerNiveau(String difficulte, int niveau)
	{
		return niveau <= this.niveauxMaximum.get(difficulte);
	}
	
}
