package Projet.Metier.Pieces;

/**
 * Classe permettant la creation d'un Fou
 * @author Ashbringer
 *
 */
public class Fou extends Piece
{
	/**
	 * Constructeur permettent d'instancier une piece de type Fou
	 * @param posX
	 * @param posY
	 */
	public Fou(int posX, int posY)
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
				&& (deltaX == deltaY || deltaX == -deltaY));
	}
		
	/**
	 * Accesseur qui retourne la premiere lettre du type de la piece
	 */
	public char getType() {return 'F';}
}
