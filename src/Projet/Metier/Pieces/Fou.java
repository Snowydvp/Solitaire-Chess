package Projet.Metier.Pieces;

public class Fou extends Piece
{

	public Fou(int posX, int posY)
	{
		super(posX, posY);
	}

	public boolean mouvement(int deltaX, int deltaY) 
	{
		return deltaX == deltaY;
	}
		
	public char getType() {return 'F';}



}
