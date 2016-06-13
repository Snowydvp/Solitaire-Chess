package Projet.Metier;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import Projet.Metier.Pieces.*;

public class InitialisateurNiveau
{
	private ArrayList<Piece> alPiece = new ArrayList<Piece>();
	
	public InitialisateurNiveau(int numNiveau, String difficulter)
	{
		try
		{
			  FileReader fr = new FileReader("Niveaux/NiveauxPredefini/Niveaux"+ difficulter + ".txt");
			  Scanner sc = new Scanner ( fr );
			  
			  
			  while(sc.hasNextInt())
				 if (sc.nextInt() == numNiveau )
				 {
					  sc.nextLine();
					  for(int i = 0; i < 4; i++)
						  for(int j = 0; j < 4; j++)
							  switch(sc.next())
							  {
							  	case "F" : alPiece.add(new Fou     (i, j)); break;
							  	case "C" : alPiece.add(new Cavalier(i, j)); break;
							  	case "P" : alPiece.add(new Pion    (i, j)); break;
							  	case "R" : alPiece.add(new Reine   (i, j)); break;
							  	case "r" : alPiece.add(new Roi     (i, j)); break;
							  	case "T" : alPiece.add(new Tour    (i, j)); break;
							  }
					 sc.close();
				 }
				 else
					 for(int i = 0; i<5 ; i++)
						 sc.nextLine();
		}catch (Exception e){System.out.println(e.toString());}
	}
	
	public ArrayList<Piece> getPiece(){return alPiece;}
}
