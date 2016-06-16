package Projet;

import java.util.ArrayList;

import Projet.IHM.Fenetre;
import Projet.Metier.*;
import Projet.Metier.Pieces.Piece;

public class Controleur
{
	private final String[] tabDifficultee = {"Debutant", "Intermediaire", "Avance", "Expert"};
	private Plateau pl;
	private Fenetre fenetre;
	private Niveau niveau;
	private Partie partie;
	private ArrayList<Plateau> alEtatPrecedent;
	
	public Controleur()
	{
		//niveau aleatoire
		//int niveau = (int)(Math.random()*15+1);
		//int difficultee = (int)(Math.random()*4);
		
		this.niveau = new Niveau(0, tabDifficultee[0]);
		pl = new Plateau(this.niveau.getPiece());
		
		alEtatPrecedent = new ArrayList<>();
		alEtatPrecedent.add(new Plateau(this.copieTableau(this.pl.getPlateau())));
		this.partie = new Partie();
		this.fenetre = new Fenetre(this);
		
		//System.out.println(niveau.getNumNiveau() + "  " + niveau.getDifficultee());
		//System.out.println(pl.toString());
	}
	
	
	public void augmenterNiveau()
	{
		if (this.niveau.getNumNiveau() < 15)
			this.niveau = new Niveau(this.niveau.getNumNiveau()+1, this.niveau.getDifficulte());
		else if ( !this.niveau.getDifficulte().equals("Expert"))
			this.niveau = new Niveau(1, this.tabDifficultee[this.augmenterDifficulte(this.niveau.getDifficulte())]);
			
		pl = new Plateau(this.niveau.getPiece());	
		
		this.alEtatPrecedent.clear();
		alEtatPrecedent.add(new Plateau(this.copieTableau(this.pl.getPlateau())));
	}
	
	public void diminuerNiveau()
	{
		if ( this.niveau.getNumNiveau() > 1)
			this.niveau = new Niveau(this.niveau.getNumNiveau()-1, this.niveau.getDifficulte());
		else if ( !this.niveau.getDifficulte().equals("Debutant"))
			this.niveau = new Niveau(15, this.tabDifficultee[this.diminuerDifficulte(this.niveau.getDifficulte())]);
		
		pl = new Plateau(this.niveau.getPiece());
		
		this.alEtatPrecedent.clear();
		alEtatPrecedent.add(new Plateau(this.copieTableau(this.pl.getPlateau())));
	}
	
	public void creerPartie()
	{
		this.partie.nouvellePartie();
	}
	
	public void chargerPartie()
	{
		this.partie.chargerPartie();
		
	}
	
	public void rejouer()
	{
		this.niveau = new Niveau(this.niveau.getNumNiveau(), this.niveau.getDifficulte());
		pl = new Plateau(this.niveau.getPiece());
		
		this.alEtatPrecedent.clear();
		alEtatPrecedent.add(new Plateau(this.copieTableau(this.pl.getPlateau())));
	}
	
	private int diminuerDifficulte(String d) 
	{
		int index = -1;
		for ( int i = 0; i < tabDifficultee.length; i++)
			if(tabDifficultee[i].equalsIgnoreCase(d))
				index = i;

		
		if ( index - 1 < 0)
			return 0;
		else
			return index-1;
 	}
	
	private int augmenterDifficulte(String d) 
	{
		int index = -1;
		for ( int i = 0; i < tabDifficultee.length; i++)
			if(tabDifficultee[i].equalsIgnoreCase(d))
				index = i;

		
		if ( index + 1 > 3)
			return index;
		else
			return index+1;
	}
	
	public boolean victoireNiveauCourant() {
		int nbPiece = 0;
		
		for ( int i = 0; i < 4; i++)
			for ( int j = 0; j < 4; j++ )
				if ( this.pl.getPlateau()[i][j] != null )
					nbPiece++;
		
		if ( nbPiece == 1)
		{
			this.augmenterNiveau();
			return true;
		}
		
		return false;
	}
	
	public void coupPrecedent()
	{
		if(this.alEtatPrecedent.size() > 1)
		{
			this.alEtatPrecedent.remove(alEtatPrecedent.size()-1);
			Piece[][] plateauPrecedent = this.alEtatPrecedent.get(alEtatPrecedent.size()-1).getPlateau();
			this.pl = new Plateau(this.copieTableau(plateauPrecedent), this.pl.getPiecesCapturees()); //il faut recopier la valeur de plateuPrecedent
			
			for(int i = 0; i< alEtatPrecedent.size(); i++)
				System.out.println(alEtatPrecedent.get(i).hashCode());
			System.out.println("-------------------");
		}
	}
	
	public void sauvegardeCoup()
	{
		this.alEtatPrecedent.add(new Plateau(this.copieTableau(this.pl.getPlateau())));

		for(int i = 0; i< alEtatPrecedent.size(); i++)
			System.out.println(alEtatPrecedent.get(i).hashCode());
		
		System.out.println("-------------------");

	}
	
	public Piece[][] copieTableau(Piece[][] or)
	{
		Piece tabPiece[][] = new Piece[4][4];
		
		for(int i = 0; i<tabPiece.length;i++)
			for(int j = 0; j<tabPiece.length; j++) {
				if(or[i][j]!=null)
				{
					Piece piece = (Piece) or[i][j].clone();
					tabPiece[i][j] = piece;
				}
			}
		
		return tabPiece;
	}
	
	public Niveau  getNiveau (){return this.niveau;}
	public Plateau getPlateau(){return this.pl    ;}
	
	public void setNiveau (Niveau niv){this.niveau = niv;}
	public void setPlateau(Plateau pl){this.pl = pl   ;}
	
	public static void main(String[] a)
	{
		new Controleur();
	}
}
