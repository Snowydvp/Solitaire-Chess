package Projet.Metier;



import Projet.Metier.Pieces.Piece;

public class Plateau
{
	private Piece[][]        tabPiece;
	
	public Plateau(Piece[][] tabPiece)
	{
		this.tabPiece = new Piece[4][4];
		
		this.tabPiece = tabPiece;
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
