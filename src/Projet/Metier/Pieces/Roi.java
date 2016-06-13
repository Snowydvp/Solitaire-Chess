package Projet.Metier.Pieces;

public class Roi extends Piece
{


	public Roi(int posX, int posY) 
	{
		super(posX, posY);
	}

	@Override
	public boolean mouvement(int deltaX, int deltaY)
	{
		
		return (deltaX >= -1 && deltaX <= 1 && deltaY >= -1 && deltaY <= 1);
	}

	public char getType() {return 'r';}


}
