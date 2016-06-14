package Projet.Metier;



import java.util.ArrayList;

import Projet.Metier.Pieces.Piece;

public class Plateau
{
	private Piece[][]        tabPiece;
	private ArrayList<Piece> capturees;
	
	public Plateau(Piece[][] tabPiece)
	{
		this.tabPiece = new Piece[4][4];
		this.capturees = new ArrayList<>();
		this.tabPiece = tabPiece;
	}
	
	public boolean deplacer(Piece p, int cibleX, int cibleY)
	{
		if(p.deplacementValide(p.getPosX()-cibleX, p.getPosY()-cibleY) && this.tabPiece[cibleX][cibleY] != null)
		{
			tabPiece[p.getPosX()][p.getPosY()] = null;
			this.capturees.add(tabPiece[cibleX][cibleY]);
			tabPiece[cibleX][cibleY] = p;
			p.setPosX(cibleX);
			p.setPosY(cibleY);
			return true;
		}
		return false;
	}
	
	public Piece[][] getTabPiece(){return this.tabPiece;}
	public ArrayList<Piece> getPiecesCapturees(){return this.capturees;}
	
	public String toString()
	{
		String sRet = "";
		
		for(int i = 0; i < tabPiece.length; i++)
		{
			for (int j =0; j < tabPiece[i].length; j++)
				if (tabPiece[i][j] != null)
					sRet += "| "+ tabPiece[i][j].getType() +" |";
				else
					sRet += "|   |";
			sRet += "\n";
		}
		return sRet;
	}
	
}
