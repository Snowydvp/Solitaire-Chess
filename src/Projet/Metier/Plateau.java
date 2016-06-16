package Projet.Metier;

import java.util.ArrayList;

import Projet.Metier.Pieces.Piece;
import Projet.Metier.Pieces.Roi;

public class Plateau
{
	private Piece[][]        plateau;
	private ArrayList<Piece> capturees;
	
	public Plateau(Piece[][] plateau)
	{
		this.capturees = new ArrayList<>();
		this.plateau   = plateau;
	}
	
	public Plateau(Piece[][] plateau, ArrayList<Piece> capturees)
	{
		this(plateau);
		if(capturees.size() != 0)
			capturees.remove(capturees.size()-1);
		this.capturees = capturees;
		
	}
	
	public boolean deplacer(Piece p, int cibleX, int cibleY)
	{
		if(p.deplacementValide(cibleX, cibleY, this.plateau) && this.plateau[cibleY][cibleX] != null && 
				!(this.plateau[cibleY][cibleX] instanceof Roi))
		{
			this.plateau[p.getPosY()][p.getPosX()] = null;
			this.capturees.add(this.plateau[cibleY][cibleX]);
			this.plateau[cibleY][cibleX] = p;
			p.setPosX(cibleX);
			p.setPosY(cibleY);
			
			return true;
		}
		return false;
	}
	
	public boolean simuleDeplacement(Piece p, int cibleX, int cibleY)
	{
		if(p.deplacementValide(cibleX, cibleY, this.plateau) && !(this.plateau[cibleY][cibleX] instanceof Roi))
			return true;
		return false;
	}
	
	public boolean deplacerEditeur(Piece p, int cibleX, int cibleY)
	{
		if(this.plateau[cibleY][cibleX] == null)
		{
			this.plateau[p.getPosY()][p.getPosX()] = null;
			this.plateau[cibleY][cibleX] = p;
			p.setPosX(cibleX);
			p.setPosY(cibleY);
			
			return true;
		}
		
		return false;
	}
	
	public Piece[][]        getPlateau        (){return this.plateau  ;}
	public ArrayList<Piece> getPiecesCapturees(){return this.capturees;}
	
	public String toString()
	{
		String sRet = "";
		
		for(int i = 0; i < plateau.length; i++)
		{
			for (int j = 0; j < plateau[i].length; j++)
				if (plateau[i][j] != null)
					sRet += "| "+ plateau[i][j].getType() +" |";
				else
					sRet += "|   |";
			sRet += "\n";
		}
		return sRet;
	}
}
