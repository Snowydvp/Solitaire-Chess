package Projet.IHM;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Projet.Controleur;

public class MenuDefi extends JPanel implements ActionListener {
	
	private JPanel listeDefisDebutant;
	private JPanel listeDefisIntermediaire;
	private JPanel listeDefisAvance;
	private JPanel listeDefisExpert;
	private Controleur ctrl;
	
	public MenuDefi(Controleur ctrl)
	{
		this.ctrl = ctrl;
		
		
		this.listeDefisDebutant = new GridLayout(this.ctrl.);
		this.listeDefisIntermediaire = new GridLayout();
		this.listeDefisAvance = new GridLayout();
		this.listeDefisExpert = new GridLayout();
	}

	public void actionPerformed(ActionEvent e) {
		
	}

}
