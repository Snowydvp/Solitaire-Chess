package Projet.Metier.Pieces;


public class Reine extends Piece
{

	public Reine(int posX, int posY)
	{
		super(posX, posY);
	}

	@Override
	public boolean deplacementValide(int deltaX, int deltaY)
	{
		return (deltaX==deltaY || deltaX==0 || deltaY==0); 	//return true si la piece se deplace verticalement, horizontalement 
															//diagonalement
	}

	@Override
	public char getType() {return 'R';}


}
