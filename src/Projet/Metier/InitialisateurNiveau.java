package Projet.Metier;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import Projet.Metier.Pieces.*;

public class InitialisateurNiveau
{
	private Piece[][] tabPiece = new Piece[4][4];
	
	public InitialisateurNiveau(int numNiveau, String difficulter)
	{
		try
		{
			  FileReader fr = new FileReader("Niveaux/NiveauxPredefini/Niveaux"+ difficulter + ".txt");
			  Scanner sc = new Scanner ( fr );
			  
			  
			  while(sc.hasNextLine())
				 if(sc.hasNextInt())
				 {
					 if (sc.nextInt() == numNiveau )
					 {
						  sc.nextLine();
						  for(int i = 0; i < 4; i++)
							  for(int j = 0; j < 4; j++)
								  switch(sc.next().charAt(0))
								  {
								  	case 'F' : this.tabPiece[i][j] = new Fou     (i,j); break;
								  	case 'C' : this.tabPiece[i][j] = new Cavalier(i,j); break;
								  	case 'P' : this.tabPiece[i][j] = new Pion    (i,j); break;
								  	case 'R' : this.tabPiece[i][j] = new Reine   (i,j); break;
								  	case 'r' : this.tabPiece[i][j] = new Roi     (i,j); break;
								  	case 'T' : this.tabPiece[i][j] = new Tour    (i,j); break;
								  }
						 sc.close();
					 }
				}
				else
					sc.nextLine();
			  
		}catch (Exception e){System.out.println(e.toString());}
	}
	
	public Piece[][] getPiece(){return this.tabPiece;}
}
