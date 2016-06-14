package Projet;

import Projet.IHM.Fenetre;
import Projet.Metier.*;

public class Controleur
{
	private final String[] tabDifficultee = {"Debutant", "Intermediaire", "Avance", "Expert"};
	private Plateau pl;
	private Fenetre fenetre;
	private Niveau niveau;
	private Partie partie;
	
	public Controleur()
	{
		//niveau aleatoire
		int niveau = (int)(Math.random()*15+1);
		int difficultee = (int)(Math.random()*4);
		
		Niveau iN = new Niveau(0, tabDifficultee[0]);
		pl = new Plateau(iN.getPiece());
		
		this.fenetre = new Fenetre(this);
		
		System.out.println(niveau + "  " + difficultee);
		System.out.println(pl.toString());
	}
	
	public Plateau getPlateau(){return this.pl;}
	
	public static void main(String[] a)
	{
		new Controleur();
	}
	
	public void augmenterNiveau()
	{
		
	}
	public void diminuerNiveau()
	{
		
	}
	public void creerPartie(String nom)
	{
		
	}
	public void chargerPartie(String nom)
	{
		
	}
}
