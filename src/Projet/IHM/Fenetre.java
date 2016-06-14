package Projet.IHM;

import javax.swing.JFrame;

import Projet.Controleur;

public class Fenetre extends JFrame
{
	private Controleur ctrl;
	
	public Fenetre(Controleur ctrl) 
	{	
		this.setTitle("Fenetre de jeu");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.ctrl = ctrl;
		
		this.add(new Jeu(this.ctrl));
		
		this.pack();
		
		this.setVisible(true);
	}
	

}
