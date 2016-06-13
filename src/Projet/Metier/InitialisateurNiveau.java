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
				 if (sc.nextInt() == numNiveau )
				 {
					  sc.nextLine();
					  for(int i = 0; i < 4; i++)
						  for(int j = 0; j < 4; j++)
							  switch(sc.next())
							  {
							  	case "F" : this.tabPiece[i][j] = new Fou     (); break;
							  	case "C" : this.tabPiece[i][j] = new Cavalier(); break;
							  	case "P" : this.tabPiece[i][j] = new Pion    (); break;
							  	case "R" : this.tabPiece[i][j] = new Reine   (); break;
							  	case "r" : this.tabPiece[i][j] = new Roi     (); break;
							  	case "T" : this.tabPiece[i][j] = new Tour    (); break;
							  }
					 sc.close();
				 }
				 else
					 for(int i = 0; i<5 ; i++)
						 sc.nextLine();
			  
		}catch (Exception e){System.out.println(e.toString());}
	}
	
	public Piece[][] getPiece(){return this.tabPiece;}
}