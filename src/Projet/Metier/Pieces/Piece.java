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
	
	
	public abstract boolean mouvement(int deltaX, int deltaY);
	public abstract char    getType();
	
}