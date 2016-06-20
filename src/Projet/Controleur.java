package Projet;

import java.util.ArrayList;

import Projet.IHM.Fenetre;
import Projet.Metier.*;
import Projet.Metier.Pieces.Piece;

/**
 * Cette classe lie la partie IHM � la partie m�tier. 
 * @author BELLANGER Jessy, LINTOT Maxime, PICOT Maxence et SINAEVE Antoine
 *
 */
public class Controleur
{
	private final String[] tabDifficultee = {"Debutant", "Intermediaire", "Avance", "Expert"};
	private Plateau pl;
	private Partie partie;
	private Niveau niveauCourant;
	private ArrayList<Plateau> alEtatPrecedent;
	private ArrayList<Niveau> alNiveauDebutant, alNiveauIntermediaire, alNiveauAvance, alNiveauExpert, alNiveauEdite;
	private String theme;
	private boolean cheatActive;
	
	/**
	 * Constructeur par défaut
	 * 
	 */
	public Controleur()
	{
		this.initNiveau();
		this.cheatActive     = false;
		this.theme           = "Default";
		this.partie          = new Partie(this);
		this.pl              = new Plateau(this.getNiveau().getPiece());
		this.alEtatPrecedent = new ArrayList<>();
		this.alEtatPrecedent.add(new Plateau(this.copieTableau(this.pl.getPlateau())));
		
		new Fenetre(this);
	}
	
	/**
	 * M�thode permettant d'augmenter le niveau courant.
	 * Cette m�thode g�re aussi l'augmentation de difficult� en cas de niveau maximum atteint.
	 * 
	 */
	public void augmenterNiveau()
	{
		if(!this.niveauCourant.getDifficulte().equals("Edite"))
		{
			if(this.niveauCourant.getNumNiveau() < this.getDifficulteCourante().size())
				this.setNiveau(new Niveau(this.niveauCourant.getNumNiveau() + 1, this.niveauCourant.getDifficulte()));
			else if (!this.niveauCourant.getDifficulte().equals("Expert"))
				this.setNiveau(new Niveau(1, this.tabDifficultee[this.augmenterDifficulte(this.niveauCourant.getDifficulte())]));
		}
		else
			this.setNiveau(new Niveau(this.niveauCourant.getNumNiveau()));
		
		this.pl = new Plateau(this.niveauCourant.getPiece());	
		this.partie.enregistrerPartie();
		this.alEtatPrecedent.clear();
		this.alEtatPrecedent.add(new Plateau(this.copieTableau(this.pl.getPlateau())));
	}
	
	/**
	 * Charge le niveau pr�c�dent
	 * Cette m�thode g�re aussi la diminution de la difficult� en cas de niveau minimum atteint.
	 * 
	 */
	public void diminuerNiveau()
	{
		if(this.niveauCourant.getNumNiveau() > 1)
			this.setNiveau(new Niveau(this.niveauCourant.getNumNiveau() - 1, this.niveauCourant.getDifficulte()));
		else
			if(!this.niveauCourant.getDifficulte().equals("Debutant"))
				this.setNiveau(new Niveau(this.getDifficulteCourante().size(), this.tabDifficultee[this.diminuerDifficulte(this.niveauCourant.getDifficulte())]));

		if(this.niveauCourant.getDifficulte().equals("Edite"))
			this.setNiveau(new Niveau(this.niveauCourant.getNumNiveau()-1));
		
		this.pl = new Plateau(this.niveauCourant.getPiece());
		
		this.alEtatPrecedent.clear();
		this.alEtatPrecedent.add(new Plateau(this.copieTableau(this.pl.getPlateau())));
	}
	
	/**
	 * Cr�e une nouvelle partie
	 * 
	 */
	public void creerPartie()
	{
		this.partie.nouvellePartie();
	}
	
	/**
	 * Replace toutes les pi�ces afin d'obtenir le plateau d'origine
	 * 
	 */
	public void rejouer()
	{
		if(!this.niveauCourant.getDifficulte().equals("Edite"))
			this.setNiveau(new Niveau(this.niveauCourant.getNumNiveau(), this.niveauCourant.getDifficulte()));
		else
			this.setNiveau(new Niveau(this.niveauCourant.getNumNiveau()-1));
		
		this.pl = new Plateau(this.niveauCourant.getPiece());
		
		this.alEtatPrecedent.clear();
		this.alEtatPrecedent.add(new Plateau(this.copieTableau(this.pl.getPlateau())));
	}
	
	/**
	 * M�thode appel� dans diminuerNiveau pour diminuer la difficult� du niveau courant si celui ci
	 * est de num�ro 1.
	 * @param d est la difficult� du niveau courant.
	 * @return l'indice de la difficult� obtenue.
	 * 
	 */
	private int diminuerDifficulte(String d) 
	{
		int index = -1;
		for(int i = 0; i < this.tabDifficultee.length; i++)
			if(this.tabDifficultee[i].equalsIgnoreCase(d))
				index = i;

		
		if(index - 1 < 0) return 0;
		else return index - 1;
 	}
	
	/**
	 * M�thode appel� dans augmenterNiveau pour augmenter la difficult� 
	 * si le niveau courant est le dernier de sa difficult�.
	 * @param d est la difficult� du niveau courant.
	 * @return l'indice de la difficult� obtenue.
	 * 
	 */
	private int augmenterDifficulte(String d) 
	{
		int index = -1;
		for(int i = 0; i < this.tabDifficultee.length; i++)
			if(this.tabDifficultee[i].equalsIgnoreCase(d))
				index = i;

		
		if(index + 1 > 3) return index;
		else return index + 1;
	}
	
	/**
	 * M�thode v�rifiant si le joueur est victorieux sur le niveau courant.
	 * @return vrai si il a gagn� et faux si il n'a pas encore gagn�
	 * 
	 */
	public boolean victoireNiveauCourant() 
	{
		int nbPiece = 0;
		
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				if(this.pl.getPlateau()[i][j] != null)
					nbPiece++;
		
		if(nbPiece == 1 && !(this.niveauCourant.getDifficulte().equals("Expert") && this.niveauCourant.getNumNiveau() == this.alNiveauExpert.size())
				        && !(this.niveauCourant.getDifficulte().equals("Edite") && this.niveauCourant.getNumNiveau() == this.alNiveauEdite.size()))
			this.augmenterNiveau();
		
		return nbPiece == 1;
	}
	
	/**
	 * M�thode permmettant de remettre le plateau a l'�tat pr�c�dent.
	 * (fonctionnalit�e annuler coup)
	 * 
	 */
	public void coupPrecedent()
	{
		if(this.alEtatPrecedent.size() > 1)
		{
			this.alEtatPrecedent.remove(this.alEtatPrecedent.size()-1);
			Piece[][] plateauPrecedent = this.alEtatPrecedent.get(this.alEtatPrecedent.size()-1).getPlateau();
			this.pl = new Plateau(this.copieTableau(plateauPrecedent), this.pl.getPiecesCapturees());
		}
	}
	
	/**
	 * M�thode appel� apr�s chaque d�placement permmettant de sauvegarder l'�tat du plateau.
	 * 
	 */
	public void sauvegardeCoup()
	{
		this.alEtatPrecedent.add(new Plateau(this.copieTableau(this.pl.getPlateau())));
	}
	
	/**
	 * M�thode permmettant d'initialiser tout les niveaux dans des ArrayList et
	 * ainsi connaitre le nombre de niveau par difficult�.
	 * 
	 */
	public void initNiveau()
	{
		alNiveauDebutant      = new ArrayList<>();
		alNiveauIntermediaire = new ArrayList<>();
		alNiveauAvance        = new ArrayList<>();
		alNiveauExpert        = new ArrayList<>();
		alNiveauEdite         = new ArrayList<>();
		
		for(int i = 1; i < 100; i++)
		{
			Niveau tmp = new Niveau(i, tabDifficultee[0]);
			if(!tmp.getInstancier())
				alNiveauDebutant.add(tmp);
			Niveau tmp1 = new Niveau(i, tabDifficultee[1]);
			if(!tmp1.getInstancier())
				alNiveauIntermediaire.add(tmp1);
			Niveau tmp2 = new Niveau(i, tabDifficultee[2]);
			if(!tmp2.getInstancier())
				alNiveauAvance.add(tmp2);
			Niveau tmp3 = new Niveau(i, tabDifficultee[3]);
			if(!tmp3.getInstancier())
				alNiveauExpert.add(tmp3);
			Niveau tmp4 = new Niveau(i-1);
			if(!tmp4.getInstancier())
				alNiveauEdite.add(tmp4);
		}
	}
	
	/**
	 * M�thode permmettant de recopier un tableau de Piece � deux dimensions.
	 * @param or est un tableau de piece � deux dimensions.
	 * @return le tableau de piece recopi�.
	 * 
	 */
	public Piece[][] copieTableau(Piece[][] or)
	{
		Piece tabPiece[][] = new Piece[4][4];
		
		for(int i = 0; i < tabPiece.length; i++)
			for(int j = 0; j < tabPiece.length; j++) {
				if(or[i][j]!=null)
				{
					Piece piece = (Piece) or[i][j].clone();
					tabPiece[i][j] = piece;
				}
			}
		
		return tabPiece;
	}
	
	/**
	 * M�thode permmettant de connaitre la difficult� courante
	 * @return l'ArrayList de niveau de la difficult� courante.
	 * 
	 */
	public ArrayList<Niveau> getDifficulteCourante()
	{
		ArrayList<Niveau> difficulteCourante = this.alNiveauDebutant;
		if(this.niveauCourant.getDifficulte().equals("Intermediaire"))
			difficulteCourante = this.alNiveauDebutant;
		else if(this.niveauCourant.getDifficulte().equals("Avance"))
			difficulteCourante = this.alNiveauIntermediaire;
		else if(this.niveauCourant.getDifficulte().equals("Expert"))
			difficulteCourante = this.alNiveauAvance;
		return difficulteCourante;
	}

	public Niveau   getNiveau     (){return this.niveauCourant ;}
	public Plateau  getPlateau    (){return this.pl            ;}
	public Partie   getPartie     (){return this.partie        ;}
	public String[] getDifficultes(){return this.tabDifficultee;}
	public String   getTheme      (){return this.theme         ;}    
	public boolean  getCheatActive(){return this.cheatActive   ;}
	
	
	public void setTheme      (String theme){this.theme = theme      ;}  
	public void setNiveau     (Niveau niv)  {this.niveauCourant = niv;}
	public void setPlateau    (Plateau pl)  {this.pl = pl            ;}
	public void setCheatActive(boolean b)   {this.cheatActive = b    ;}
	
	public ArrayList<Niveau> getNiveauDebutant     (){return this.alNiveauDebutant     ;}
	public ArrayList<Niveau> getNiveauIntermediaire(){return this.alNiveauIntermediaire;}
	public ArrayList<Niveau> getNiveauAvance       (){return this.alNiveauAvance       ;}
	public ArrayList<Niveau> getNiveauExpert       (){return this.alNiveauExpert       ;}
	public ArrayList<Niveau> getNiveauEdite        (){return this.alNiveauEdite        ;}
	
	public static void main(String[] a)
	{
		new Controleur();
	}
	
}
