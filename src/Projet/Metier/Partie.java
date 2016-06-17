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
	private int nbCoups;
	
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
		this.niveauxMaximum.put("Debutant", 1);
		this.niveauxMaximum.put("Intermediaire", 0);
		this.niveauxMaximum.put("Avance", 0);
		this.niveauxMaximum.put("Expert", 0);
		this.nbCoups = 0;
		this.ctrl.setNiveau(new Niveau(1, "Debutant"));
		this.enregistrerPartie();
	}
	
	public void chargerPartie()
	{
		FileReader fr;
		try 
		{
			if(!this.fichier.exists()) this.initFichier();
			fr = new FileReader(this.fichier);
			Scanner sc = new Scanner(fr);
			
			String difficultee = sc.next();	
			int niveau = sc.nextInt();
			
			this.ctrl.setNiveau(new Niveau(niveau, difficultee));
			
			for(int cpt  = 0;cpt < 4;cpt++)
				this.niveauxMaximum.put(sc.next(), sc.nextInt());
			sc.next(); //Correspond à la chaîne "Score"
			this.nbCoups = sc.nextInt();
			sc.close();
		}catch(IOException e){e.printStackTrace();}
	}
	
	public void enregistrerPartie()
	{
		FileWriter fw;
		if ( !this.ctrl.getNiveau().getDifficulte().equals("Edite"))
			if(this.niveauxMaximum.get(this.ctrl.getNiveau().getDifficulte()) <= this.ctrl.getNiveau().getNumNiveau())
			{
				// enregistre le niveau maximum de la difficulté courante
				this.niveauxMaximum.put(this.ctrl.getNiveau().getDifficulte(), this.ctrl.getNiveau().getNumNiveau());
				//Teste si le joueur a resolu plus de 50% des defis de sa difficultée, pour debloquer la difficultée suivante
				if(this.ctrl.getNiveau().getNumDifficulte() != 4 && this.niveauxMaximum.get(this.ctrl.getDifficultes()[this.ctrl.getNiveau().getNumDifficulte()]) == 0
				   && this.niveauxMaximum.get(this.ctrl.getNiveau().getDifficulte())-1 >= (int)Math.ceil((double)this.ctrl.getDifficulteCourante().size()/2) ) 
					this.niveauxMaximum.put(this.ctrl.getDifficultes()[this.ctrl.getNiveau().getNumDifficulte()], 1);
			}
			
			try {
				fw = new FileWriter(this.fichier);
				//Sauvegarde la progression dans le fichier
				fw.write(this.ctrl.getNiveau().getDifficulte()+ " " +this.ctrl.getNiveau().getNumNiveau() + "\n"); //niveau courant
				for(String s : this.niveauxMaximum.keySet())                                                       //niveaux maximum
					fw.write(s + "\t"+this.niveauxMaximum.get(s) + "\n");
				fw.write("Score total\t"+this.nbCoups);
				fw.close();
			}catch (IOException e){e.printStackTrace();}
	}
	
	public void initFichier()
	{
		FileWriter fw;
		try {
			this.fichier.createNewFile();
		} catch (IOException e) {System.out.print("Erreur d'accès au fichier "+this.fichier.getName());}
	}
	
	/**
	 * Cette méthode détermine si le joueur accèder au défi dont la difficulté et le niveau est passé en paramètre.
	 **/
	public boolean peutJouerNiveau(String difficulte, int niveau) 
	{
		if ( !difficulte.equals("Edite"))
			return niveau <= this.niveauxMaximum.get(difficulte);
		return true;
		
	}
	
	public void setNbCoups(int nbCoups){this.nbCoups = nbCoups;}
	public int getNbCoups(){return this.nbCoups;}
}
