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
	public abstract boolean deplacementValide(int deltaX, int deltaY);
	public abstract char    getType();
	
}