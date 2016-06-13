package Projet.Metier.Pieces;

public class Reine extends Piece
{

	public Reine(int posX, int posY)
	{
		super(posX, posY);
	}

	@Override
	public boolean mouvement(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public char getType() {return 'R';}



}
