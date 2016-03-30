/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JTextArea;
/**
 *
 * @author Nicolas
 */
public class Membership {
    //die Hauptklasse, die die drei Algorithmen für Closure, Membership und Überdeckung enthält
    
    JTextArea output;
    
    public void getOutput(JTextArea output){
        this.output=output;
    }
    
    //Membership-Algorithmus
    public boolean member(Funktion F, Abhaengigkeit a) {
        output.setText("Durchführung des Membership-Algorhythmus mit: F = "+F.toString()+" und der Abhaengigkeit a = "+a+"\n");
        return closure(F, a.links).containsAll(a.rechts);
    }
    
    //Closure-Algorithmus
    public HashSet<String> closure(Funktion F, Set<String> left) {
        output.setText(output.getText()+"Durchführung des Closure-Algorhythmus mit: F="+F+" und der Hülle h="+left+"\n");
        HashSet abhlist = F.getAbhaengigkeiten();
        HashSet listx = new HashSet();
        listx.addAll(left);
        HashSet listxx = new HashSet();
        //Hülle und Abhängigkeitenliste werden kopiert
        output.setText(output.getText()+"Eine Kopie der Hülle wurde gespeichert.\n");
        //solange listx und listxx ungleich sind
        do {
            output.setText(output.getText()+"Berechnung der Hülle mit: "+listx+"\n");
            listxx.addAll(listx);
            //listxx und listx werden auf den gleichen Stand gebracht
            output.setText(output.getText()+"Die Ableitungsregel R wurde angewandt.\n");
            Iterator it = abhlist.iterator();
            while (it.hasNext()) {
                Abhaengigkeit abh = (Abhaengigkeit) it.next();
                output.setText(output.getText()+"Prüfen, ob Y teilmenge von X* ist. Ergebnis= "+listx.containsAll(abh.links)+"\n");
                //wenn listx die linke Seite der Abhängigkeit vollständig enthält, wird die rechte Seite hinzugefügt
                if (listx.containsAll(abh.links)) {
                    listx.addAll(abh.rechts);
                    output.setText(output.getText()+"Ableitungsregel A wurde angeandt.\n");
                }
            }
        } while (!listx.equals(listxx));
        return listx;
    }
    
    //minimale Überdeckung - Algorithmus
    public Funktion reducedCover(Funktion F) {
        //Kopie der Funktion durchführen
        Funktion G = F.copy();
        HashSet abhlist = new HashSet();
        abhlist.addAll(G.getAbhaengigkeiten());
        HashSet sidelist = new HashSet();
        HashSet worklist = new HashSet();
        Abhaengigkeit help;
        Iterator it = abhlist.iterator();
        //1. Durchgang
        //Linksreduktion
        while (it.hasNext()) {
            Abhaengigkeit abh = (Abhaengigkeit) it.next();
            output.setText(output.getText()+"Linksreduktion wird durchgeführt für: "+abh+"\n");
            sidelist.clear();
            sidelist.addAll(abh.links);
            worklist.clear();
            worklist.addAll(sidelist);
            Iterator iter = sidelist.iterator();
            while (iter.hasNext()) {
                String s = (String) iter.next();
                output.setText(output.getText()+"Prüfen, ob "+s+"unwesentlich ist.\n");
                worklist.remove(s);
                output.setText(output.getText()+"Prüfen, ob Y teilmenge von CLOSURE(F,X-"+s+") ist. Ergebnis: "+closure(G, worklist).containsAll(abh.rechts)+"\n");
                if (closure(G, worklist).containsAll(abh.rechts)) {
                    abh.setLeft(worklist);
                } else {
                    worklist.add(s);
                }
            }
        }
        output.setText(output.getText()+"Nach Linksüberdeckung " +abhlist+"\n");
        it = abhlist.iterator();
        //2. Durchgang
        //Rechtsreduktion
        while (it.hasNext()) {
            Abhaengigkeit abh = (Abhaengigkeit) it.next();
            output.setText(output.getText()+"Rechtsreduktion wird durchgeführt für: "+abh+"\n");
            sidelist.clear();
            sidelist.addAll(abh.rechts);
            worklist.clear();
            worklist.addAll(sidelist);
            Iterator iter = sidelist.iterator();
            while (iter.hasNext()) {
                help = new Abhaengigkeit(abh.links, abh.rechts);
                help.setLeft(abh.links);
                help.setRight(abh.rechts);
                G.removeAbh(abh, G);
                String s = (String) iter.next();
                output.setText(output.getText()+"Prüfen, ob "+s+"unwesentlich ist.\n");
                worklist.remove(s);
                abh.setRight(worklist);
                G.addAbhaengigkeit(abh);
                output.setText(output.getText()+"Prüfen, ob Y teilmenge von CLOSURE(F-{"+help+"}U{"+help+"-"+s+"},X) ist. Ergebnis: "+(!closure(G, abh.links).contains(s))+"\n");
                if (!closure(G, abh.links).contains(s)) {
                    G.removeAbh(abh, G);
                    abh.setRight(help.rechts);
                    G.addAbhaengigkeit(abh);
                }
            }
        }
        output.setText(output.getText()+"Nach Rechtsreduktion "+abhlist+"\n");
        abhlist.clear();
        abhlist.addAll(G.getAbhaengigkeiten());
        it = abhlist.iterator();
        //3. Durchgang
        //ist die rechte Seite einer Abhängigkeit leer, lösche diese Abhängigkeit aus der Liste
        worklist.clear();
        worklist.addAll(abhlist);
        output.setText(output.getText()+"Elimination von Abhängigkeiten nach folgendem Muster: X->{}\n");
        while (it.hasNext()) {
            Abhaengigkeit abh = (Abhaengigkeit) it.next();
            if (abh.rechts == null || abh.rechts.isEmpty()) {
                worklist.remove(abh);
            }
        }
        G.setAbhaengigkeiten(worklist);
        abhlist.clear();
        abhlist.addAll( G.getAbhaengigkeiten());
        worklist.clear();
        worklist.addAll(abhlist);
        it = abhlist.iterator();
        Iterator iter;
        Abhaengigkeit abh1, abh2;
        int anzahl = 1;
        //4. Durchgang
        //haben mehrere Abhängigkeiten die gleiche linke Seite, vereinige die rechten Seiten, sodass
        //nur noch eine Abhängigkeit übrig bleibt
        output.setText(output.getText()+"Vereinigung von Abhängigkeiten nach folgendem Muster X->Y1, X->Y2...zu X->Y1Y2\n");
        while (it.hasNext()) {
            abh1 = (Abhaengigkeit) it.next();
            iter = abhlist.iterator();
            for (int i = 0; i < anzahl; i++) {
                if (iter.hasNext()) {
                    iter.next();
                } else {
                    break;
                }
            }
            while (iter.hasNext()) {
                abh2 = (Abhaengigkeit) iter.next();
                if (abh2.links.equals(abh1.links)) {
                    abh1.rechts.addAll(abh2.rechts);
                    worklist.remove(abh2);
                }
            }
            anzahl++;
        }
        G.setAbhaengigkeiten(worklist);
        output.setText(output.getText()+"Reduktion durchgeführt.\n");
        return G;
    }
}
