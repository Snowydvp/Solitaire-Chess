package Projet.Metier.Pieces;

import Projet.Metier.Plateau;

public abstract class Piece 
{
	protected Plateau pl;
	
	public Piece()
	{
	}
	
	
	public abstract boolean mouvement(int x, int y);
	public abstract char    getType();
	
}