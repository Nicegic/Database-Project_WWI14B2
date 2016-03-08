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
    String message;
    public IllegalEntryException(){
        super();
    }
    public IllegalEntryException(String message){
        super(message);
    }
}
