package Projet.IHM;

import java.awt.GridLayout;

import javax.swing.*;

import Projet.Controleur;

public class Menu extends JFrame
{
	private Controleur ctrl;
	
	public Menu(Controleur ctrl)
	{
		this.ctrl = ctrl;
		
		this.setLayout(new GridLayout(8, 1));
		
		JButton init;
		
		init = new JButton();
		init.setText("Continuer");
		this.add(init);
		
		init = new JButton();
		init.setText("Nouvelle partie");
		this.add(init);

		init = new JButton();
		init.setText("Choisir niveau");
		this.add(init);
		
		init = new JButton();
		init.setText("Charger");
		this.add(init);
		
		init = new JButton();
		init.setText("Aide");
		this.add(init);
		
		init = new JButton();
		init.setText("Scores");
		this.add(init);
		

		init = new JButton();
		init.setText("Cr�er un niveau");
		this.add(init);
		
		init = new JButton();
		init.setText("Quitter");
		this.add(init);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		//nouveau jeu
		//continuer
		//selection niveau
		//option
		//quitter
		//son
	}
}
