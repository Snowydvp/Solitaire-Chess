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
	
	public Piece deplacer(Piece p, int cibleX, int cibleY)
	{
		if(p.deplacementValide(p.getPosX()-cibleX, p.getPosY()-cibleY) && this.tabPiece[cibleX][cibleY] != null)
		{
			Piece capturee = tabPiece[cibleX][cibleY];
			tabPiece[p.getPosX()][p.getPosY()] = null;
			tabPiece[cibleX][cibleY] = p;
			p.setPosX(cibleX);
			p.setPosY(cibleY);
			return capturee;
		}
		return null;
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
