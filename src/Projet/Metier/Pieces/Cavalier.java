package Projet.Metier.Pieces;

public class Cavalier extends Piece
{
	public Cavalier(int posX, int posY)
	{
		super(posX, posY);
	}

	public boolean mouvement(int deltaX, int deltaY) 
	{
		if(deltaX == -2 || deltaX == 2)
			if (deltaY == -1 || deltaY == 1)
				return true;

		if(deltaY == -2 || deltaY == 2)
			if (deltaX == -1 || deltaX == 1)
				return true;
		return false;
	}
		
	public char getType() {return 'C';}



}
