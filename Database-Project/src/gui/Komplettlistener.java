/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import data.*;

/**
 *
 * @author Nicolas
 */
class Komplettlistener implements ActionListener {

    JTextArea function, output;
    JButton add, delete, execute;
    JTextField left, right, relation;
    ArrayList<String> attlist;
    JCheckBox closure, member, overlay;
    Funktion f;
    boolean relationchanged = true;
    String relationlast, huelle;

    public Komplettlistener(JTextArea function, JTextField relation, JTextArea output,
            JButton add, JButton delete, JButton execute, JTextField left, JTextField right,
            JCheckBox closure, JCheckBox member, JCheckBox overlay) {
        this.function = function;
        this.relation = relation;
        this.output = output;
        this.add = add;
        this.delete = delete;
        this.execute = execute;
        this.left = left;
        this.right = right;
        this.closure = closure;
        this.member = member;
        this.overlay = overlay;
        f = new Funktion();
        relationlast = null;
        huelle = null;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!relation.getText().equals(relationlast)) {
            relationchanged = true;
            relationlast = relation.getText();
        }
        if (relationchanged) {
            try {
                if (relation.getText().equals("Hier die Relation einfügen:\n Format bitte so: X={...,...,...}")) {
                    relation.setText("Bitte eine Relation eingeben!!!");
                    return;
                } else {
                    attlist = new Extractor().buildRelation(relation.getText());
                    f.setAlphabet(attlist);
                    relationchanged = false;
                }
            } catch (IllegalEntryException iee) {
                output.setText(iee.getMessage());
                return;
            }

        }
        if (ae.getSource().equals(add) || ae.getSource().equals(delete)) {
            if (ae.getSource().equals(add)) {
                if (left.getText().equals("")) {
                    left.requestFocus();
                    output.setText("Zum Hinzufügen von Abhängigkeiten bitte immer beide Felder ausfüllen!");
                } else if (right.getText().equals("")) {
                    right.requestFocus();
                    output.setText("Zum Hinzufügen von Abhängigkeiten bitte immer beide Felder ausfüllen!");
                } else {
                    try {
                        f.addAbhaengigkeit(new Abhaengigkeit(left.getText(), right.getText()));
                    } catch (NotInAlphabetException niae) {
                        System.out.println(niae.getMessage());
                    }
                    left.setText("");
                    right.setText("");
                    left.requestFocus();
                }
            } else if (ae.getSource().equals(delete)) {
                f.removeAbhaengigkeit();
            }
            StringBuffer sb = new StringBuffer();
            if (f.keineAbhaengigkeiten()) {
                sb.append("Es sind bisher keine Abhängigkeiten in der Funktion vorhanden!");
            } else {
                sb.append("Die Funktion beinhaltet momentan folgende Abhängigkeiten: \n");
                ArrayList<Abhaengigkeit> abhlist = f.getAbhaengigkeiten();
                for (int i = 0; i < abhlist.size(); i++) {
                    sb.append(abhlist.get(i) + " \n");
                }
            }
            function.setText(new String(sb));
        }
        if (ae.getSource().equals(execute)) {
            if (closure.isSelected()) {
                doClosure();
            } else if (member.isSelected()) {
                doMember();
            } else if (overlay.isSelected()) {
                doOverlay();
            }
        }
    }

    private void doClosure() {
        try {
            attlist = new Extractor().buildRelation(relation.getText());
        } catch (IllegalEntryException iee) {
            System.out.println(iee.getMessage());
        }
        if (attlist != null) {
            EingabeHuelle eh = new EingabeHuelle(this);
            while (huelle == null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException inte) {
                }
            }
            String result = Closure.closure(f, huelle);
            output.setText(result);
        }
    }

    private void doMember() {
        //TODO supply code
    }

    private void doOverlay() {
        //TODO supply code
    }
}
