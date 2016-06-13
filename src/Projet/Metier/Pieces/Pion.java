package Projet.Metier.Pieces;

public class Pion extends Piece 
{
	public Pion() 
	{
	}

	public boolean mouvement(int deltaX, int deltaY ) 
	{
		return (deltaX == -1 || deltaX == 1) && deltaY == -1;
	}
	

	public char getType() {return 'P';}



}
