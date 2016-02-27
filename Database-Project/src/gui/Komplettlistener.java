/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import data.Abhaengigkeit;
import data.Funktion;

/**
 *
 * @author Nicolas
 */
class Komplettlistener implements ActionListener {

    JTextArea function, relation, output;
    JButton add, delete, execute;
    JTextField left, right;
    ArrayList<String> attlist;
    ArrayList<Abhaengigkeit> abhlist;
    JCheckBox closure, member, overlay;

    public Komplettlistener(JTextArea function, JTextArea relation, JTextArea output,
            JButton add, JButton delete, JButton execute, JTextField left, JTextField right,
            JCheckBox closure, JCheckBox member, JCheckBox overlay) {
        this.function = function;
        this.relation=relation;
        this.output=output;
        this.add = add;
        this.delete = delete;
        this.execute=execute;
        this.left = left;
        this.right = right;
        this.closure=closure;
        this.member=member;
        this.overlay=overlay;
        attlist = new ArrayList();
        abhlist = new ArrayList();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(add) || ae.getSource().equals(delete)) {
            if (ae.getSource().equals(add)) {
                if (left.getText().equals("")) {
                    left.requestFocus();
                    output.setText("Zum Hinzufügen von Abhängigkeiten bitte immer beide Felder ausfüllen!");
                } else if (right.getText().equals("")) {
                    right.requestFocus();
                    output.setText("Zum Hinzufügen von Abhängigkeiten bitte immer beide Felder ausfüllen!");
                } else {
                    abhlist.add(new Abhaengigkeit(left.getText(), right.getText()));
                    left.setText("");
                    right.setText("");
                    left.requestFocus();
                }
            } else if (ae.getSource().equals(delete)) {
                if(abhlist.size()>0)
                    abhlist.remove(abhlist.size() - 1);
            }
            StringBuffer sb = new StringBuffer();
            if (abhlist.size() == 0) {
                sb.append("Es sind bisher keine Abhängigkeiten in der Funktion vorhanden!");
            } else {
                sb.append("Die Funktion beinhaltet momentan folgende Abhängigkeiten: \n");
                for (int i = 0; i < abhlist.size(); i++) {
                    sb.append(abhlist.get(i) + " \n");
                }
            }
            function.setText(new String(sb));
        }
        if (ae.getSource().equals(execute)) {
            if (closure.isSelected()) {
                doClosure();
            }else if(member.isSelected()){
                doMember();
            }else if(overlay.isSelected()){
                doOverlay();
            }
        }
    }
    
    private void doClosure(){
        //TODO supply code
    }
    
    private void doMember(){
        //TODO supply code
    }
    
    private void doOverlay(){
        //TODO supply code
    }
}
