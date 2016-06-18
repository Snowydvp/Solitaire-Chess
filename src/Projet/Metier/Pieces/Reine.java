package Projet.Metier.Pieces;

/**
 * Classe permettant la creation d'une Reine
 * @author Ashbringer
 *
 */
public class Reine extends Piece
{
	/**
	 * Constructeur permettent d'instancier une piece de type Reine
	 * @param posX
	 * @param posY
	 */
	public Reine(int posX, int posY)
	{
		super(posX, posY);
	}

	/**
	 * Verifie si le deplacement de la piece est possible
	 */
	public boolean deplacementValide(int cibleX, int cibleY, Piece[][] plateau)
	{
		int deltaX = cibleX - this.posX;
		int deltaY = cibleY - this.posY;
		
		return (!super.pieceGenantDeplacement(cibleX, cibleY, plateau) 
				&& (deltaX == deltaY || deltaX == 0 || deltaY == 0 || deltaX == -deltaY)); 	//return true si la piece se deplace verticalement, horizontalement 
															//diagonalement
	}

	/**
	 * Accesseur qui retourne la premiere lettre du type de la piece
	 */
	public char getType() {return 'R';}
}
