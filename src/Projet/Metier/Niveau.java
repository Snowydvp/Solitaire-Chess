package Projet.Metier;

import java.io.FileReader;
import java.util.Scanner;

import Projet.Metier.Pieces.*;

public class Niveau
{
	private Piece[][] tabPiece;
	private int numNiveau;
	private String difficulte;
	
	public Niveau(int niveau)
	{
		this.numNiveau = niveau;
		this.difficulte = "Edite";
		try
		{
			  FileReader fr = new FileReader("Niveaux/NiveauEditeur/NiveauEdites.txt");
			  Scanner sc = new Scanner ( fr );
			  boolean niveauTrouve = false;
			  
			  for(int i = 0; i < niveau; i++)
				  for(int j = 0; j < 5; j++)
					  sc.nextLine();
			  
			  while(sc.hasNextLine() && !niveauTrouve)	  
			  {
				  this.tabPiece = new Piece[4][4];
				  if (sc.nextLine().equals(""))
				  {
					  for(int i = 0; i < 4; i++)
						  for(int j = 0; j < 4; j++)
							  switch(sc.next().charAt(0))
							  {
							  		case 'F' : this.tabPiece[i][j] = new Fou     (j,i); break;
								  	case 'C' : this.tabPiece[i][j] = new Cavalier(j,i); break;
								  	case 'P' : this.tabPiece[i][j] = new Pion    (j,i); break;
								  	case 'R' : this.tabPiece[i][j] = new Reine   (j,i); break;
								  	case 'r' : this.tabPiece[i][j] = new Roi     (j,i); break;
								  	case 'T' : this.tabPiece[i][j] = new Tour    (j,i); break;
							  }
						  
					  niveauTrouve = true;
				  }
			  }
			  
			  sc.close();	  
		}catch (Exception e){}
	}
	
	public Niveau(Piece[][] tabPiece)
	{
		this.tabPiece = tabPiece;
		this.difficulte = "Edite";
		this.numNiveau = 0;
	}
	
	public Niveau(int numNiveau, String difficulte)
	{
		
		this.numNiveau = numNiveau;
		this.difficulte = difficulte;
		try
		{
			  FileReader fr = new FileReader("Niveaux/NiveauxPredefini/Niveaux"+ difficulte + ".txt");
			  Scanner sc = new Scanner ( fr );
			  boolean niveauTrouve = false;
			  
			  while(sc.hasNextLine() && !niveauTrouve)
				 if(sc.hasNextInt())
				 {
					 if (sc.nextInt() == numNiveau )
					 {
						 this.tabPiece = new Piece[4][4];
						  sc.nextLine();
						  for(int i = 0; i < 4; i++)
							  for(int j = 0; j < 4; j++)
								  switch(sc.next().charAt(0))
								  {
								  	case 'F' : this.tabPiece[i][j] = new Fou     (j,i); break;
								  	case 'C' : this.tabPiece[i][j] = new Cavalier(j,i); break;
								  	case 'P' : this.tabPiece[i][j] = new Pion    (j,i); break;
								  	case 'R' : this.tabPiece[i][j] = new Reine   (j,i); break;
								  	case 'r' : this.tabPiece[i][j] = new Roi     (j,i); break;
								  	case 'T' : this.tabPiece[i][j] = new Tour    (j,i); break;
								  }
						  niveauTrouve = true;
					 }
				}
				else
					sc.nextLine();
			  sc.close(); 
		}catch (Exception e){}
	}
	
	public Piece[][] getPiece()
	{
		Piece[][] pieceOrigine = new Piece[4][4];
		for(int i = 0; i < 4;i++)
			for(int j = 0;j < 4;j++)
				pieceOrigine[i][j]=this.tabPiece[i][j];
		return pieceOrigine;
	}
	
	public int getNumDifficulte()
	{
		int d = 1;
		if(this.difficulte.equals("Intermediaire")) d = 2;
		else if(this.difficulte.equals("Avance"))   d = 3;
		else if(this.difficulte.equals("Expert"))   d = 4;
		return d;
	}
	
	public boolean getInstancier(){return tabPiece == null;}
	public int     getNumNiveau (){ return this.numNiveau ;}
	public String  getDifficulte(){ return this.difficulte;}
}
