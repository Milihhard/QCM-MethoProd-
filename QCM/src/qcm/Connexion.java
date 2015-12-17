/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcm;

import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Axel
 */
public class Connexion extends JFrame {
    private JLabel utilisateur = new JLabel("Nom d'utilisateur : ");
    private JTextField t_utilisateur = new JTextField();
    private JLabel mdp = new JLabel("Mot de passe : ");
    private JTextField t_mdp = new JTextField();
    private JPanel pano = new JPanel();
    
    public Connexion(){
        this.setTitle("Connexion");
        GridBagConstraints contraintes = new GridBagConstraints();
        
    }
}
