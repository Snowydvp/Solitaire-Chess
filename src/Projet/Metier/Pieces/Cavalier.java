package Projet.Metier.Pieces;

/**
 * Classe permettant la creation d'un Cavalier
 * @author Ashbringer
 *
 */
public class Cavalier extends Piece
{
	/**
	 * Constructeur permettent d'instancier une piece de type Cavalier
	 * @param posX
	 * @param posY
	 */
	public Cavalier(int posX, int posY)
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
		
		if(deltaX == -2 || deltaX == 2)
			if (deltaY == -1 || deltaY == 1)
				return true;

		if(deltaY == -2 || deltaY == 2)
			if (deltaX == -1 || deltaX == 1)
				return true;
		
		return false;
	}
	
	/**
	 * Accesseur qui retourne la premiere lettre du type de la piece
	 */
	public char getType() {return 'C';}
}
