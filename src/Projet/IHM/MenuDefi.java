package Projet.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Projet.Controleur;
import Projet.Metier.Niveau;

public class MenuDefi extends JPanel implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	
	private Controleur ctrl;
	private Fenetre fenetre;
	
	private JRadioButton debutant;
	private JRadioButton intermediaire;
	private JRadioButton avance;
	private JRadioButton expert;

	private JPanel listeDebutant;
	private JPanel listeIntermediaire;
	private JPanel listeAvance;
	private JPanel listeExpert;
	
	public MenuDefi(Controleur ctrl, Fenetre fenetre)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.fenetre = fenetre;
		this.ctrl = ctrl;

		this.listeDebutant = initialisePanel(this.ctrl.getNiveauDebutant(), "Debutant");
		this.listeIntermediaire = initialisePanel(this.ctrl.getNiveauIntermediaire(), "Intermediaire");
		this.listeAvance = initialisePanel(this.ctrl.getNiveauAvance(), "Avance");
		this.listeExpert = initialisePanel(this.ctrl.getNiveauExpert(), "Expert");




		JPanel difficulte = new JPanel();
		difficulte.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				"Niveaux",0,0,new Font("Dialog", 1, 12),Color.BLACK
		));

		debutant = new JRadioButton("Défis débutant");
		debutant.addActionListener(this);
		debutant.setMnemonic(KeyEvent.VK_NUMPAD1);
		debutant.setSelected(true);
		difficulte.add(debutant);

		intermediaire = new JRadioButton("Défis intermédiaire");
		intermediaire.addActionListener(this);
		intermediaire.setMnemonic(KeyEvent.VK_NUMPAD2);
		difficulte.add(intermediaire);

		avance = new JRadioButton("Défis avancé");
		avance.addActionListener(this);
		avance.setMnemonic(KeyEvent.VK_NUMPAD3);
		difficulte.add(avance);

		expert = new JRadioButton("Défis expert");
		expert.addActionListener(this);
		expert.setMnemonic(KeyEvent.VK_NUMPAD4);
		difficulte.add(expert);

		this.add(difficulte, BorderLayout.NORTH);

		this.add(listeDebutant);
		this.add(listeIntermediaire);
		this.add(listeAvance);
		this.add(listeExpert);

		ButtonGroup group = new ButtonGroup();
		group.add(debutant);
		group.add(intermediaire);
		group.add(avance);
		group.add(expert);
	}
	
	public JPanel initialisePanel(ArrayList<Niveau> alNiveau, String s) 
	{
		JPanel tmp = new JPanel(new GridLayout(0, 5,10,10));
		tmp.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				s,0,0,new Font("Dialog", 1, 12),Color.BLACK
		));
		for ( int j = 0; j < alNiveau.size(); j++) {
			JButton buttonTmp = new JButton(alNiveau.get(j).getNumNiveau()+"");
			buttonTmp.setName(s);
			buttonTmp.addActionListener(this);
            buttonTmp.setSize(55, 26);
			if ( this.ctrl.getPartie().peutJouerNiveau(s,alNiveau.get(j).getNumNiveau()))
				buttonTmp.setBackground(Color.WHITE);
			else {
                buttonTmp.setBackground(Color.GRAY);
                buttonTmp.setEnabled(false);
            }
			tmp.add(buttonTmp);
			if ( !s.equals("Debutant"))
				tmp.setVisible(false);
		}
		return tmp;
	}

	public void actionPerformed(ActionEvent e) 
	{
		this.listeDebutant.setVisible(false);
		this.listeIntermediaire.setVisible(false);
		this.listeAvance.setVisible(false);
		this.listeExpert.setVisible(false);


		if(e.getSource() == this.debutant)
			this.listeDebutant.setVisible(true);
		else if(e.getSource() == this.intermediaire)
			this.listeIntermediaire.setVisible(true);
		else if(e.getSource() == this.avance)
			this.listeAvance.setVisible(true);
		else if(e.getSource() == this.expert)
			this.listeExpert.setVisible(true);

		this.fenetre.pack();
		
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
