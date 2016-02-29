/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.*;
import java.awt.Container;
/**
 *
 * @author Nicolas
 */
public class EingabeHuelle extends JFrame implements ActionListener, KeyListener {
    Container c;
    JButton go;
    JTextField huelle;
    Komplettlistener kl;
    
    public EingabeHuelle(Komplettlistener kl){
        this.kl=kl;
        c=getContentPane();
        go=new JButton("starten");
        huelle=new JTextField("");
        c.add(go,BorderLayout.SOUTH);
        c.add(huelle, BorderLayout.NORTH);
        go.addActionListener(this);
        huelle.addKeyListener(this);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(huelle.getText().equals("")){
            
        }else{
            kl.huelle=huelle.getText();
            dispose();
        }
    }
    public void keyPressed(KeyEvent ke){
        if(ke.getComponent().equals(huelle)&&ke.getKeyCode()==KeyEvent.VK_ENTER)
            go.doClick();
    }
    public void keyReleased(KeyEvent ke){
        
    }
    public void keyTyped(KeyEvent ke){
        
    }
}
