package Projet.Metier.Pieces;

public class Fou extends Piece
{

	public boolean mouvement(int deltaX, int deltaY) 
	{
		return deltaX == deltaY;
	}
		
	public char getType() {return 'F';}



}
