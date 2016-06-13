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
	
	public int getPosX(){return this.posX;}
	public int getPosY(){return this.posY;}
	public void setPosX(int x){this.posX = x;}
	public void setPosY(int y){this.posY = y;}
	
	public abstract boolean mouvement(int x, int y);
	public abstract char    getType();
	
}