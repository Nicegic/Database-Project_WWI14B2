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
    String message;
    public NotInAlphabetException(){
        super();
    }
    
    public NotInAlphabetException(String message){
        super(message);
    }
    
    public String getMessage(){
        return message;
    }
}
