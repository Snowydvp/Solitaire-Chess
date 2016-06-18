package Projet.Metier.Pieces;

/**
 * Classe permettant la creation d'un Pion
 * @author Ashbringer
 *
 */
public class Pion extends Piece 
{
	/**
	 * Constructeur permettent d'instancier une piece de type Pion
	 * @param posX
	 * @param posY
	 */
	public Pion(int posX, int posY) 
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

		return (deltaX == -1 || deltaX == 1) && deltaY == -1;
	}
	
	/**
	 * Accesseur qui retourne la premiere lettre du type de la piece
	 */
	public char getType() {return 'P';}
}
