/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.*;

/**
 *
 * @author Nicolas
 */
class NiceToHaveListener implements KeyListener {

    JButton add;
    JTextField right;
    JTextField relation;
    boolean firsttype=true;
    final String hint = "\n (Format der Relation: R={a,b,c,d,e,f,g,h})";

    public NiceToHaveListener(JButton add, JTextField right, JTextField relation) {
        this.add = add;
        this.right = right;
        this.relation = relation;
    }

    public void keyPressed(KeyEvent ke) {
        if (ke.getComponent().equals(right)) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                add.doClick();
            }
        }
        if(ke.getComponent().equals(relation)){
            if(firsttype){
                relation.setText("");
                firsttype=false;
            }
        }
    }

    public void keyReleased(KeyEvent ke) {

    }

    public void keyTyped(KeyEvent ke) {

    }
}
