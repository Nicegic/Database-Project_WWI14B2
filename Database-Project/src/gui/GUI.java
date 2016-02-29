/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Nicolas
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import data.Extractor;


//Klasse ist obsolet, wird gelöscht, sobald die neue GUI komplett eingebunden ist!

/*public class GUI extends JFrame implements ActionListener, KeyListener {

    JButton solve;
    JTextArea relation, abhaengigkeiten;
    JScrollPane scroll1, scroll2;
    JLabel output;
    JPanel content;
    Container c;
    JTextField hull;
    boolean relationfirst, abhaengigkeitenfirst, hullfirst;

    /*public GUI() {
        relationfirst = abhaengigkeitenfirst = hullfirst = true;
        c = getContentPane();
        content = new JPanel();
        content.setLayout(new GridLayout(2, 2, 10, 10));
        relation = new JTextArea("Hier bitte die Relation eingeben!");
        abhaengigkeiten = new JTextArea("Hier bitte die Abhängigkeiten eingeben!");
        scroll1 = new JScrollPane(relation);
        scroll1.setAutoscrolls(true);
        relation.setLineWrap(true);
        scroll2 = new JScrollPane(abhaengigkeiten);
        scroll2.setAutoscrolls(true);
        abhaengigkeiten.setLineWrap(true);
        solve = new JButton("Closure-Funktion ausführen");
        output = new JLabel("Hier werden die Ergebnisse angezeigt.");
        hull = new JTextField("Hier die gewünschte Hülle eingeben!");
        content.add(output);
        content.add(scroll1);
        content.add(scroll2);
        content.add(hull);
        c.add(content, BorderLayout.CENTER);
        c.add(solve, BorderLayout.SOUTH);
        solve.addActionListener(this);
        setSize(500,300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        relation.addKeyListener(this);
        abhaengigkeiten.addKeyListener(this);
        hull.addKeyListener(this);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent ae) {
        Extractor e = new Extractor(relation.getText(), abhaengigkeiten.getText(), hull.getText());
        e.buildRelation();
        e.buildAbhaengigkeiten();
        e.buildFunction();
        output.setText(e.doClosure());
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if (relationfirst && ke.getSource().equals(relation)) {
            relation.setText("");
            relationfirst = false;
        }
        if (abhaengigkeitenfirst && ke.getSource().equals(abhaengigkeiten)) {
            abhaengigkeiten.setText("");
            abhaengigkeitenfirst = false;
        }
        if (hullfirst && ke.getSource().equals(hull)) {
            hull.setText("");
            hullfirst = false;
        }
    }
}*/
