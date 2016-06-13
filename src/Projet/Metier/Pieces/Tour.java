package Projet.Metier.Pieces;

public class Tour extends Piece
{


	public Tour(int posX, int posY) 
	{
		super(posX, posY);
	}


	public boolean deplacementValide(int deltaX, int deltaY)
	{
		return (deltaX==0 ^ deltaY==0);
	}


	public char getType(){return 'T';}

}
