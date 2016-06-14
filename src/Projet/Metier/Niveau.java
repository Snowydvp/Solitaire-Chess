package Projet.Metier;

import java.io.FileReader;
import java.util.Scanner;

import Projet.Metier.Pieces.*;

public class Niveau
{
	private Piece[][] tabPiece = new Piece[4][4];
	
	public Niveau(int numNiveau, String difficultee)
	{
		try
		{
			  FileReader fr = new FileReader("Niveaux/NiveauxPredefini/Niveaux"+ difficultee + ".txt");
			  Scanner sc = new Scanner ( fr );
			  boolean niveauTrouve = false;
			  
			  while(sc.hasNextLine() && !niveauTrouve)
				 if(sc.hasNextInt())
				 {
					 if (sc.nextInt() == numNiveau )
					 {
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
			  
		}catch (Exception e){System.out.println(e.toString());}
	}
	
	public Piece[][] getPiece(){return this.tabPiece;}
}