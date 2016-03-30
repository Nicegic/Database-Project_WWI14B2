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
public class IllegalEntryException extends Exception{
    //wird geworfen, wenn die Relation ungültige Zeichen enthält und sie deshalb nicht erstellt werden kann
    String message;
    public IllegalEntryException(){
        super();
    }
    public IllegalEntryException(String message){
        super(message);
    }
}
