package Projet.Metier.Pieces;

public class Cavalier extends Piece
{

	public Cavalier(int posX, int posY) 
	{
		super(posX, posY);
	}

	@Override
	public boolean mouvement(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}


	public char getType() {return 'C';}



}
