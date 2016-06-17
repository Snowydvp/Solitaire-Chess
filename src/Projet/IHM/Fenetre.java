package Projet.IHM;

import java.awt.Toolkit;

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
	
	public Fenetre(Controleur ctrl) 
	{	
		this.setTitle("Solitaire Chess - Menu");
		this.setResizable(false);
		this.setIconImage(new ImageIcon("Images/Interface/crown.png").getImage());
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.ctrl     = ctrl;
		this.jeu      = null;
		this.editeur  = null;
		this.menuDefi = null;
		this.menu     = new Menu(this.ctrl,this);
		this.add(this.menu);
		
		this.pack();
		
        this.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - this.getWidth() / 2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - this.getHeight() / 2));
		this.setVisible(true);
	}
	
	public Menu     getMenu()    {return this.menu    ;}
	public Jeu      getJeu()     {return this.jeu     ;}
	public Editeur  getEditeur() {return this.editeur ;}
	public MenuDefi getMenuDefi(){return this.menuDefi;}
	
	public void setJeu(Jeu j)           {this.jeu = j      ;}
	public void setEditeur(Editeur e)   {this.editeur = e  ;}
	public void setMenuDefi(MenuDefi mD){this.menuDefi = mD;}
}
