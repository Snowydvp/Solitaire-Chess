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
		//int niveau = (int)(Math.random()*15+1);
		//int difficultee = (int)(Math.random()*4);
		
		this.niveau = new Niveau(0, tabDifficultee[0]);
		pl = new Plateau(this.niveau.getPiece());
		
		this.fenetre = new Fenetre(this);
		
		//System.out.println(niveau.getNumNiveau() + "  " + niveau.getDifficultee());
		//System.out.println(pl.toString());
	}
	
	public Plateau getPlateau(){return this.pl;}
	
	public static void main(String[] a)
	{
		new Controleur();
	}
	
	public void augmenterNiveau()
	{
		if ( this.niveau.getNumNiveau() < 15)
			this.niveau = new Niveau(this.niveau.getNumNiveau()+1, this.niveau.getDifficulte());
		else if ( !this.niveau.getDifficulte().equals("Expert"))
			this.niveau = new Niveau(1, this.tabDifficultee[this.augmenterDifficulte(this.niveau.getDifficulte())]);
			
		pl = new Plateau(this.niveau.getPiece());	
	}
	
	public void diminuerNiveau()
	{
		if ( this.niveau.getNumNiveau() > 1)
			this.niveau = new Niveau(this.niveau.getNumNiveau()-1, this.niveau.getDifficulte());
		else if ( !this.niveau.getDifficulte().equals("Debutant"))
			this.niveau = new Niveau(15, this.tabDifficultee[this.diminuerDifficulte(this.niveau.getDifficulte())]);
		
		pl = new Plateau(this.niveau.getPiece());
	}
	
	public void creerPartie(String nom)
	{
		
	}
	
	public void chargerPartie(String nom)
	{
		
	}
	
	public void rejouer()
	{
		this.niveau = new Niveau(this.niveau.getNumNiveau(), this.niveau.getDifficulte());
		pl = new Plateau(this.niveau.getPiece());
	}
	
	private int diminuerDifficulte(String d) 
	{
		int index = -1;
		for ( int i = 0; i < tabDifficultee.length; i++)
		{
			if(tabDifficultee[i].equalsIgnoreCase(d))
				index = i;
		}
		
		if ( index - 1 < 0)
			return 0;
		else
			return index-1;
	}
	
	private int augmenterDifficulte(String d) 
	{
		int index = -1;
		for ( int i = 0; i < tabDifficultee.length; i++)
		{
			if(tabDifficultee[i].equalsIgnoreCase(d))
				index = i;
		}
		
		if ( index + 1 > 3)
			return index;
		else
			return index+1;
	}
	
	public void victoireNiveauCourant() {
		int nbPiece = 0;
		
		for ( int i = 0; i < 4; i++)
		{
			for ( int j = 0; j < 4; j++ )
			{
				if ( this.pl.getPlateau()[i][j] != null )
					nbPiece++;
			}
		}
		
		if ( nbPiece == 1)
			this.augmenterNiveau();
	}
	
	public Niveau getNiveau() { return this.niveau; }
}
