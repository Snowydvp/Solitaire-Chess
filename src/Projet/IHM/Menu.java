package Projet.IHM;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Projet.Controleur;

public class Menu extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JButton continuer, nouvellePartie, choisirNiveau, chargerPartie,
	                aide, score;
	
	public Menu(Controleur ctrl)
	{
		this.ctrl = ctrl;
		
		this.setLayout(new GridLayout(8, 1));
		
		JButton continuer = new JButton("Continuer");
		this.add(continuer);
		
		JButton nouvellePartie = new JButton("Nouvelle partie");
		this.add(nouvellePartie);

		JButton choisirNiveau = new JButton("Choisir niveau");
		this.add(choisirNiveau);
		
		JButton chargerPartie = new JButton("Charger");
		this.add(chargerPartie);
		
		JButton aide = new JButton("Aide");
		this.add(aide);
		
		JButton scores = new JButton("Scores");
		this.add(scores);
		

		JButton creerNiveau = new JButton("Créer un niveau");
		this.add(creerNiveau);
		
		JButton quitter = new JButton("Quitter");
		this.add(quitter);
	}

	public void actionPerformed(ActionEvent e) {
		
		
	}
}
