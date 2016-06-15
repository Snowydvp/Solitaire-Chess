package Projet.Metier;



import java.util.ArrayList;

import Projet.Metier.Pieces.Piece;
import Projet.Metier.Pieces.Roi;

public class Plateau
{
	private Piece[][]        plateau;
	private ArrayList<Piece> capturees;
	
	public Plateau(Piece[][] plateau)
	{
		this.plateau = new Piece[4][4];
		this.capturees = new ArrayList<>();
		this.plateau = plateau;
	}
	
	public boolean deplacer(Piece p, int cibleX, int cibleY)
	{
		if(p.deplacementValide(cibleX, cibleY, this.plateau) && this.plateau[cibleY][cibleX] != null && !(plateau[cibleY][cibleX] instanceof Roi))
		{
			plateau[p.getPosY()][p.getPosX()] = null;
			this.capturees.add(plateau[cibleY][cibleX]);
			plateau[cibleY][cibleX] = p;
			p.setPosX(cibleX);
			p.setPosY(cibleY);
			
			return true;
		}
		return false;
	}
	
	public Piece[][] getPlateau(){return this.plateau;}
	public ArrayList<Piece> getPiecesCapturees(){return this.capturees;}
	
	public String toString()
	{
		String sRet = "";
		
		for(int i = 0; i < plateau.length; i++)
		{
			for (int j = 0; j < plateau[i].length; j++)
				if (plateau[i][j] != null)
					sRet += "| "+ plateau[i][j].getType() +" |";
				else
					sRet += "|   |";
			sRet += "\n";
		}
		return sRet;
	}
	
}
