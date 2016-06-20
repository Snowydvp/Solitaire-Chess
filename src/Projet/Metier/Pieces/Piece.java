package Projet.Metier.Pieces;

import Projet.Metier.Plateau;

/**
 * Classe permettant la creation d'une Piece
 * @author Ashbringer
 *
 */
public abstract class Piece implements Cloneable
{
	protected int posX;
	protected int posY;
	protected Plateau pl;
	
	/**
	 * Constructeur de la classe.
	 * @param posX
	 * @param posY
	 */
	public Piece(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
	}
	
	/**
	 * @return position X dans le plateau
	 */
	public int getPosX() {return this.posX;}
	/**
	 * @return position Y dans le plateau
	 */
	public int getPosY() {return this.posY;}

	public void setPosX(int posX) {this.posX=posX;}
	public void setPosY(int posY) {this.posY=posY;}

	/**
	 * Methode abstract commune a toute les pieces qui détermine la validité du déplacement.
	 * @param cibleX
	 * @param cibleY
	 * @param plateau
	 * @return
	 */
	public abstract boolean deplacementValide(int cibleX, int cibleY, Piece[][] plateau);
	/**
	 * Methode commune a toutes les pieces qui permet de connaitre le type de la piece
	 * @return
	 */
	public abstract char    getType();

	/**
	 * Methode qui retourne si une piece gene le deplacement de la piece selectionnée
	 * @param cibleX
	 * @param cibleY
	 * @param plateau
	 * @return
	 */
	public boolean pieceGenantDeplacement(int cibleX, int cibleY, Piece[][] plateau)
	{
		boolean pieceEntreDeux = false;
		int dirX = 0;
		int dirY = 0;
		
		if     (cibleX - this.posX > 0) dirX =  1;
		else if(cibleX - this.posX < 0) dirX = -1;
		
		if     (cibleY - posY > 0) dirY =  1;
		else if(cibleY - posY < 0) dirY = -1;
		
		int caseX = this.posX + dirX;
		int caseY = this.posY + dirY;
		
		while(!pieceEntreDeux && (caseX != cibleX || caseY != cibleY) && caseX >= 0 && caseY >= 0 && caseX < 4 && caseY < 4)
		{
			if(plateau[caseY][caseX] != null)
				pieceEntreDeux = true;

			caseX += dirX;
			caseY += dirY;
		}
		
		return pieceEntreDeux;
	}

	/**
	 * @return Copie conforme de la piece
	 */
	public Piece clone() 
	{
		Piece p = null;
		try 
		{
			p = (Piece) super.clone();
			p.setPosX(p.getPosX());
			p.setPosY(p.getPosY());
		}catch(CloneNotSupportedException cnse) {cnse.printStackTrace(System.err);}
		return p;
	}
}