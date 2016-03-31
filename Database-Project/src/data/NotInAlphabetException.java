/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Nicolas
 */
public class NotInAlphabetException extends Exception{
    //diese Exception wird geworfen, wenn eine Seite der Abhängigkeit Elemente enthält,
    //die nicht im Alphabet / in der Relation der Funktion vorhanden sind
    public NotInAlphabetException(){
        super();
    }
    
    public NotInAlphabetException(String message){
        super(message);
    }
}
