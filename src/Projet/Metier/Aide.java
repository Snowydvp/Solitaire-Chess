package Projet.Metier;

import java.io.FileReader;
import java.util.Scanner;

import Projet.Controleur;
import Projet.Metier.Pieces.Piece;

/**
 * Aide du jeu lorsque le joueur est bloqué.
 * @author BELLANGER Jessy, LINTOT Maxime, PICOT Maxence et SINAEVE Antoine
 *
 */
public class Aide 
{
	/**
	 * Constructeur par défaut.
	 * @param numDefi est le numéro du défi ou l'aide va etre faite.
	 * @param ctrl est le Controleur.
	 * 
	 */
	public Aide(int numDefi, Controleur ctrl)
	{	
		try
		{
			FileReader fr = new FileReader("Aide/AideDebutant.txt");
			Scanner sc = new Scanner( fr );
			boolean niveauTrouve = false;
			  
			while(sc.hasNextLine() && !niveauTrouve)
			{
				if(sc.hasNextInt())
				{
					int tmp = sc.nextInt();
					String line = sc.nextLine();
					if(!(line.length()>2) && tmp == numDefi) 
					{
						Piece pieceTmp = null;
						do 
						{
							line = sc.nextLine();
						 
							Scanner scLigne = new Scanner(line);
							int posY = scLigne.nextInt();
							int posX = scLigne.nextInt();
						 
							int cibleY = scLigne.nextInt();
							int cibleX = scLigne.nextInt();
						 
							pieceTmp = ctrl.getPlateau().getPlateau()[posY][posX];
						 
							if(pieceTmp != null)
								ctrl.getPlateau().deplacer(pieceTmp, cibleX, cibleY);
						 
						}while(!line.equals("") && pieceTmp == null);
					}
				}
				else
					sc.nextLine();
			}
			sc.close(); 
		}catch (Exception e){}
	}
}
