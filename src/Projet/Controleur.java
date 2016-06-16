package Projet;

import java.util.ArrayList;

import Projet.IHM.Fenetre;
import Projet.IHM.Jeu;
import Projet.Metier.*;
import Projet.Metier.Pieces.Piece;

public class Controleur
{
	private final String[] tabDifficultee = {"Debutant", "Intermediaire", "Avance", "Expert"};
	private Plateau pl;
	private Partie partie;
	private Fenetre fenetre;
	//private Partie partie;
	private Niveau niveauCourant;
	private ArrayList<Plateau> alEtatPrecedent;
	private ArrayList<Niveau> alNiveauDebutant, alNiveauIntermediaire, alNiveauAvance, alNiveauExpert, alNiveauEdite;
	
	public Controleur()
	{
		//niveau aleatoire
		//int niveau = (int)(Math.random()*15+1);
		//int difficultee = (int)(Math.random()*4);
		
		alNiveauDebutant = new ArrayList<>();
		alNiveauIntermediaire = new ArrayList<>();
		alNiveauAvance = new ArrayList<>();
		alNiveauExpert = new ArrayList<>();
		alNiveauEdite  = new ArrayList<>();
		
			for(int i = 1; i < 50; i++)
			{
				Niveau tmp = new Niveau(i, tabDifficultee[0]);
				if (!tmp.getInstancier())
					alNiveauDebutant.add(tmp);
				Niveau tmp1 = new Niveau(i, tabDifficultee[1]);
				if (!tmp1.getInstancier())
					alNiveauIntermediaire.add(tmp1);
				Niveau tmp2 = new Niveau(i, tabDifficultee[2]);
				if (!tmp2.getInstancier())
					alNiveauAvance.add(tmp2);
				Niveau tmp3 = new Niveau(i, tabDifficultee[3]);
				if (!tmp3.getInstancier())
					alNiveauExpert.add(tmp3);
				Niveau tmp4 = new Niveau(i-1);
				if (!tmp4.getInstancier())
					alNiveauEdite.add(tmp4);
			}
			
		this.niveauCourant = this.alNiveauDebutant.get(0);
		this.partie = new Partie(this);
		this.fenetre = new Fenetre(this);
		pl = new Plateau(this.getNiveau().getPiece());
		alEtatPrecedent = new ArrayList<>();
		alEtatPrecedent.add(new Plateau(this.copieTableau(this.pl.getPlateau())));
		
		//System.out.println(niveau.getNumNiveau() + "  " + niveau.getDifficultee());
		//System.out.println(pl.toString());
	}
	
	
	public void augmenterNiveau()
	{
		if (this.niveauCourant.getNumNiveau() < 15)
			this.setNiveau(new Niveau(this.niveauCourant.getNumNiveau()+1, this.niveauCourant.getDifficulte()));
		else if ( !this.niveauCourant.getDifficulte().equals("Expert"))
			this.setNiveau(new Niveau(1, this.tabDifficultee[this.augmenterDifficulte(this.niveauCourant.getDifficulte())]));
			
		pl = new Plateau(this.niveauCourant.getPiece());	
		System.out.println(this.niveauCourant.getNumNiveau());
		this.partie.enregistrerPartie();
		
		this.alEtatPrecedent.clear();
		alEtatPrecedent.add(new Plateau(this.copieTableau(this.pl.getPlateau())));
		
		/*
		String difficulte = this.niveauCourant.getDifficulte();
		ArrayList<Niveau> diffCourant;
		if(difficulte.equals("Debutant"))
			diffCourant = this.alNiveauDebutant;
		else if(difficulte.equals("Intermediaire"))
			diffCourant = this.alNiveauIntermediaire;
		else if(difficulte.equals("Avance"))
			diffCourant = this.alNiveauAvance;
		else if(difficulte.equals("Expert"))
			diffCourant = this.alNiveauExpert;*/
		
			
		
		
	}
	
	public void diminuerNiveau()
	{
		if ( this.niveauCourant.getNumNiveau() > 1)
			this.setNiveau(new Niveau(this.niveauCourant.getNumNiveau()-1, this.niveauCourant.getDifficulte()));
		else if ( !niveauCourant.getDifficulte().equals("Debutant"))
			this.setNiveau(new Niveau(15, this.tabDifficultee[this.diminuerDifficulte(this.niveauCourant.getDifficulte())]));
		
		pl = new Plateau(niveauCourant.getPiece());
		
		this.alEtatPrecedent.clear();
		alEtatPrecedent.add(new Plateau(this.copieTableau(this.pl.getPlateau())));
	}
	
	public void creerPartie()
	{
		Jeu.score = 0;
		//this.partie.nouvellePartie();
	}
	
	public void rejouer()
	{
		this.setNiveau(new Niveau(this.niveauCourant.getNumNiveau(), this.niveauCourant.getDifficulte()));
		pl = new Plateau(this.niveauCourant.getPiece());
		
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
			
		}
	}
	
	public void sauvegardeCoup()
	{
		this.alEtatPrecedent.add(new Plateau(this.copieTableau(this.pl.getPlateau())));

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
	
	public Niveau  getNiveau (){return this.niveauCourant;}
	public Plateau getPlateau(){return this.pl    ;}
	public Partie getPartie  (){return this.partie;}
	
	public void setNiveau (Niveau niv){this.niveauCourant = niv;}
	public void setPlateau(Plateau pl){this.pl = pl;}
	
	public ArrayList<Niveau> getNiveauDebutant(){return this.alNiveauDebutant    ;}
	public ArrayList<Niveau> getNiveauIntermediaire(){return this.alNiveauIntermediaire    ;}
	public ArrayList<Niveau> getNiveauAvance(){return this.alNiveauAvance   ;}
	public ArrayList<Niveau> getNiveauExpert(){return this.alNiveauExpert    ;}
	
	public static void main(String[] a)
	{
		new Controleur();
	}
}
