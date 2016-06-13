package Projet.Metier.Pieces;

public class Tour extends Piece
{

	public Tour(int posX, int posY)
	{
		super(posX, posY);
	}

	public boolean mouvement(int x, int y) 
	{
		
		return false;
	}


	public char getType(){return 'T';}

}
