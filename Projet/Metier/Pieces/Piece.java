package Projet.Metier.Pieces;

public abstract class Piece 
{
	protected int posX;
	protected int posY;
	
	public Piece(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosX(){return this.posX;}
	public int getPosY(){return this.posY;}
	
	public abstract boolean mouvement(int x, int y);
	public abstract char    getType();
	
}