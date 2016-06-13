package Projet.Metier.Pieces;

import Projet.Metier.Plateau;

public abstract class Piece 
{
	protected Plateau pl;
	
	public Piece()
	{
	}
	
	
	public abstract boolean mouvement(int xOrigine, int yOrigine, int xCible, int yCible);
	public abstract char    getType();
	
}