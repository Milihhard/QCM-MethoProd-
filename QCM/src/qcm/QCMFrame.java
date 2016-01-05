/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import user.Utilisateur;

/**
 *
 * @author Emilien
 */
public class QCMFrame extends JFrame implements ActionListener {

    //global
    
    private JLabel welcome;
    Utilisateur user;
    JPanel pano;

    //admin
    JButton ajoutAdmin;
    JLabel[] signalAdmin;

    //enseignant
    
    //etudiant
    public class QCMEtudiant{
        
    }
    
    public QCMFrame() {
        this.setTitle("QCM");
        pano = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcome = new JLabel("niah");
        welcome.setVisible(false);
        //"Bonjour " + user.getPrenom() + " " + user.getNom() + "(" + user.getId() + ")");

        GridBagConstraints contrainte = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        Connexion b = new Connexion(this);
        user = b.showDialog();
        if (user != null) {
            initGlobal(contrainte);
            switch (user.getType()) {
                case Admin:
                    initAdmin(contrainte);
                    break;
                case Enseignant:
                    initEnseignant(contrainte);
                    break;
                case Etudiant:
                    initEtudiant(contrainte);
                    break;
            }
        }
        this.pack();
    }

    private void initGlobal(GridBagConstraints contrainte) {
        welcome = new JLabel("Bonjour " + user.getPrenom() + " " + user.getNom() + "(" + user.getId() + ")");
        contrainte.fill = GridBagConstraints.BOTH;
        contrainte.gridx = 0;
        contrainte.gridy = 0;
        this.add(welcome, contrainte);
    }

    private void initAdmin(GridBagConstraints contrainte) {
        ajoutAdmin = new JButton("Ajouter un utilisateur");
        signalAdmin = new JLabel[1];
        signalAdmin[0] = new JLabel();
        contrainte.gridx = 0;
        contrainte.gridy = 1;
        this.add(ajoutAdmin, contrainte);
        contrainte.gridx++;
        this.add(signalAdmin[0], contrainte);

        ajoutAdmin.addActionListener(this);
    }

    private void initEnseignant(GridBagConstraints contrainte) {

    }

    private void initEtudiant(GridBagConstraints contrainte) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ajoutAdmin) {
            AddUser au = new AddUser(this);
            boolean b = au.showDialog();
            if (b) {
                hideSignal();
                signalAdmin[0].setText("New User!");
                this.pack();
            }
        }
    }

    public void hideSignal() {
        for (JLabel j : signalAdmin) {
            j.setText("");
        }
    }
}
