package Projet.IHM;

import javax.swing.JFrame;

import Projet.Controleur;

public class Fenetre extends JFrame
{
	private Controleur ctrl;
	private Menu menu;
	private Jeu jeu;
	private Editeur editeur;
	
	public Fenetre(Controleur ctrl) 
	{	
		this.setTitle("Solitaire Chess - Menu");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.ctrl = ctrl;
		
		this.jeu = null;
		this.editeur = null;
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
	
	public void setJeu(Jeu j) {
		this.jeu = j;
	}
	
	public void setEditeur(Editeur e) {
		this.editeur = e;
	}
	

}
