package Projet.Metier.Pieces;

public class Tour extends Piece
{


	public Tour(int posX, int posY) 
	{
		super(posX, posY);
	}


	public boolean deplacementValide(int cibleX, int cibleY, Piece[][] plateau)
	{
		int deltaX = cibleX - this.posX;
		int deltaY = cibleY - this.posY;
		
		return (!super.pieceGenantDeplacement(cibleX, cibleY, plateau)
				&& deltaX==0 ^ deltaY==0);
	}


	public char getType(){return 'T';}

}
