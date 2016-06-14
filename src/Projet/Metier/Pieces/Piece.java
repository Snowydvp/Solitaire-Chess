package Projet.Metier.Pieces;

import Projet.Metier.Plateau;

public abstract class Piece 
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
		while(!pieceEntreDeux && (caseX < cibleX || caseY < cibleY))
		{
			if(plateau[caseX][caseY] != null)
				pieceEntreDeux = true;
			
			caseX += dirX;
			caseY += dirY;
		}
				

		return pieceEntreDeux;
	}
	
}