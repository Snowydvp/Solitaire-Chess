package Projet.Metier.Pieces;

public class Roi extends Piece
{


	public Roi(int posX, int posY) 
	{
		super(posX, posY);
	}

	@Override
	public boolean deplacementValide(int cibleX, int cibleY, Piece[][] plateau)
	{
		int deltaX = cibleX - this.posX;
		int deltaY = cibleY - this.posY;
		
		return (deltaX >= -1 && deltaX <= 1 && deltaY >= -1 && deltaY <= 1);
	}

	public char getType() {return 'r';}


}
