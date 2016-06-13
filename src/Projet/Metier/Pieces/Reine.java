package Projet.Metier.Pieces;


public class Reine extends Piece
{

	public Reine()
	{
	}

	@Override
	public boolean mouvement(int deltaX, int deltaY)
	{
		return (deltaX==deltaX || deltaX==0 || deltaY==0); 	//return true si la piece se deplace verticalement, horizontalement 
															//diagonalement
	}

	@Override
	public char getType() {return 'R';}


}
