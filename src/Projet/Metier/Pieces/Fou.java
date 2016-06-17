package Projet.Metier.Pieces;

public class Fou extends Piece
{
	public Fou(int posX, int posY)
	{
		super(posX, posY);
	}

	public boolean deplacementValide(int cibleX, int cibleY, Piece[][] plateau) 
	{
		int deltaX = cibleX - this.posX;
		int deltaY = cibleY - this.posY;
		
		return (!super.pieceGenantDeplacement(cibleX, cibleY, plateau) 
				&& (deltaX == deltaY || deltaX == -deltaY));
	}
		
	public char getType() {return 'F';}
}
