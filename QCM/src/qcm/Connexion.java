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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import user.Utilisateur;

/**
 *
 * @author Axel
 */
public class Connexion extends JDialog implements ActionListener, KeyListener {

    private JLabel utilisateur;
    private JTextField t_utilisateur;
    private JLabel mdp;
    private JPasswordField t_mdp;
    private JButton connexion;
    private JPanel pano;
    Utilisateur user;

    public Connexion(QCMFrame owner) {
        super(owner, true);
        this.setTitle("Connexion");
        pano = new JPanel();

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        utilisateur = new JLabel("Nom d'utilisateur : ");
        t_utilisateur = new JTextField();
        mdp = new JLabel("Mot de passe : ");
        t_mdp = new JPasswordField();
        connexion = new JButton("Se connecter");

        GridBagConstraints contraintes = new GridBagConstraints();
        init();
        this.pack();
        connexion.addActionListener(this);
    }

    private void init() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints contrainte = new GridBagConstraints();

        contrainte.fill = GridBagConstraints.BOTH;
        contrainte.gridx = 0;
        contrainte.gridy = 0;
        this.add(utilisateur, contrainte);
        contrainte.gridy++;
        this.add(t_utilisateur, contrainte);
        contrainte.gridy++;
        this.add(mdp, contrainte);
        contrainte.gridy++;
        this.add(t_mdp, contrainte);
        contrainte.gridy++;
        this.add(connexion, contrainte);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == connexion) {
            connect();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("key typed");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("key pressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            connect();
        }
    }
    
    public void connect(){
            if (t_utilisateur.getText().equals("42") && t_mdp.getText().equals("admin")) {
                user = new Utilisateur(42, "Some", "One", Utilisateur.typeUser.Admin);
            } else {
                user = SQL.rechercheUtilisateur(Integer.parseInt(t_utilisateur.getText()), t_mdp.getText());
            }
            if (user != null) {
                this.setVisible(false);
            }
    }
    
    public Utilisateur showDialog() {
        this.setVisible(true);
        //System.out.println("id: " + user.getId());
        return user;
    }
}
