package Projet.Metier;

import java.util.ArrayList;

import Projet.Metier.Pieces.Piece;

public class Plateau
{
	private ArrayList<Piece> alPiece ;
	private Piece[][]        tabPiece;
	
	public Plateau(ArrayList<Piece> alPiece)
	{
		this.alPiece = alPiece;
		this.tabPiece = new Piece[4][4];
		
		for (int i = 0; i < this.alPiece.size(); i++)
			this.tabPiece[this.alPiece.get(i).getPosX()][this.alPiece.get(i).getPosY()] = this.alPiece.get(i);
	}
	
	public Piece[][] getTabPiece(){return this.tabPiece;}
	
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
