package Projet.Metier.Pieces;

/**
 * Classe permettant la creation d'un Roi
 * @author Ashbringer
 *
 */
public class Roi extends Piece
{
	/**
	 * Constructeur permettent d'instancier une piece de type Roi
	 * @param posX
	 * @param posY
	 */
	public Roi(int posX, int posY) 
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
		
		return (deltaX >= -1 && deltaX <= 1 && deltaY >= -1 && deltaY <= 1);
	}

	/**
	 * Accesseur qui retourne la premiere lettre du type de la piece
	 */
	public char getType() {return 'r';}
}
