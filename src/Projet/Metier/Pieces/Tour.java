package Projet.Metier.Pieces;
/**
 * Classe permettant la creation d'une Tour
 * @author Ashbringer
 *
 */
public class Tour extends Piece
{
	/**
	 * Constructeur permettent d'instancier une piece de type Roi
	 * @param posX
	 * @param posY
	 */
	public Tour(int posX, int posY) 
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
		
		return (!super.pieceGenantDeplacement(cibleX, cibleY, plateau) && deltaX == 0 ^ deltaY == 0);
	}

	/**
	 * Accesseur qui retourne la premiere lettre du type de la piece
	 */
	public char getType(){return 'T';}
}
