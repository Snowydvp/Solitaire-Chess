package Projet.Metier.Pieces;

public class Roi extends Piece
{

	public Roi() 
	{
	}

	@Override
	public boolean mouvement(int deltaX, int deltaY)
	{
		
		return false;
	}

	public char getType() {return 'r';}


}
