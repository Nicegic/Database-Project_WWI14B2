/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Nicolas
 */
public class CheckBoxListener implements ActionListener {   //Listener für die Auswahl von Closure, Member und Überdeckung

    JComboBox memberbox;
    JCheckBox closure, member, cover;
    JTextField closurefield;
    JFrame f;
    ButtonGroup bg;
    boolean wasSelected = false;

    public CheckBoxListener(JComboBox memberbox, JTextField closurefield, JFrame f, ButtonGroup bg, JCheckBox closure, JCheckBox member, JCheckBox cover) {
        this.f = f;
        this.memberbox = memberbox;
        this.closurefield = closurefield;
        this.closure = closure;
        this.member = member;
        this.cover = cover;
        this.bg=bg;
    }

    public void actionPerformed(ActionEvent ae) {
        //Zum Anzeigen der spezifischen Eingabefelder und Verschönerung der Auswahl
        if (ae.getSource().equals(closure)) {
            if (closurefield.isVisible()) {
                bg.clearSelection();
                deselect();
            } else {
                memberbox.setVisible(false);
                closurefield.setVisible(true);
            }
        }
        else if(ae.getSource().equals(member)){
            if(memberbox.isVisible()){
                deselect();
                bg.clearSelection();
            }else{
                memberbox.setVisible(true);
                closurefield.setVisible(false);
            }
        }else{
            if(wasSelected){
                bg.clearSelection();
                wasSelected = false;
            }else{
                wasSelected=true;
            }
            deselect();
        }
            f.pack();
        }
    private void deselect(){
        memberbox.setVisible(false);
        closurefield.setVisible(false);
    }
}
