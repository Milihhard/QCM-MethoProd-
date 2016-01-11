/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author Emilien
 */
public class Utilisateur {
    public enum typeUser{
        Enseignant,
        Etudiant,
        Admin
    };
    int id;
    private String prenom, nom, mdp;
    private typeUser type;

    public Utilisateur(int id, String prenom, String nom, typeUser type) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.type = type;
    }
    
    public boolean isEtudiant(){
        return type == typeUser.Etudiant;
    }
    public boolean isEnseignant(){
        return type == typeUser.Enseignant;
    }
    public boolean isAdmin(){
        return type == typeUser.Admin;
    }

    public int getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getMdp() {
        return mdp;
    }

    public typeUser getType() {
        return type;
    }
    
    
}
