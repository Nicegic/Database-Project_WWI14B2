/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Nicolas
 */
public class CheckBoxListener implements ActionListener {   //Listener für die Auswahl von Closure, Member und Überdeckung

    JCheckBox closure, member, cover;
    JTextField closurefield, memberleft, memberright;
    JLabel memberarrow;
    JFrame f;
    ButtonGroup bg;
    boolean wasSelected = false;

    public CheckBoxListener(JTextField memberleft, JLabel memberarrow, JTextField memberright, JTextField closurefield, JFrame f, ButtonGroup bg, JCheckBox closure, JCheckBox member, JCheckBox cover) {
        this.f = f;
        this.memberleft = memberleft;
        this.memberright = memberright;
        this.memberarrow = memberarrow;
        this.closurefield = closurefield;
        this.closure = closure;
        this.member = member;
        this.cover = cover;
        this.bg = bg;
    }

    public void actionPerformed(ActionEvent ae) {
        //Zum Anzeigen der spezifischen Eingabefelder und Verschönerung der Auswahl
        if (ae.getSource().equals(closure)) {
            if (closurefield.isVisible()) {
                bg.clearSelection();
                deselect();
            } else {
                hideMember();
                showClosure();
                wasSelected=false;
            }
        } else if (ae.getSource().equals(member)) {
            if (memberleft.isVisible()) {
                deselect();
                bg.clearSelection();
            } else {
                showMember();
                hideClosure();
                wasSelected=false;

            }
        } else {
            if (wasSelected) {
                bg.clearSelection();
                wasSelected = false;
            } else {
                deselect();
                wasSelected = true;
            }
        }
        f.pack();
    }

    private void deselect() {
        hideClosure();
        hideMember();
        wasSelected=false;
    }

    private void showMember() {
        memberleft.setVisible(true);
        memberarrow.setVisible(true);
        memberright.setVisible(true);
    }

    private void hideMember() {
        memberleft.setVisible(false);
        memberright.setVisible(false);
        memberarrow.setVisible(false);
    }

    private void showClosure() {
        closurefield.setVisible(true);
    }

    private void hideClosure() {
        closurefield.setVisible(false);
    }
}
