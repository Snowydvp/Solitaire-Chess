package Projet;

import Projet.IHM.Fenetre;
import Projet.Metier.InitialisateurNiveau;
import Projet.Metier.Plateau;

public class Controleur
{
	private final String[] tabDifficulter = {"Debutant", "Intermediaire", "Avance", "Expert"};
	private Plateau pl;
	private Fenetre fenetre;
	
	public Controleur()
	{
		//niveau aleatoire
		int niveau = (int)(Math.random()*15+1);
		int difficulter = (int)(Math.random()*4);
		
		InitialisateurNiveau iN = new InitialisateurNiveau(0, tabDifficulter[0]);
		pl = new Plateau(iN.getPiece());
		
		this.fenetre= new Fenetre(this);
		
		System.out.println(niveau + "  " + difficulter);
		System.out.println(pl.toString());
	}
	
	public Plateau getPlateau(){return this.pl;}
	
	public static void main(String[] a)
	{
		new Controleur();
	}
}
