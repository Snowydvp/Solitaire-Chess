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