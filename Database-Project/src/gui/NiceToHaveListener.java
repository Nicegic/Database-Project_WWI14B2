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
    //Listener, um die Eingabe in den Eingabefeldern etwas angenehmer zu gestalten

    JButton add, execute, addrelation;
    JTextField right, relation, closurefield, left, memberright;
    boolean firsttypecf = true;
    final String hint = "\n (Format der Relation: R={a,b,c,d,e,f,g,h})";

    public NiceToHaveListener(JButton add, JButton execute, JButton addrelation, JTextField closurefield, JTextField left, JTextField right, JTextField relation, JTextField memberright) {
        this.add = add;
        this.right = right;
        this.left = left;
        this.addrelation = addrelation;
        this.relation = relation;
        this.closurefield = closurefield;
        this.memberright = memberright;
        this.execute = execute;
    }

    public void keyPressed(KeyEvent ke) {
        //wenn in dem Eingabefeld ENTER gedrückt wird, wird der Button aktiviert
        //falls die Relation nachträglich geändert wird, werden die Abhängigkeitsfelder deaktiviert
        if (ke.getComponent().equals(relation)) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                addrelation.doClick();
            } else {
                Komplettlistener.relationchanged = true;
                right.setEditable(false);
                left.setEditable(false);
            }
        } else if (ke.getComponent().equals(right)) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                add.doClick();
            }
        } else if (ke.getComponent().equals(closurefield)) {
            if (firsttypecf) {
                closurefield.setText("");
                firsttypecf = false;
            }
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                execute.doClick();
            }
        } else if (ke.getComponent().equals(memberright)) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                execute.doClick();
            }
        }
    }

    public void keyReleased(KeyEvent ke) {

    }

    public void keyTyped(KeyEvent ke) {

    }
}
