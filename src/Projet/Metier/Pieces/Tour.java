package Projet.Metier.Pieces;

public class Tour extends Piece
{


	public boolean mouvement(int deltaX, int deltaY)
	{
		return (deltaX==0 ^ deltaY==0);
	}


	public char getType(){return 'T';}

}
