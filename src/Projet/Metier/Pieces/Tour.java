package Projet.Metier.Pieces;

public class Tour extends Piece
{

	public Tour()
	{
	}

	public boolean mouvement(int deltaX, int deltaY)
	{
		if(deltaX==0 || deltaY==0)
			return true;
		return false;
	}


	public char getType(){return 'T';}

}
