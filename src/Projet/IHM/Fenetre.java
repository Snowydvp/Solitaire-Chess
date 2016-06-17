package Projet.IHM;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Projet.Controleur;

public class Fenetre extends JFrame
{
	private static final long serialVersionUID = 1L;
	private Controleur ctrl;
	private Menu menu;
	private Jeu jeu;
	private Editeur editeur;
	private MenuDefi menuDefi;
	private MenuDefiEdit menuDefiEdit;
	
	public Fenetre(Controleur ctrl) 
	{	
		this.setTitle("Solitaire Chess - Menu");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("Images/Interface/crown.png").getImage());
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.ctrl     = ctrl;
		this.jeu      = null;
		this.editeur  = null;
		this.menuDefi = null;
		this.menuDefiEdit = null;
		this.menu     = new Menu(this.ctrl,this);
		this.add(this.menu);
		
		this.pack();
		
		this.setVisible(true);
	}
	
	public Menu     getMenu()    {return this.menu    ;}
	public Jeu      getJeu()     {return this.jeu     ;}
	public Editeur  getEditeur() {return this.editeur ;}
	public MenuDefi getMenuDefi(){return this.menuDefi;}
	public MenuDefiEdit getMenuDefiEdit(){return this.menuDefiEdit;}
	
	public void setJeu(Jeu j)           {this.jeu = j      ;}
	public void setEditeur(Editeur e)   {this.editeur = e  ;}
	public void setMenuDefi(MenuDefi mD){this.menuDefi = mD;}
	public void setMenuDefiEdit(MenuDefiEdit mDE){this.menuDefiEdit = mDE;}
}
