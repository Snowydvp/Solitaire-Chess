package Projet.Metier.Pieces;

import Projet.Metier.Plateau;

public abstract class Piece implements Cloneable
{
	protected int posX;
	protected int posY;
	protected Plateau pl;
	
	public Piece(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosX() {return this.posX;}
	public int getPosY() {return this.posY;}
	public void setPosX(int posX) {this.posX=posX;}
	public void setPosY(int posY) {this.posY=posY;}
	public abstract boolean deplacementValide(int cibleX, int cibleY, Piece[][] plateau);
	public abstract char    getType();
	public boolean pieceGenantDeplacement(int cibleX, int cibleY, Piece[][] plateau)
	{
		boolean pieceEntreDeux = false;
		int dirX = 0;
		int dirY = 0;
		if     (cibleX - posX > 0) dirX = 1;
		else if(cibleX - posX < 0) dirX = -1;
		if     (cibleY - posY > 0) dirY = 1;
		else if(cibleY - posY < 0) dirY = -1;
		
		

		
		int caseX = this.posX + dirX;
		int caseY = this.posY + dirY;
		while(!pieceEntreDeux && (caseX != cibleX || caseY != cibleY) && caseX > 0 && caseY > 0 && caseX < 4 && caseY < 4)
		{
			if(plateau[caseY][caseX] != null)
				pieceEntreDeux = true;

			caseX += dirX;
			caseY += dirY;
		}
				

		return pieceEntreDeux;
	}
	
	public Piece clone() {
		Piece p = null;
		try {
			// On récupère l'instance à renvoyer par l'appel de la 
			// méthode super.clone()
			p = (Piece) super.clone();
			p.setPosX(p.getPosX());
			p.setPosY(p.getPosY());
		} catch(CloneNotSupportedException cnse) {
			// Ne devrait jamais arriver car nous implémentons 
			// l'interface Cloneable
			cnse.printStackTrace(System.err);
		}
		// on renvoie le clone
		return p;
	}

	
}