/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Extractor;
import data.Abhaengigkeit;
import data.Funktion;
import data.Membership;
import javax.swing.*;
import java.awt.event.*;
import data.IllegalEntryException;
import data.NotInAlphabetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 *
 * @author Nicolas
 */
class Komplettlistener implements ActionListener {

    JTextArea function, output;
    JButton add, delete, execute, addrelation;
    JTextField left, right, relation, closurefield;
    ConcurrentSkipListSet<String> attlist;
    JCheckBox closure, member, overlay;
    Funktion f;
    public static boolean relationchanged;
    boolean firsttime;
    String relationlast, huelle;
    JComboBox<Object> memberbox;
    Membership m = new Membership();

    public Komplettlistener(JTextArea function, JTextField relation, JTextArea output,
            JButton add, JButton delete, JButton execute, JButton addrelation, JTextField left, JTextField right,
            JCheckBox closure, JCheckBox member, JCheckBox overlay, JTextField closurefield, JComboBox memberbox) {
        this.function = function;
        this.relation = relation;
        this.output = output;
        this.add = add;
        this.delete = delete;
        this.execute = execute;
        this.addrelation = addrelation;
        this.left = left;
        this.right = right;
        this.closure = closure;
        this.member = member;
        this.overlay = overlay;
        this.closurefield = closurefield;
        this.memberbox = memberbox;
        f = new Funktion();
        relationchanged = false;
        firsttime = true;
        huelle = null;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (relationchanged) {
            try {
                buildRelation();
                if(!firsttime)
                    forceValidation();
                firsttime=false;
            } catch (IllegalEntryException iee) {
                output.setText(iee.getMessage());
            }
        }
        if (ae.getSource().equals(addrelation)) {
            relationchanged = false;
        }

        if (ae.getSource()
                .equals(add) || ae.getSource().equals(delete)) {
            if (ae.getSource().equals(add)) {
                if (!left.isEditable()) {
                    output.setText("Bitte erst eine Relation eintragen, damit die Abhängigkeiten validiert werden können!");
                    return;
                } else if (left.getText().equals("")) {
                    left.requestFocus();
                    output.setText("Zum Hinzufügen von Abhängigkeiten bitte immer beide Felder ausfüllen!");
                } else if (right.getText().equals("")) {
                    right.requestFocus();
                    output.setText("Zum Hinzufügen von Abhängigkeiten bitte immer beide Felder ausfüllen!");
                } else {
                    try {
                        f.addAbhaengigkeit(new Abhaengigkeit(left.getText(), right.getText(), attlist));
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
                HashSet<Abhaengigkeit> abhlist = f.getAbhaengigkeiten();
                memberbox.removeAllItems();
                Iterator it = abhlist.iterator();
                while (it.hasNext()) {
                    Abhaengigkeit abh = (Abhaengigkeit) it.next();
                    memberbox.addItem(abh);
                    sb.append(abh + " \n");
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
        output.setText("");
        m.getOutput(output);
        if (attlist != null) {
            huelle = closurefield.getText();
            if (huelle == null || huelle == "") {
                output.setText("Bitte eine Hülle eingeben!");
                return;
            }
            Abhaengigkeit abh = null;
            try {
                abh = new Abhaengigkeit(huelle, attlist, null);
            } catch (NotInAlphabetException niae) {}
            if (abh == null) {
                output.setText("Bitte eine gültige Hülle eingeben!");
                return;
            }
            HashSet<String> result = m.closure(f, abh.getLeft());
            StringBuffer sb = new StringBuffer();
            Iterator it = result.iterator();
            output.setText(output.getText()+"\nDas Ergebnis der Closure-Funktion ist: "+result.toString());
        }
    }

    private void doMember() {
        int i = memberbox.getSelectedIndex();
        output.setText("");
        m.getOutput(output);
        boolean result = m.member(f, f.getAbhaengigkeit(i));
        output.setText(output.getText()+"\n"+ result);

    }

    private void doOverlay() {
        output.setText("");
        m.getOutput(output);
        Funktion g=m.reducedCover(f);
        String abh = "";
        for (int i = 0; i < g.getAbhSize(); i++) {
            abh = abh + g.getAbhaengigkeit(i) + "\n";
        }
        function.setText("Neue Abhaengigkeiten:\n" + abh);
        output.setText(output.getText()+"\nÜberdeckung durgeführt");
    }

    private void buildRelation() throws IllegalEntryException {
        attlist = new Extractor().buildRelation(relation.getText());
        f.setAlphabet(attlist);
        left.setEditable(true);
        right.setEditable(true);
    }
    
    private void forceValidation(){
        HashSet abhlist = new HashSet();
        abhlist.addAll(f.getAbhaengigkeiten());
        Iterator it = abhlist.iterator();
        String left,right;
        StringBuffer result = new StringBuffer("Es wurden Abhängigkeiten entfernt, da sie nicht zum neuen Alphabet konform sind: \n");
        while(it.hasNext()){
            Abhaengigkeit abh = (Abhaengigkeit)it.next();
            left = abh.getLeftString();
            right = abh.getRightString();
            try{
                Abhaengigkeit neu = new Abhaengigkeit(left,right,f.getAlphabet());
            }catch(NotInAlphabetException niae){
                f.removeAbh(abh, f);
                result.append(abh+" \n");
            }
        }
        if(result.length()>85)
            function.setText(result.toString());
    }
}
