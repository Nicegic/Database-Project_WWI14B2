/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.*;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 *
 * @author Nicolas
 */
public class GuiNew extends JFrame {

    GridBagLayout gbl;
    GridBagConstraints gbc;
    JTextField left, right;
    JLabel arrow, desc1, desc2;
    JScrollPane j1, j2, j3;
    JTextArea relation, function, output;
    JButton add, delete, execute;
    Container c;
    JCheckBox closure, member, overlay;
    int row;
    Komplettlistener kl;
    NiceToHaveListener nthl;

    public GuiNew() {
        row = 0;
        c = getContentPane();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        c.setLayout(gbl);
        left = new JTextField();
        right = new JTextField();
        arrow = new JLabel("-- >");
        relation = new JTextArea("Hier die Relation einfügen:\n"
                + "Format bitte so: X={...,...,...}");
        function = new JTextArea("");
        output = new JTextArea("");
        add = new JButton("hinzufügen");
        delete = new JButton("löschen");
        execute = new JButton("ausführen");
        overlay = new JCheckBox("nicht-redundante minimale Überdeckung");
        closure = new JCheckBox("Closure-Funktion (Hülle bestimmen)");
        member = new JCheckBox("Membership-Algorithmus durchführen");
        desc1 = new JLabel("Funktion");
        desc2 = new JLabel("Relation");
        j1 = new JScrollPane(relation);
        j2 = new JScrollPane(function);
        j3 = new JScrollPane(output);
        j1.setAutoscrolls(true);
        j2.setAutoscrolls(true);
        j3.setAutoscrolls(true);
        relation.setLineWrap(true);
        function.setLineWrap(true);
        output.setLineWrap(true);
        function.setWrapStyleWord(true);
        relation.setWrapStyleWord(true);
        output.setWrapStyleWord(true);
        j2.setPreferredSize(new Dimension(200, 84));
        j1.setPreferredSize(new Dimension(200, 84));
        j3.setPreferredSize(new Dimension(400, 150));
        left.setPreferredSize(new Dimension(75,27));
        right.setPreferredSize(new Dimension(75,27));
        arrow.setPreferredSize(new Dimension(25,27));
        arrow.setHorizontalTextPosition(JLabel.CENTER);
        gbc.gridx = 0;
        row = 0;
        gbc.gridy = row;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        c.add(desc1, gbc);
        gbc.gridwidth=2;
        gbc.gridx = 3;
        c.add(desc2, gbc);
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridheight = 2;
        gbc.gridwidth=3;
        c.add(j2, gbc);
        gbc.gridx = 3;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.gridwidth=2;
        c.add(j1, gbc);
        row += 2;
        gbc.ipady = 0;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0.5;
        c.add(left, gbc);
        gbc.gridx = 1;
        c.add(arrow, gbc);
        gbc.gridx = 2;
        c.add(right, gbc);
        gbc.gridx = 3;
        c.add(add, gbc);
        gbc.gridx = 4;
        c.add(delete, gbc);
        row++;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = row;
        c.add(closure, gbc);
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        c.add(member, gbc);
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        c.add(overlay, gbc);
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        c.add(execute, gbc);
        row++;
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.gridheight = 5;
        c.add(j3, gbc);
        function.setEditable(false);
        function.setText("Bitte trage in die beiden Felder unten deine Abhängigkeiten der Funktion ein und füge sie"
                + " mit Klick auf 'hinzufügen' zur Funktion hinzu. Mit löschen kann jeweils die letzte Abhängigkeit"
                + " gelöscht werden.");
        output.setEditable(false);
        output.setText("Hier werden alle möglichen Outputs stehen.");
        kl = new Komplettlistener(function, relation, output, add, delete, execute, left, right, closure, member, overlay);
        nthl = new NiceToHaveListener(add, right, relation);
        right.addKeyListener(nthl);
        relation.addKeyListener(nthl);
        add.addActionListener(kl);
        delete.addActionListener(kl);
        execute.addActionListener(kl);
        setLocation(200, 100);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
