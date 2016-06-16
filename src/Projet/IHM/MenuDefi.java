package Projet.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import Projet.Controleur;
import Projet.Metier.Niveau;

public class MenuDefi extends JPanel implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	private JPanel debutant;
	private JPanel intermediaire;
	private JPanel avance;
	private JPanel expert;
	private Controleur ctrl;
	private Fenetre fenetre;
	
	public MenuDefi(Controleur ctrl, Fenetre fenetre)
	{
		this.fenetre = fenetre;
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(4,1));
		
		debutant = this.initialisePanel(this.ctrl.getNiveauDebutant(), "Défis débutant","Debutant");
		this.add(debutant);
		
		intermediaire = this.initialisePanel(this.ctrl.getNiveauIntermediaire(), "Défis intermediaire","Intermediaire");
		this.add(intermediaire);
		
		avance = this.initialisePanel(this.ctrl.getNiveauAvance(), "Défis avancé","Avance");
		this.add(avance);
		
		expert = this.initialisePanel(this.ctrl.getNiveauExpert(), "Défis expert","Expert");
		this.add(expert);
	}
	
	public JPanel initialisePanel(ArrayList<Niveau> alNiveau, String s, String difficulte) {
		JPanel tmp = new JPanel();
		tmp.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				s,0,0,new Font("Dialog", 1, 12),Color.BLACK));
		for ( int j = 0; j < alNiveau.size(); j++) 
		{
			JButton buttonTmp = new JButton(alNiveau.get(j).getNumNiveau()+"");
			buttonTmp.setName(difficulte);
			buttonTmp.addActionListener(this);
			if ( this.ctrl.getPartie().peutJouerNiveau(difficulte,alNiveau.get(j).getNumNiveau()-1))
				buttonTmp.setBackground(Color.GREEN);
			else 
			{
				buttonTmp.setBackground(Color.RED);
				buttonTmp.setEnabled(false);
			}
			tmp.add(buttonTmp);
		}
		
		return tmp;
	}

	public void actionPerformed(ActionEvent e) 
	{
		if ( e.getSource() instanceof JButton)
		{
			JButton tmp = (JButton) e.getSource();
			
			int num = Integer.parseInt(tmp.getText());
			String difficulte = tmp.getName();
			
			this.ctrl.setNiveau(new Niveau(num, difficulte));
			
        	this.setVisible(false);
        	Jeu j = new Jeu(this.ctrl, this.fenetre);
        	this.fenetre.setJeu(j);
            this.fenetre.add(j, BorderLayout.CENTER);
            this.fenetre.setTitle("Solitaire Chess - Jeu");
            this.fenetre.pack();
            this.fenetre.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - this.fenetre.getWidth() / 2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - this.fenetre.getHeight() / 2));
		}
	}

}
