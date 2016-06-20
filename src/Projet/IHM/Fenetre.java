package Projet.IHM;

import java.awt.*;

import javax.swing.*;

import Projet.Controleur;
import Projet.IHM.Projet.Usine;

/**
 * Fenetre principale de l'application: elle gère l'affichage de chaque mode.
 * @author BELLANGER Jessy, LINTOT Maxime, PICOT Maxence et SINAEVE Antoine
 *
 */
public class Fenetre extends JFrame
{
	private static final long serialVersionUID = 1L;
	private Menu menu;
	private Jeu jeu;
	private Editeur editeur;
	private MenuDefi menuDefi;
	
	/**
	 * Constructeur par défaut.
	 * @param ctrl est le Controleur.
	 * 
	 */
	public Fenetre(Controleur ctrl) 
	{
		this.setIconImage(Usine.ICONE_APPLICATION);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.menu         = new Menu(ctrl, this);
        this.add(this.menu);

		this.pack();
		
        this.centrer();
		this.setVisible(true);
	}
	
	/**
	 * Recentre la frame par rapport à l'ecran.
	 * 
	 */
    public void centrer()
    {
        this.pack();
        this.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - this.getWidth() / 2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - this.getHeight() / 2));
    }

	public void setJeu(Jeu j)           {this.jeu      = j ;}
	public void setEditeur(Editeur e)   {this.editeur  = e ;}
	public void setMenuDefi(MenuDefi mD){this.menuDefi = mD;}

	/**
	 * Affiche le menu principal sur la fenetre.
	 * @param contenuACacher est le panel qui va etre remplacer par le menu principal.
	 * 
	 */
    public void afficherMenu(JPanel contenuACacher)
    {
        contenuACacher.setVisible(false);

        this.add(this.menu);
        this.menu.setMenuBarre();
        this.menu.setVisible(true);
        this.setTitle("Solitaire Chess - Menu");
        this.centrer();
    }

	/**
	 * Affiche le mode jeu sur la fenetre.
	 * @param contenuACacher est le panel qui va etre remplacer par le mode jeu.
	 * @param j est le panel du jeu
	 * 
	 */
    public void afficherJeu(JPanel contenuACacher, Jeu j)
    {
        contenuACacher.setVisible(false);
        this.setJeu(j);
        this.add(this.jeu);
        this.jeu.setMenuBarre();
        this.jeu.setVisible(true);
        this.setTitle("Solitaire Chess - Jeu");
        this.centrer();
    }
    
	/**
	 * Affiche le mode editeur sur la fenetre.
	 * @param contenuACacher est le panel qui va etre remplacer par le mode editeur.
	 * 
	 */
    public void afficherEditeur(JPanel contenuACacher, Editeur e)
    {
        contenuACacher.setVisible(false);
        this.setEditeur(e);
        this.add(this.editeur);
        this.editeur.setMenuBarre();
        this.editeur.setVisible(true);
        this.setTitle("Solitaire Chess - Editeur");
        this.centrer();
    }
    
	/**
	 * Affiche la selection des défis sur la fenetre.
	 * @param contenuACacher est le panel qui va etre remplacer par le menu de sélection des défis.
	 * 
	 */
    public void afficherMenuDefi(JPanel contenuACacher, MenuDefi mD)
    {
        contenuACacher.setVisible(false);
        this.setMenuDefi(mD);
        this.add(this.menuDefi);
        this.menuDefi.setMenuBarre();
        this.menuDefi.setVisible(true);
        this.setTitle("Solitaire Chess - Séléction défi");
        this.centrer();
    }
}
