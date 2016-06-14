package Projet.Metier;



import java.util.ArrayList;

import Projet.Metier.Pieces.Piece;

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
		if(p.deplacementValide(cibleX, cibleY, this.plateau) && this.plateau[cibleX][cibleY] != null)
		{
			plateau[p.getPosX()][p.getPosY()] = null;
			this.capturees.add(plateau[cibleX][cibleY]);
			plateau[cibleX][cibleY] = p;
			p.setPosX(cibleX);
			p.setPosY(cibleY);
			return true;
		}
		return false;
	}
	
	public Piece[][] getplateau(){return this.plateau;}
	public ArrayList<Piece> getPiecesCapturees(){return this.capturees;}
	
	public String toString()
	{
		String sRet = "";
		
		for(int i = 0; i < plateau.length; i++)
		{
			for (int j =0; j < plateau[i].length; j++)
				if (plateau[i][j] != null)
					sRet += "| "+ plateau[i][j].getType() +" |";
				else
					sRet += "|   |";
			sRet += "\n";
		}
		return sRet;
	}
	
}
