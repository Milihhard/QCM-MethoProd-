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
public class Question {
    
    public int id;
    public int idQCM;
    public String Quest;
    public Question(int id, int idQCM, String Quest){
        this.id=id;
        this.idQCM=idQCM;
        this.Quest=Quest;
        
    }
    
    public String toString(){
        return Quest;
    }
}

