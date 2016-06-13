package Projet.Metier.Pieces;

public class Roi extends Piece
{

	public Roi(int posX, int posY) 
	{
		super(posX, posY);
	}

	@Override
	public boolean mouvement(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	public char getType() {return 'r';}


}
