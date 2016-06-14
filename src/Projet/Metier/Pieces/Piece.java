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
		if(cibleX - posX > 0)      dirX = 1;
		else if(cibleX - posX < 0) dirX = -1;
		if(cibleY - posY > 0)      dirY = 1;
		else if(cibleY - posY < 0) dirY = -1;
		
		for(int caseX = this.posX + dirX; !pieceEntreDeux && caseX < cibleX - dirX; caseX += dirX)
			for(int caseY = this.posY + dirY; !pieceEntreDeux && caseY < cibleY - dirY; caseY += dirY)
				if(plateau[caseX][caseY] != null)
					pieceEntreDeux = true;
		
		return pieceEntreDeux;
	}
	
}