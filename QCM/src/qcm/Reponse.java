/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcm;

/**
 *
 * @author p1406269
 */
public class Reponse {
    public int id;
    public int idQuest;
    public String Rep;
    public Reponse(int id, int idQuest, String Rep){
        this.id=id;
        this.idQuest=idQuest;
        this.Rep=Rep;
        
    }
    public String toString(){
        return Rep;
    }
}
