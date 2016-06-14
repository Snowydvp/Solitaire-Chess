package Projet.Metier.Pieces;


public class Reine extends Piece
{

	public Reine(int posX, int posY)
	{
		super(posX, posY);
	}

	@Override
	public boolean deplacementValide(int cibleX, int cibleY, Piece[][] plateau)
	{
		int deltaX = cibleX - this.posX;
		int deltaY = cibleY - this.posY;
		
		return (!super.pieceGenantDeplacement(cibleX, cibleY, plateau) 
				&& (deltaX==deltaY || deltaX==0 || deltaY==0 || deltaX==-deltaY)); 	//return true si la piece se deplace verticalement, horizontalement 
															//diagonalement
	}

	@Override
	public char getType() {return 'R';}


}
