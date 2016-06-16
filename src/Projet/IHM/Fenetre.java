package Projet.IHM;

import javax.swing.JFrame;

import Projet.Controleur;

public class Fenetre extends JFrame
{
	private Controleur ctrl;
	private Menu menu;
	private Jeu jeu;
	private Editeur editeur;
	private MenuDefi menuDefi;
	
	public Fenetre(Controleur ctrl) 
	{	
		this.setTitle("Solitaire Chess - Menu");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.ctrl = ctrl;
		
		this.jeu = null;
		this.editeur = null;
		this.menuDefi = null;
		this.menu = new Menu(this.ctrl,this);
		this.add(this.menu);
		
		this.pack();
		
		this.setVisible(true);
	}
	
	public Menu getMenu() {
		return this.menu;
	}
	
	public Jeu getJeu() {
		return this.jeu;
	}
	
	public Editeur getEditeur() {
		return this.editeur;
	}
	
	public MenuDefi getMenuDefi() {
		return this.menuDefi;
	}
	
	public void setJeu(Jeu j) {
		this.jeu = j;
	}
	
	public void setEditeur(Editeur e) {
		this.editeur = e;
	}
	
	public void setMenuDefi(MenuDefi mD) {
		this.menuDefi = mD;
	}
	

}
